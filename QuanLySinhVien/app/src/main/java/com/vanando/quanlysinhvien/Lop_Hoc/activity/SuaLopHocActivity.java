package com.vanando.quanlysinhvien.Lop_Hoc.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vanando.quanlysinhvien.Lop_Hoc.dialog.DialogThoiGian;
import com.vanando.quanlysinhvien.Lop_Hoc.adapter_database.DatabaseManager;
import com.vanando.quanlysinhvien.Lop_Hoc.object.LopHoc;
import com.vanando.quanlysinhvien.R;

public class SuaLopHocActivity extends AppCompatActivity {

    private EditText edtTenLopHocSua, edtPhongHocSua, edtThoiGianSua, edtThuSua;
    private Button btnThoiGianSua, btnHuySua, btnThemSua;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_lop_hoc);
        // anh xa
        initView();

        // nhan doi tuong lop hoc
        Intent intent = getIntent();
        LopHoc lopHoc = (LopHoc) intent.getSerializableExtra("guiLopHoc");

        id = lopHoc.getId();
        edtTenLopHocSua.setText(lopHoc.getTenLopHoc());
        edtPhongHocSua.setText(lopHoc.getPhongHoc());
        edtThoiGianSua.setText(lopHoc.getThoiGian());
        edtThuSua.setText(lopHoc.getThuHoc());
        // click button
        setClickButton();
    }

    private void setClickButton() {
        // thời gian
        btnThoiGianSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogThoiGian dialogThoiGian = new DialogThoiGian(SuaLopHocActivity.this);
                dialogThoiGian.setDialog(edtThoiGianSua, edtThuSua);
            }
        });

        // hủy
        btnHuySua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // thêm
        btnThemSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lopHoc = edtTenLopHocSua.getText().toString().trim();
                String thoiGian = edtThoiGianSua.getText().toString().trim();
                String thu = edtThuSua.getText().toString().trim();
                String phongHoc = edtTenLopHocSua.getText().toString().trim();

                if (lopHoc.isEmpty() || thoiGian.isEmpty() || thu.isEmpty() || phongHoc.isEmpty()) {
                    Toast.makeText(SuaLopHocActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseManager databaseManager = new DatabaseManager(SuaLopHocActivity.this);
                    databaseManager.readJSON_UPDATE(id, edtTenLopHocSua, edtThoiGianSua, edtThuSua, edtPhongHocSua);
                }
            }
        });
    }

    private void initView() {
        edtTenLopHocSua = (EditText) findViewById(R.id.edtTenLopHocSua);
        edtPhongHocSua = (EditText) findViewById(R.id.edtPhongHocSua);
        edtThoiGianSua = (EditText) findViewById(R.id.edtThoiGianSua);
        edtThuSua = (EditText) findViewById(R.id.edtThuSua);

        btnThoiGianSua = (Button) findViewById(R.id.btnThoiGianSua);

        btnHuySua = (Button) findViewById(R.id.btnHuySua);
        btnThemSua = (Button) findViewById(R.id.btnThemSua);
    }
}
