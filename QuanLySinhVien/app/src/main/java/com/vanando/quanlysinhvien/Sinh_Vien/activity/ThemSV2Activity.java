package com.vanando.quanlysinhvien.Sinh_Vien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vanando.quanlysinhvien.Lop_Hoc.object.LopHoc;
import com.vanando.quanlysinhvien.MyApplication;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.Sinh_Vien.adapter_database.DatabaseSinhVien;

import java.util.ArrayList;

import static com.vanando.quanlysinhvien.MyApplication.app;

public class ThemSV2Activity extends AppCompatActivity {

    private Button btnHuySv, btnThemSv;
    private EditText edtTenSv, edtMaSv, edtIdLop;
    private Spinner spinIdLop;
    private ArrayList<Integer> mangIdLop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sv2);
        // anh xa
        initView();
        // spinner
        spinner();
        // set click
        setClick();
    }

    private void spinner() {
        // lay mang id lop
        mangIdLop = new ArrayList<>();
        for (int i = 0; i < app.appArrLopHoc.size(); i++) {
            int j = app.appArrLopHoc.get(i).getId();
            mangIdLop.add(j);
        }
        ArrayAdapter adapterIdLop = new ArrayAdapter(ThemSV2Activity.this, android.R.layout.simple_spinner_item, mangIdLop);
        adapterIdLop.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinIdLop.setAdapter(adapterIdLop);
        // click spinner
        spinIdLop.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                edtIdLop.setText(mangIdLop.get(position) + "");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setClick() {
        // click huy
        btnHuySv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // click them
        btnThemSv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiem tra editext null
                String tenLopHoc = edtTenSv.getText().toString().trim();
                String thoiGian = edtMaSv.getText().toString().trim();
                String idLop = edtIdLop.getText().toString().trim();
                if (tenLopHoc.isEmpty() || thoiGian.isEmpty() || idLop.isEmpty()) {
                    Toast.makeText(ThemSV2Activity.this, "Vui lòng nhập đủ thông tin",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseSinhVien databaseSinhVien = new DatabaseSinhVien(ThemSV2Activity.this);
                    databaseSinhVien.raedJSON_INSERT_SV(edtIdLop, edtTenSv, edtMaSv);
                    Intent intent = new Intent();
                    setResult(RESULT_OK);
                    finish();
                }

            }
        });
    }

    private void initView() {
        btnHuySv = (Button) findViewById(R.id.btnHuySinhVien2);
        btnThemSv = (Button) findViewById(R.id.btnThemSinhVien2);
        edtTenSv = (EditText) findViewById(R.id.edtTenSinhVien_2);
        edtMaSv = (EditText) findViewById(R.id.edtMaSinhVien_2);
        edtIdLop = (EditText) findViewById(R.id.edtIdLopHoc2);
        spinIdLop = (Spinner) findViewById(R.id.spinnerIdLopHoc_2);
    }
}
