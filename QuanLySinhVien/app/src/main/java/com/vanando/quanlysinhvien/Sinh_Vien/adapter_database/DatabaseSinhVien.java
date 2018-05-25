package com.vanando.quanlysinhvien.Sinh_Vien.adapter_database;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.Lop_Hoc.activity.MainActivity;
import com.vanando.quanlysinhvien.Sinh_Vien.object.SinhVien;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnectSinhVien;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.vanando.quanlysinhvien.MyApplication.app;

public class DatabaseSinhVien {

    // url connect sinh vien
    private final UrlConnectSinhVien url = new UrlConnectSinhVien();
    private Context context;

    public DatabaseSinhVien(Context context) {
        this.context = context;
    }

    public void readJSON_GET_SV(final ArrayList<SinhVien> arrSV) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url.getUrlGetDatabase_SV(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrSV.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int id = jsonObject.getInt("ID");
                                int idLop = jsonObject.getInt("IdLop");
                                String tenSV = jsonObject.getString("TenSinhVien");
                                String maSV = jsonObject.getString("MaSinhVien");

                                arrSV.add(new SinhVien(id,tenSV,maSV,idLop));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (app != null) {
                            app.appArrSinhVien.clear();
                            app.appArrSinhVien.addAll(arrSV);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorVolley", "loi : \t" + error.toString());
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }

    public void raedJSON_INSERT_SV(final EditText edtIdLop, final EditText edtTenSV, final EditText edtMaSV ) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlInsert_SV(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Thêm thành công sinh viên", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Lỗi thêm", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString() + "", Toast.LENGTH_SHORT).show();
                        Log.d("volleyError", "loi insert: \n" + error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Map dinh dang cho kieu tra ve
                Map<String, String> params = new HashMap<>();
                // put len param
                params.put("idLopInsert",edtIdLop.getText().toString().trim());
                params.put("tenSinhVienInsert",edtTenSV.getText().toString().trim());
                params.put("maSinhVienInsert",edtMaSV.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_UPDATE_SV(final int id, final EditText edtTen_SV, final EditText edtMa_SV) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlUpdate_SV(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Lỗi update", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("volleyError", "update: \n" +error.toString());
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> paramsUpdate = new HashMap<>();

                paramsUpdate.put("id_update_SV", String.valueOf(id));
                paramsUpdate.put("ten_update_SV",edtTen_SV.getText().toString().trim());
                paramsUpdate.put("ma_update_SV",edtMa_SV.getText().toString().trim());

                return paramsUpdate;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_DELETE_SV(final int id, final OnDeleteLopHocListener mOnDeleteLopHocListener) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlDelete_SV(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            mOnDeleteLopHocListener.onDeleteSuccess();
                        } else {
                            Toast.makeText(context, "Lỗi delete", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("volleyError","delete: \n" +error.toString());
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> paramsDelete = new HashMap<>();
                paramsDelete.put("id_delete_SV", String.valueOf(id));
                return paramsDelete;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

}
