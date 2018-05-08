package com.vanando.quanlysinhvien.activity;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vanando.quanlysinhvien.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ThemLopHocActivity extends AppCompatActivity {

    private String urlInsert = "http://"+ MainActivity.ipConfig +"/webserviceQLSV/insert.php";

    private EditText edtTenLopHoc, edtPhongHoc, edtThoiGian, edtThu;
    private Button btnThoiGian, btnThu, btnHuy, btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_lop_hoc);
        // anh xa
        initView();
        // set click button
        setClickButton();

        // long click btn Thu
        // đăng ký view với contextMenu
        registerForContextMenu(btnThu);
    }

    private void themLopHoc_Volley(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(ThemLopHocActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ThemLopHocActivity.this, MainActivity.class));
                        } else {
                            Toast.makeText(ThemLopHocActivity.this, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ThemLopHocActivity.this, error.toString() + "", Toast.LENGTH_SHORT).show();
                        Log.d("volleyError", "loi insert: \n" + error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Map dinh dang cho kieu tra ve
                Map<String, String> params = new HashMap<>();
                // put len param
                params.put("tenLop_Insert",edtTenLopHoc.getText().toString().trim());
                params.put("thoiGian_Insert",edtThoiGian.getText().toString().trim());
                params.put("thu_Insert",edtThu.getText().toString().trim());
                params.put("phongHoc_Insert",edtPhongHoc.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(ThemLopHocActivity.this);
        requestQueue.add(stringRequest);
    }

    private void setClickButton() {
        // thời gian
        btnThoiGian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hien thi time piker dialog
               timePickerDialog();
            }
        });
//        // thứ
//        btnThu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

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
                    themLopHoc_Volley(urlInsert);
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
        btnThu = (Button) findViewById(R.id.btnThu);

        btnHuy = (Button) findViewById(R.id.btnHuy);
        btnThem = (Button) findViewById(R.id.btnThem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu_thu, menu);
        menu.setHeaderTitle("Chọn thứ:");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.itemThu2:
                edtThu.setText("Thứ 2");
                break;
            case R.id.itemThu3:
                edtThu.setText("Thứ 3");
                break;
            case R.id.itemThu4:
                edtThu.setText("Thứ 4");
                break;
            case R.id.itemThu5:
                edtThu.setText("Thứ 5");
                break;
            case R.id.itemThu6:
                edtThu.setText("Thứ 6");
                break;
            case R.id.itemThu7:
                edtThu.setText("Thứ 7");
                break;
            case R.id.itemCN:
                edtThu.setText("CN");
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void timePickerDialog() {
        //calender
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(ThemLopHocActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // dinh dang gio
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        calendar.set(0,0,0, hourOfDay, minute);
                        edtThoiGian.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },gio, phut,true);
        timePicker.show();
    }
}
