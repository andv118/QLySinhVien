package com.vanando.quanlysinhvien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.vanando.quanlysinhvien.adapter.AdapterLvSinhVien;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.SinhVien;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnectSinhVien;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DanhSachSinhVien extends AppCompatActivity {

    //url Sinh Vien
    UrlConnectSinhVien url = new UrlConnectSinhVien();

    final int defaultValue = 123;
    private String tenLopHoc;
    private int idLopHoc;
    // view tren activity
    private TextView txtTenLopHoc, txtIdLop;
    // floattingButton
    private com.github.clans.fab.FloatingActionButton fabLopHoc, fabSinhVien;
    // adapter
    private ListView lvDanhSach_SV;
    private AdapterLvSinhVien adapter_SV;
    private ArrayList<SinhVien> arr_SV = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien);
        // anh xa
        initView();
        // get Intent
        getIntent_DanhSach_SV();
        // doc Json Database SV
        readJSON_GET_SV();
        // set click view
        setClickItem();
        // fab
        floatingButtonAction();
    }

    public void readJSON_GET_SV() {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url.getUrlGetDatabase_SV(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arr_SV.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int idSV = jsonObject.getInt("ID");
                                String tenSV = jsonObject.getString("TenSinhVien");
                                String maSV = jsonObject.getString("MaSinhVien");
                                int idLopHoc_SV = jsonObject.getInt("IdLop");

                                if (idLopHoc == idLopHoc_SV) {
                                    arr_SV.add(new SinhVien(idSV, tenSV, maSV, idLopHoc_SV));
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            // set adpater
                            setAdapter();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorVolley", "loi : \t" + error.toString());
                        Toast.makeText(DanhSachSinhVien.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(DanhSachSinhVien.this);
        requestQueue.add(jsonArrayRequest);
    }

    private void setAdapter() {
        adapter_SV = new AdapterLvSinhVien(DanhSachSinhVien.this, R.layout.custom_lv_ds_sinhvien, arr_SV, new OnDeleteLopHocListener() {
            @Override
            public void onDeleteSuccess() {
                readJSON_GET_SV();
            }
        });
        lvDanhSach_SV.setAdapter(adapter_SV);
        adapter_SV.notifyDataSetChanged();
    }

    private void setClickItem() {
       lvDanhSach_SV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Toast.makeText(DanhSachSinhVien.this, arr_SV.get(position).getTenSv(), Toast.LENGTH_SHORT).show();
           }
       });
    }

    private void floatingButtonAction() {

        fabLopHoc.hideButtonInMenu(true);
        // fab them sinh vien
        fabSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DanhSachSinhVien.this, ThemSinhVienActivity.class);
                intent1.putExtra("idLopSV", idLopHoc);
                intent1.putExtra("tenLopHoc", tenLopHoc);
                startActivity(intent1);
            }
        });
    }

    private void getIntent_DanhSach_SV() {
        Intent intent = getIntent();
        tenLopHoc = intent.getStringExtra("tenLopHoc");
        idLopHoc = intent.getIntExtra("idLopHoc", defaultValue);
        txtTenLopHoc.setText("Lớp-" + tenLopHoc);
        txtIdLop.setText(idLopHoc + "");
    }

    private void initView() {
        txtTenLopHoc = (TextView) findViewById(R.id.txtDSSVtenLop);
        txtIdLop = (TextView) findViewById(R.id.txtDSSVid);
        fabLopHoc = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabThemLopHoc);
        fabSinhVien = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabThemSinhVien);
        lvDanhSach_SV = (ListView) findViewById(R.id.listviewDanhSachSV);
    }

}
