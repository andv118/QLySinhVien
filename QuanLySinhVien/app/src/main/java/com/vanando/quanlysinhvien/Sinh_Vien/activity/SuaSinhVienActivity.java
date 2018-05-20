package com.vanando.quanlysinhvien.Sinh_Vien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.Sinh_Vien.object.SinhVien;
import com.vanando.quanlysinhvien.Sinh_Vien.adapter_database.DatabaseSinhVien;

public class SuaSinhVienActivity extends AppCompatActivity {

    private EditText edtTen_SV, edtMa_SV, edtIdLop_SV;
    private Button btnCancel_SV, btnUpdate_SV;

    private int id_SV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_sinh_vien);
        // anh xa
        initView();
        // nhan doi tuong Sinh Vien
        nhanIntent();
        // set click
        setClickButton();

    }

    private void setClickButton() {

        // cancel
        btnCancel_SV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // update
        btnUpdate_SV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String tenSV = edtTen_SV.getText().toString().trim();
                String maSV = edtMa_SV.getText().toString().trim();
                String idLop_SV = edtIdLop_SV.getText().toString().trim();

                if (tenSV.isEmpty() || maSV.isEmpty() || idLop_SV.isEmpty()) {
                    Toast.makeText(SuaSinhVienActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseSinhVien databaseSinhVien = new DatabaseSinhVien(SuaSinhVienActivity.this);
                    databaseSinhVien.readJSON_UPDATE_SV(id_SV, edtTen_SV, edtMa_SV);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
    }

    private void nhanIntent() {
        Intent intent = getIntent();
        SinhVien sinhVien = (SinhVien) intent.getSerializableExtra("guiSinhVien");
        id_SV = sinhVien.getId();

        edtTen_SV.setText(sinhVien.getTenSv());
        edtMa_SV.setText(sinhVien.getMaSv());
        edtIdLop_SV.setText(sinhVien.getIdLopHoc() + "");
    }

    private void initView() {
        edtTen_SV = (EditText) findViewById(R.id.edtTenSinhVien_Update);
        edtMa_SV = (EditText) findViewById(R.id.edtMaSinhVien_update);
        edtIdLop_SV = (EditText) findViewById(R.id.edtIdLopHoc_update);
        btnCancel_SV = (Button) findViewById(R.id.btnHuySinhVien_Update);
        btnUpdate_SV = (Button) findViewById(R.id.btnThemSinhVien_Update);

    }
}
