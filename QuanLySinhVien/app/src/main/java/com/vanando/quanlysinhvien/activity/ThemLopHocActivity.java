package com.vanando.quanlysinhvien.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.vanando.quanlysinhvien.R;

public class ThemLopHocActivity extends AppCompatActivity {

    private Context context;
    private EditText edtTenLopHoc, edtPhongHoc;
    private Button btnThoiGian, btnThu, btnHuy, btnThem;

    public ThemLopHocActivity() {
    }

    public ThemLopHocActivity(Context context) {
        this.context = context;
    }

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

//                TimePicker timePicker = new TimePicker(context);
////                timePicker.
            }
        });
        // thứ
        btnThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ThemLopHocActivity.this, "thứ", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(ThemLopHocActivity.this, "thêm", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {

        edtTenLopHoc = (EditText) findViewById(R.id.edtTenLopHoc);
        edtPhongHoc = (EditText) findViewById(R.id.edtPhongHoc);

        btnThoiGian = (Button) findViewById(R.id.btnThoiGian);
        btnThu = (Button) findViewById(R.id.btnThu);

        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnThem = (Button) findViewById(R.id.btnThem);
    }
}
