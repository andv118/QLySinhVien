package com.vanando.quanlysinhvien.Lop_Hoc.fragment;

import android.content.Intent;
import android.os.Bundle;
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
import com.vanando.quanlysinhvien.MyApplication;
import com.vanando.quanlysinhvien.Sinh_Vien.activity.DanhSachSinhVien;
import com.vanando.quanlysinhvien.Lop_Hoc.adapter_database.AdapterLvLopHoc;
import com.vanando.quanlysinhvien.Lop_Hoc.object.LopHoc;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 4/19/2018.
 */

public class FragmentLop extends Fragment {

    //url
    UrlConnect url = new UrlConnect();

    private View view;
    private ListView lvDanhSachLop;
    private AdapterLvLopHoc adapter;
    private ArrayList<LopHoc> arrLopHoc = new ArrayList<>();
    public static MyApplication app;

    public FragmentLop() {
    }

    public static FragmentLop newIntance() {
        FragmentLop fragmentA = new FragmentLop();
        app = MyApplication.getApp();
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
                        if (app != null) {
                            app.appArrLopHoc.clear();
                            app.appArrLopHoc.addAll(arrLopHoc);
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
