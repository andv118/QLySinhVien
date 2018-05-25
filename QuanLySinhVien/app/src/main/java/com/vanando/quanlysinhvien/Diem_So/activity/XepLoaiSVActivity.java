package com.vanando.quanlysinhvien.Diem_So.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.vanando.quanlysinhvien.Diem_So.adapter.DSAdapterXepLoai;
import com.vanando.quanlysinhvien.Diem_So.database.DatabaseDiem;
import com.vanando.quanlysinhvien.Diem_So.object.XepLoaiDiem;
import com.vanando.quanlysinhvien.R;

import java.util.ArrayList;

import static com.vanando.quanlysinhvien.Constants.DEFAULT_VALUES_PUT_INT;
import static com.vanando.quanlysinhvien.MyApplication.app;

public class XepLoaiSVActivity extends AppCompatActivity {
    // adapter
    ListView lvXepLoai;
    DSAdapterXepLoai adapterXL;
    ArrayList<XepLoaiDiem> arrXL;
    // view
    Button btnBack;
    // bien trong class
    DatabaseDiem databaseDiem;
    double diemTB;
    int idLopInten;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xep_loai_sv);
        initView();
        getIntenDSSV();

        app.appArrDiemSo.size();
        for (int i = 0; i < app.appArrDiemSo.size(); i++) {
            int idLop = app.appArrDiemSo.get(i).getIdLop();
            if (idLopInten == idLop) {
                int idDiem = app.appArrDiemSo.get(i).getId();
                String tenSV = app.appArrDiemSo.get(i).getTenSV();
                int diemCC = app.appArrDiemSo.get(i).getDiemCC();
                int diemKT = app.appArrDiemSo.get(i).getDiemKT();
                int diemTH = app.appArrDiemSo.get(i).getDiemTH();
                int diemKTM = app.appArrDiemSo.get(i).getDiemKTM();

                diemXL(idDiem, diemCC, diemKT, diemTH, diemKTM, tenSV);
            }
        }
           // update Xep Loai
//        for (int j =0; j< arrXL.size(); j++) {
//            int idDiemUpdate = arrXL.get(j).getIdDiem();
//            String XL = arrXL.get(j).getXepLoai();
//            databaseDiem.readJSON_UPDATE_XepLoai(idDiemUpdate, XL);
//        }

        setAdaper();
        setClickView();
    }

    // tinh diem trung binh va xep loai
    private ArrayList<XepLoaiDiem> diemXL(int idDiem, int diemCC, int diemKT, int diemTH, int diemKTM, String tenSV) {

        diemTB = (diemCC * 10 + diemKT * 10 + diemTH * 10 + diemKTM * 60) / 100;
        if (diemTB < 4) {
            // f
            arrXL.add(new XepLoaiDiem(idDiem, diemTB, "F", "Kém", tenSV));
        }
        if (diemTB >= 4 && diemTB <= 5.4) {
            //d
            arrXL.add(new XepLoaiDiem(idDiem, diemTB, "D", "TB yếu", tenSV));
        }
        if (diemTB >= 5.5 && diemTB <= 6.9) {
            //c
            arrXL.add(new XepLoaiDiem(idDiem, diemTB, "C", "TB", tenSV));
        }
        if (diemTB >= 7 && diemTB <= 8.4) {
            //b
            arrXL.add(new XepLoaiDiem(idDiem, diemTB, "B", "Khá", tenSV));
        }
        if (diemTB >= 8.5 && diemTB <= 10) {
            //a
            arrXL.add(new XepLoaiDiem(idDiem, diemTB, "A", "Giỏi", tenSV));
        }

        return arrXL;
    }

    private void setAdaper() {
        adapterXL = new DSAdapterXepLoai(XepLoaiSVActivity.this, R.layout.custom_ds_adapter_xeploai, arrXL);
        lvXepLoai.setAdapter(adapterXL);
        adapterXL.notifyDataSetChanged();
    }

    private void setClickView() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntenDSSV() {
        Intent intent = getIntent();
        idLopInten = intent.getIntExtra("optionmenu_idLophoc_ds_xl", DEFAULT_VALUES_PUT_INT);
    }

    private void initView() {
        lvXepLoai = (ListView) findViewById(R.id.DS_listView_XepLoai);
        btnBack = (Button) findViewById(R.id.DS_btnHuy_XepLoai);

        arrXL = new ArrayList<>();
        databaseDiem = new DatabaseDiem(XepLoaiSVActivity.this);
    }
}
