package com.vanando.quanlysinhvien.Sinh_Vien.activity;

import android.content.Intent;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
import com.vanando.quanlysinhvien.Diem_So.activity.ThemDiemSoActivity;
import com.vanando.quanlysinhvien.Diem_So.activity.XemDiemSoActivity;
import com.vanando.quanlysinhvien.Diem_So.activity.XepLoaiSVActivity;
import com.vanando.quanlysinhvien.Diem_So.database.DatabaseDiem;
import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.MyApplication;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.Sinh_Vien.adapter_database.AdapterLvSinhVien;
import com.vanando.quanlysinhvien.Sinh_Vien.adapter_database.DatabaseSinhVien;
import com.vanando.quanlysinhvien.Sinh_Vien.object.SinhVien;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnectSinhVien;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static com.vanando.quanlysinhvien.Constants.DEFAULT_VALUES_PUT_INT;
import static com.vanando.quanlysinhvien.Constants.REQUEST_ADD_DIEMSO;
import static com.vanando.quanlysinhvien.Constants.REQUEST_ADD_SV;
import static com.vanando.quanlysinhvien.Constants.REQUEST_DIEMDANH;
import static com.vanando.quanlysinhvien.MyApplication.app;
import static com.vanando.quanlysinhvien.MyApplication.getApp;

public class DanhSachSinhVien extends AppCompatActivity {

    //url Sinh Vien
    UrlConnectSinhVien url = new UrlConnectSinhVien();
    // view tren activity
    private TextView txtTenLopHoc;
    // floattingButton
    private com.github.clans.fab.FloatingActionButton fabLopHoc, fabSinhVien, fabDiemDanh;
    // adapter
    private ListView lvDanhSach_SV;
    private AdapterLvSinhVien adapter_SV;
    private ArrayList<SinhVien> arr_SV;
    // bien dung trong class
    private String tenLopHoc;
    private int idLopHoc;
    // database
    DatabaseSinhVien databaseSinhVien;
    DatabaseDiem databaseDiem;
    private ArrayList<DiemSo> arrDiemSoAdd;
    private ArrayList<SinhVien> arrSvAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_sinh_vien);
        // toolbar
        initToolbar();
        // anh xa
        initView();
        // get Intent
        getIntent_MainActivity();
        // doc Json Database SV
        readJSON_GET_SV();
        // set click view
        setClickItem();
        // fab
        floatingButtonAction();


        // add arrSV static
        addArrSvStatic();
        // add arrDiemSo static
        addArrDsStatic();
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
                Intent intent = new Intent(DanhSachSinhVien.this, ThemDiemSoActivity.class);
                    String tenSV = arr_SV.get(position).getTenSv();
                    String maSV = arr_SV.get(position).getMaSv();
                    int idSVPut = arr_SV.get(position).getId();

                    intent.putExtra("DanhSachSV_TenSV", tenSV);
                    intent.putExtra("DanhSachSV_MaSV", maSV);
                    intent.putExtra("DanhSachSV_IdSV", idSVPut);
                    intent.putExtra("DanhSachSV_IdLopHoc", idLopHoc);

                    DiemSo diemSo;
                    int idSVDiemSo;

                for (int i = 0; i < arrDiemSoAdd.size(); i++) {
                    idSVDiemSo = arrDiemSoAdd.get(i).getIdSV();
                    if (idSVDiemSo == idSVPut) {
                        diemSo = arrDiemSoAdd.get(i);
                        intent.putExtra("DanhSachSV_ObjectDiemSo", diemSo);
                    }
                }
                startActivityForResult(intent, REQUEST_ADD_DIEMSO);
            }
        });
    }

    private void floatingButtonAction() {
        fabLopHoc.setVisibility(View.GONE);
        fabSinhVien.setVisibility(View.VISIBLE);
        fabDiemDanh.setVisibility(View.VISIBLE);
        // fab them sinh vien
        fabSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(DanhSachSinhVien.this, ThemSinhVienActivity.class);
                intent1.putExtra("idLopSV", idLopHoc);
                intent1.putExtra("tenLopHoc", tenLopHoc);
                startActivityForResult(intent1, REQUEST_ADD_SV);
            }
        });

        fabDiemDanh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(DanhSachSinhVien.this, DiemDanhActivity.class);
                intent2.putExtra("DanhSachSV_idLopHoc_DiemDanh", idLopHoc);
                startActivityForResult(intent2, REQUEST_DIEMDANH);
            }
        });
    }

    // hứng kq trả về
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // THÊM THÀNH CÔNG THÌ ĐỌC LẠI JSON SV 1 LẦN NỮA
        if (requestCode == REQUEST_ADD_SV) {
            if (resultCode == RESULT_OK) {
                //refresh adapter
                readJSON_GET_SV();
            }
        }
        if (requestCode == REQUEST_ADD_DIEMSO) {
            if (resultCode == RESULT_OK) {
                //refresh adapter
                addArrDsStatic();
            }
        }
    }

    private void getIntent_MainActivity() {
        Intent intent = getIntent();
        tenLopHoc = intent.getStringExtra("tenLopHoc");
        idLopHoc = intent.getIntExtra("idLopHoc", DEFAULT_VALUES_PUT_INT);
        txtTenLopHoc.setText("Lớp-" + tenLopHoc);
    }
    // menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_sv, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_SV_DanhSachDiem:
                Intent intent = new Intent(DanhSachSinhVien.this, XemDiemSoActivity.class);
                intent.putExtra("optionmenu_idLophoc_ds", idLopHoc);
                startActivity(intent);
                break;
            case R.id.menu_SV_XepLoai:
                Intent intent1 = new Intent(DanhSachSinhVien.this, XepLoaiSVActivity.class);
                intent1.putExtra("optionmenu_idLophoc_ds_xl", idLopHoc);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    //toolBar
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.SV_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Danh sách sinh viên");
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }

    private void initView() {
        txtTenLopHoc = (TextView) findViewById(R.id.txtDSSVtenLop);
        fabLopHoc = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabThemLopHoc);
        fabSinhVien = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabThemSinhVien);
        fabDiemDanh = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabDiemDanh);
        lvDanhSach_SV = (ListView) findViewById(R.id.listviewDanhSachSV);

        arr_SV = new ArrayList<>();
        databaseSinhVien = new DatabaseSinhVien(DanhSachSinhVien.this);
        databaseDiem = new DatabaseDiem(DanhSachSinhVien.this);
        arrSvAdd = new ArrayList<>();
        arrDiemSoAdd = new ArrayList<>();
    }


    private void addArrSvStatic() {
        databaseSinhVien.readJSON_GET_SV(arrSvAdd);
    }

    public void addArrDsStatic() {
        databaseDiem.readJSON_GET_DiemSo(arrDiemSoAdd);
    }

}
