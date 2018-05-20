package com.vanando.quanlysinhvien.Sinh_Vien.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.Sinh_Vien.adapter_database.DatabaseSinhVien;

import static com.vanando.quanlysinhvien.Constants.DEFAULT_VALUES_PUT_INT;

public class ThemSinhVienActivity extends AppCompatActivity {

    private Button btnHuySv, btnThemSv;
    private EditText edtTenSv, edtMaSv, edtIdLop;
    private int idLopHocSV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_sinh_vien);
        // anh xa
        initView();
        // get intent
        getIntentSV();
        // set click
        setClick();
    }

    private void getIntentSV() {
        Intent intent = getIntent();
        idLopHocSV = intent.getIntExtra("idLopSV", DEFAULT_VALUES_PUT_INT);
        edtIdLop.setText(String.valueOf(idLopHocSV));
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

                if (tenLopHoc.isEmpty() || thoiGian.isEmpty()) {
                    Toast.makeText(ThemSinhVienActivity.this, "Vui lòng nhập đủ thông tin",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseSinhVien databaseSinhVien = new DatabaseSinhVien(ThemSinhVienActivity.this);
                    databaseSinhVien.raedJSON_INSERT_SV(edtIdLop, edtTenSv, edtMaSv);
                    setResult(RESULT_OK);
                    finish();
                }

            }
        });
    }

    private void initView() {
        btnHuySv = (Button) findViewById(R.id.btnHuySinhVien);
        btnThemSv = (Button) findViewById(R.id.btnThemSinhVien);
        edtTenSv = (EditText) findViewById(R.id.edtTenSinhVien);
        edtMaSv = (EditText) findViewById(R.id.edtMaSinhVien);
        edtIdLop = (EditText) findViewById(R.id.edtIdLopHoc);
    }
}
