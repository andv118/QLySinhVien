package com.vanando.quanlysinhvien.Sinh_Vien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.vanando.quanlysinhvien.Diem_So.database.DatabaseDiem;
import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.Sinh_Vien.adapter_database.AdapterDiemDanh;
import com.vanando.quanlysinhvien.R;

import java.util.ArrayList;

import static com.vanando.quanlysinhvien.Constants.DEFAULT_VALUES_PUT_INT;
import static com.vanando.quanlysinhvien.MyApplication.app;

public class DiemDanhActivity extends AppCompatActivity {

    // adapter
    ListView lvDiemDanh;
    AdapterDiemDanh adapter;
    ArrayList<String> arrTenSV;
    //view
    Button btnCancel, btnOk;
    // bien
    private int idLopHoc;
    DatabaseDiem databaseDiem;

    int diemCC = 10;

    ArrayList<DiemSo> arrDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diem_danh);
        // anh xa
        initView();
        getIntenDSSV();
        mangTenSV(arrTenSV);

        arrDiem = app.appArrDiemSo;

        setAdapter();
        setClickView();
    }

    private ArrayList<String> mangTenSV(ArrayList<String> arrTenSV1) {
        for (int  i = 0; i< app.appArrSinhVien.size(); i++) {
            if (idLopHoc == app.appArrSinhVien.get(i).getIdLopHoc()) {
                String ten = app.appArrSinhVien.get(i).getTenSv();
                arrTenSV1.add(ten);
            }
        }
        return arrTenSV1;
    }

    private void setClickView() {
        //ok
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DiemDanhActivity.this, "ok", Toast.LENGTH_SHORT).show();
//                databaseDiem.readJSON_UPDATE_DiemCC(diemCC);
                setResult(RESULT_OK);
                finish();
            }
        });
        // cancel
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntenDSSV() {
        Intent intent = getIntent();
        idLopHoc = intent.getIntExtra("DanhSachSV_idLopHoc_DiemDanh", DEFAULT_VALUES_PUT_INT);
    }

    private void setAdapter() {
        adapter = new AdapterDiemDanh(DiemDanhActivity.this, R.layout.custom_sv_adapter_diemdanh, arrTenSV, diemCC);
        lvDiemDanh.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initView() {
        lvDiemDanh = (ListView) findViewById(R.id.SV_listview_DiemDanh);
        btnCancel = (Button) findViewById(R.id.SV_btnCancel_DiemDanh);
        btnOk = (Button) findViewById(R.id.SV_btnOK_DiemDanh);

        arrTenSV = new ArrayList<>();
        databaseDiem = new DatabaseDiem(DiemDanhActivity.this);
        arrDiem = new ArrayList<>();
    }
}
