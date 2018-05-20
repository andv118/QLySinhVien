package com.vanando.quanlysinhvien.Lop_Hoc.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vanando.quanlysinhvien.Lop_Hoc.dialog.DialogThoiGian;
import com.vanando.quanlysinhvien.Lop_Hoc.adapter_database.DatabaseManager;
import com.vanando.quanlysinhvien.R;

public class ThemLopHocActivity extends AppCompatActivity {

    private EditText edtTenLopHoc, edtPhongHoc, edtThoiGian, edtThu;
    private Button btnThoiGian, btnHuy, btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop_hoc);
        // anh xa
        initView();
        // set click button
        setClickButton();

    }

    private void setClickButton() {
        // thời gian
        btnThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogThoiGian dialogThoiGian = new DialogThoiGian(ThemLopHocActivity.this);
                dialogThoiGian.setDialog(edtThoiGian, edtThu);
            }
        });

        // hủy
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        // thêm
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // kiem tra editext null
                String tenLopHoc = edtTenLopHoc.getText().toString().trim();
                String thoiGian = edtThoiGian.getText().toString().trim();
                String thu = edtThu.getText().toString().trim();
                String phongHoc = edtPhongHoc.getText().toString().trim();

                if (tenLopHoc.isEmpty() || thoiGian.isEmpty() || thu.isEmpty() || phongHoc.isEmpty()) {
                    Toast.makeText(ThemLopHocActivity.this, "Vui lòng nhập đủ thông tin",
                            Toast.LENGTH_SHORT).show();
                } else {
                    DatabaseManager databaseManager = new DatabaseManager(ThemLopHocActivity.this);
                    databaseManager.raedJSON_INSERT(edtTenLopHoc, edtThoiGian, edtThu, edtPhongHoc);
                }
            }
        });
    }

    private void initView() {

        edtTenLopHoc = (EditText) findViewById(R.id.edtTenLopHoc);
        edtPhongHoc = (EditText) findViewById(R.id.edtPhongHoc);
        edtThoiGian = (EditText) findViewById(R.id.edtThoiGian);
        edtThu = (EditText) findViewById(R.id.edtThu);

        btnThoiGian = (Button) findViewById(R.id.btnThoiGian);

        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnThem = (Button) findViewById(R.id.btnThem);
    }

}
