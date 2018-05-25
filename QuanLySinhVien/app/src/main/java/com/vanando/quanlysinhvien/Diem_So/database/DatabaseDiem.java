package com.vanando.quanlysinhvien.Diem_So.database;

import android.content.Context;
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
import com.vanando.quanlysinhvien.Lop_Hoc.object.LopHoc;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnectDiem;
import com.vanando.quanlysinhvien.urlconnect.UrlConnectSinhVien;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.vanando.quanlysinhvien.MyApplication.app;

public class DatabaseDiem {
    private final UrlConnectDiem url = new UrlConnectDiem();
    private Context context;

    public DatabaseDiem(Context context) {
        this.context = context;
    }

    public void readJSON_GET_DiemSo(final ArrayList<DiemSo> arrDiemSo) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url.getUrlGetDatabase_Diem(), null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrDiemSo.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int idDiem = jsonObject.getInt("ID");
                                int idLop = jsonObject.getInt("IdLop");
                                int idSV = jsonObject.getInt("IdSV");
                                String tenSV = jsonObject.getString("TenSV");
                                String maSV = jsonObject.getString("MaSV");
                                int diemCC = jsonObject.getInt("Diem_cc");
                                int diemKT = jsonObject.getInt("Diem_kt");
                                int diemTH = jsonObject.getInt("Diem_th");
                                int diemKTM = jsonObject.getInt("Diem_ktMon");
                                String xepLoai = jsonObject.getString("Diem_xepLoai");

                                arrDiemSo.add(new DiemSo(idDiem, idLop, idSV, tenSV, maSV, diemCC, diemKT, diemTH, diemKTM, xepLoai));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        if (app != null) {
                            app.appArrDiemSo.clear();
                            app.appArrDiemSo.addAll(arrDiemSo);
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

    public void raedJSON_INSERT_Diem(final int idLop, final int idSV, final String tenSV, final String maSV, final EditText edtDiemCC, final EditText edtDiemKT,
                                     final EditText edtDiemTH, final EditText edtDiemKTM) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlInsert_Diem(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Thêm điểm thành công", Toast.LENGTH_SHORT).show();
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
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Map dinh dang cho kieu tra ve
                Map<String, String> params = new HashMap<>();
                // put len param
                params.put("idLopInsert", String.valueOf(idLop));
                params.put("idSinhVienInsert", String.valueOf(idSV));
                params.put("tenSVDiemInsert", String.valueOf(tenSV));
                params.put("maSVDiemInsert", String.valueOf(maSV));
                params.put("diemCCInsert", edtDiemCC.getText().toString().trim());
                params.put("diemKTInsert", edtDiemKT.getText().toString().trim());
                params.put("diemTHInsert", edtDiemTH.getText().toString().trim());
                params.put("diemKTMInsert", edtDiemKTM.getText().toString().trim());
                params.put("diemXLInsert", "A");
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_UPDATE_DiemSo(final int id_Diem, final EditText edtDiemCC, final EditText edtDiemKT,
                                   final EditText edtDiemTH, final EditText edtDiemKTM) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlUpdate_Diem(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Update điểm thành công", Toast.LENGTH_SHORT).show();
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

                paramsUpdate.put("IdDiem_update", String.valueOf(id_Diem));
                paramsUpdate.put("DiemCC_update", edtDiemCC.getText().toString().trim());
                paramsUpdate.put("DiemKT_update", edtDiemKT.getText().toString().trim());
                paramsUpdate.put("DiemTH_update", edtDiemTH.getText().toString());
                paramsUpdate.put("DiemKTM_update", edtDiemKTM.getText().toString());

                return paramsUpdate;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_UPDATE_XepLoai(final int id_Diem, final String XL) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlUpdate_DiemXL(),
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

                paramsUpdate.put("IdDiem_update", String.valueOf(id_Diem));
                paramsUpdate.put("DiemXL_update", String.valueOf(XL));


                return paramsUpdate;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_DELETE_DiemSo(final int idDiem, final OnDeleteLopHocListener mOnDeleteLopHocListener) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlDelete_Diem(),
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
                paramsDelete.put("id_delete_Diem", String.valueOf(idDiem));
                return paramsDelete;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

    public void readJSON_UPDATE_DiemCC(final int id_Diem, final int diemCC) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url.getUrlUpdate_Diem(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Update điểm cc thành công", Toast.LENGTH_SHORT).show();
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

                paramsUpdate.put("IdDiem_update", String.valueOf(id_Diem));
                paramsUpdate.put("DiemCC_update", String.valueOf(diemCC));

                return paramsUpdate;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

}
