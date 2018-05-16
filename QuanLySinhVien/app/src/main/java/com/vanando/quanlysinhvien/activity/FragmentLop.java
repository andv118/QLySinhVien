package com.vanando.quanlysinhvien.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vanando.quanlysinhvien.adapter.AdapterLvLopHoc;
import com.vanando.quanlysinhvien.LopHoc;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Admin on 4/19/2018.
 */

public class FragmentLop extends Fragment {

    //url
    UrlConnect url = new UrlConnect();
    // asyncTask

    private View view;
    private ListView lvDanhSachLop;
    private AdapterLvLopHoc adapter;
    private ArrayList<LopHoc> arrLopHoc = new ArrayList<>();

    public static FragmentLop newIntance() {
        FragmentLop fragmentA = new FragmentLop();
        return fragmentA;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lop, container, false);
        initView();
        // get data tu JSON
        readJSON_GET();
        // set click view
        onClickView();

        return view;
    }

    public void readJSON_GET() {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {

                final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url.getUrlGetDatabase(), null,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray response) {
                                arrLopHoc.clear();

                                ArrayList<Integer> arrIdLopHoc2 = new ArrayList<>();
                                for (int i = 0; i < response.length(); i++) {
                                    try {
                                        JSONObject jsonObject = response.getJSONObject(i);
                                        int idLop = jsonObject.getInt("ID");
                                        String tenLop = jsonObject.getString("TenLop");
                                        String thoiGian = jsonObject.getString("THoiGian");
                                        String thu = jsonObject.getString("Thu");
                                        String phongHoc = jsonObject.getString("PhongHoc");

                                        arrLopHoc.add(new LopHoc(idLop, tenLop, thoiGian, thu, phongHoc));
                                        arrIdLopHoc2.add(idLop);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                                // set adapter
                                setAdapter();

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("errorVolley", "loi : \t" + error.toString());
                                Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(jsonArrayRequest);

            }
        });
        myThread.start();
    }

    private void setAdapter() {
        adapter = new AdapterLvLopHoc(getActivity(), R.layout.custom_lv_dslop, arrLopHoc, new OnDeleteLopHocListener() {
            @Override
            public void onDeleteSuccess() {
                readJSON_GET();
            }
        });
        lvDanhSachLop.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void onClickView() {
        // set click item listview chuyen danh sach sv
        lvDanhSachLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String tenLopHocIntent = arrLopHoc.get(position).getTenLopHoc();
                int idLopHocIntent = arrLopHoc.get(position).getId();

                Intent intent = new Intent(getActivity(), DanhSachSinhVien.class);
                intent.putExtra("tenLopHoc", tenLopHocIntent);
                intent.putExtra("idLopHoc", idLopHocIntent);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        lvDanhSachLop = (ListView) view.findViewById(R.id.listviewFragmentLop);
    }
}

