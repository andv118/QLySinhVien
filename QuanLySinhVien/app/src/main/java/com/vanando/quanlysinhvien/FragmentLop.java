package com.vanando.quanlysinhvien;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Admin on 4/19/2018.
 */

public class FragmentLop extends Fragment {

    public static String ipConfig = "192.168.200.10";

    private String urlGetDatabase = "http://" + ipConfig +"/webserviceQLSV/database.php";

    private View view;
    private ListView lvDanhSachLop;
    private AdapterLvLopHoc adapter;
    private ArrayList<LopHoc> arrLopHoc = new ArrayList<>();

    public FragmentLop() {
    }

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
        // set adapter
        setAdapter();
        readJSON_GET(urlGetDatabase);

        // set click item listview chuyen qua activity sinh vien
        lvDanhSachLop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), position + " Them sinh vien", Toast.LENGTH_SHORT).show();
            }
        });
        onClickView();
        return view;
    }

    private void readJSON_GET(String url) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrLopHoc.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String tenLop = jsonObject.getString("TenLop");
                                String thoiGian = jsonObject.getString("THoiGian");
                                String thu = jsonObject.getString("Thu");
                                String phongHoc = jsonObject.getString("PhongHoc");

                                arrLopHoc.add(new LopHoc(tenLop,thoiGian,thu,phongHoc));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // set lai adapter 1 lan nua
                        setAdapter();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorVolley","loi : \t" + error.toString());
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(jsonArrayRequest);
    }

    private void setAdapter() {
        adapter = new AdapterLvLopHoc(getActivity(),R.layout.custom_lv_dslop,arrLopHoc);
        lvDanhSachLop.setAdapter(adapter);
    }

    private void onClickView() {
    }

    private void initView() {
        lvDanhSachLop = (ListView) view.findViewById(R.id.listviewFragmentLop);
    }
}
