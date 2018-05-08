package com.vanando.quanlysinhvien.activity;

import android.app.TimePickerDialog;
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
import com.vanando.quanlysinhvien.LopHoc;
import com.vanando.quanlysinhvien.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class SuaLopHocActivity extends AppCompatActivity {

    private String urlUpdate = "http://" + MainActivity.ipConfig + "/webserviceQLSV/update.php";

    private EditText edtTenLopHocSua, edtPhongHocSua, edtThoiGianSua, edtThuSua;
    private Button btnThoiGianSua, btnThuSua, btnHuySua, btnThemSua;

    private LopHoc lopHoc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_lop_hoc);
        // anh xa
        initView();

        // click button
        setClickButton();

        // dang ky cho conntext menu
        registerForContextMenu(btnThuSua);
        // nhan doi tuong lop hoc
        Intent intent = getIntent();
        lopHoc = (LopHoc) intent.getSerializableExtra("guiLopHoc");

        edtTenLopHocSua.setText(lopHoc.getTenLopHoc());
        edtPhongHocSua.setText(lopHoc.getPhongHoc());
        edtThoiGianSua.setText(lopHoc.getThoiGian());
        edtThuSua.setText(lopHoc.getThuHoc());

    }

    private void setClickButton() {
        // thời gian
        btnThoiGianSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // hien thi time piker dialog
                timePickerDialog();
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
                    updateLopHoc_volley(urlUpdate);
                }
            }
        });
    }

    private void updateLopHoc_volley(String url) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(SuaLopHocActivity.this, "Update thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SuaLopHocActivity.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SuaLopHocActivity.this, "Lỗi update", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SuaLopHocActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("volleyError", "update: \n" +error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> paramsUpdate = new HashMap<>();

                paramsUpdate.put("id_update", String.valueOf(lopHoc.getId()));
                paramsUpdate.put("tenLop_update",edtTenLopHocSua.getText().toString().trim());
                paramsUpdate.put("thoiGian_update",edtThoiGianSua.getText().toString().trim());
                paramsUpdate.put("thu_update",edtThuSua.getText().toString().trim());
                paramsUpdate.put("phongHoc_updaete",edtPhongHocSua.getText().toString().trim());

                return paramsUpdate;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void timePickerDialog() {
        //calender
        final Calendar calendar = Calendar.getInstance();
        int gio = calendar.get(Calendar.HOUR_OF_DAY);
        int phut = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePicker = new TimePickerDialog(SuaLopHocActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // dinh dang gio
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                        calendar.set(0,0,0, hourOfDay, minute);
                        edtThoiGianSua.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                },gio, phut,true);
        timePicker.show();
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
                edtThuSua.setText("Thứ 2");
                break;
            case R.id.itemThu3:
                edtThuSua.setText("Thứ 3");
                break;
            case R.id.itemThu4:
                edtThuSua.setText("Thứ 4");
                break;
            case R.id.itemThu5:
                edtThuSua.setText("Thứ 5");
                break;
            case R.id.itemThu6:
                edtThuSua.setText("Thứ 6");
                break;
            case R.id.itemThu7:
                edtThuSua.setText("Thứ 7");
                break;
            case R.id.itemCN:
                edtThuSua.setText("CN");
                break;
        }

        return super.onContextItemSelected(item);
    }

    private void initView() {

        edtTenLopHocSua = (EditText) findViewById(R.id.edtTenLopHocSua);
        edtPhongHocSua = (EditText) findViewById(R.id.edtPhongHocSua);
        edtThoiGianSua = (EditText) findViewById(R.id.edtThoiGianSua);
        edtThuSua = (EditText) findViewById(R.id.edtThuSua);

        btnThoiGianSua = (Button) findViewById(R.id.btnThoiGianSua);
        btnThuSua = (Button) findViewById(R.id.btnThuSua);

        btnHuySua = (Button) findViewById(R.id.btnHuySua);
        btnThemSua = (Button) findViewById(R.id.btnThemSua);
    }
}
