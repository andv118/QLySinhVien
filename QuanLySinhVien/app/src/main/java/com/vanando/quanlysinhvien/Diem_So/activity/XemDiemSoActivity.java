package com.vanando.quanlysinhvien.Diem_So.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.vanando.quanlysinhvien.Diem_So.adapter.DSAdapterXemDiem;
import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.R;

import java.util.ArrayList;

import static com.vanando.quanlysinhvien.Constants.DEFAULT_VALUES_PUT_INT;
import static com.vanando.quanlysinhvien.MyApplication.app;

public class XemDiemSoActivity extends AppCompatActivity {
    // adapter
    ListView lvDiem;
    DSAdapterXemDiem adapter;
    ArrayList<DiemSo> arrDiemSV;
    // view
    Button btnBack;
    // bien trong class
    int idLop_menu; // cua optionmenu gui sang

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_diem_so);
        initView();
        // getIntent
        getIntentMenu();
        // lay ra gia tri arrDiemSo
        for (int i = 0; i < app.appArrDiemSo.size(); i++) {
            int idLop = app.appArrDiemSo.get(i).getIdLop();
            if (idLop == idLop_menu) {
                String ten = app.appArrDiemSo.get(i).getTenSV();
                int diemCC = app.appArrDiemSo.get(i).getDiemCC();
                int diemKT = app.appArrDiemSo.get(i).getDiemKT();
                int diemTH = app.appArrDiemSo.get(i).getDiemTH();
                int diemKTM = app.appArrDiemSo.get(i).getDiemKTM();

                arrDiemSV.add(new DiemSo(ten, diemCC, diemKT, diemTH, diemKTM));
            }
        }

        // setadapter
        setAdapter();
        // click
        setClickView();
    }

    private void setClickView() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setAdapter() {
        adapter = new DSAdapterXemDiem(XemDiemSoActivity.this, R.layout.custom_ds_adapter_diemso, arrDiemSV);
        lvDiem.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getIntentMenu() {
        Intent intent = getIntent();
        idLop_menu = intent.getIntExtra("optionmenu_idLophoc_ds", DEFAULT_VALUES_PUT_INT);
    }

    private void initView() {
        lvDiem = (ListView) findViewById(R.id.DS_listView_XemDiem);
        btnBack = (Button) findViewById(R.id.DS_btnHuy_XemDiem);

        arrDiemSV = new ArrayList<>();
    }
}
