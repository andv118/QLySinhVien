package com.vanando.quanlysinhvien.database;

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
import com.vanando.quanlysinhvien.LopHoc;
import com.vanando.quanlysinhvien.activity.MainActivity;
import com.vanando.quanlysinhvien.activity.SuaLopHocActivity;
import com.vanando.quanlysinhvien.activity.ThemLopHocActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 5/7/2018.
 */

public class DatabaseManager {

    // ko được sửa url
    private String urlGetDatabase = "http://pritplust.000webhostapp.com/database.php";
    private String urlInsert = "http://pritplust.000webhostapp.com/insert.php";
    private String urlUpdate = "http://pritplust.000webhostapp.com/update.php";
    private String urlDelete = "http://pritplust.000webhostapp.com/delete.php";

    private Context context;

    public DatabaseManager(Context context) {
        this.context = context;
    }

    public void readJSON_GET(final ArrayList<LopHoc> arrLopHoc) {

        final JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlGetDatabase, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arrLopHoc.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                int idLop = jsonObject.getInt("ID");
                                String tenLop = jsonObject.getString("TenLop");
                                String thoiGian = jsonObject.getString("THoiGian");
                                String thu = jsonObject.getString("Thu");
                                String phongHoc = jsonObject.getString("PhongHoc");

                                arrLopHoc.add(new LopHoc(idLop,tenLop,thoiGian,thu,phongHoc));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        // set lai adapter 1 lan nua
//                        setAdapter();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("errorVolley","loi : \t" + error.toString());
                        Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(jsonArrayRequest);
    }

    public void raedJSON_INSERT(final EditText edtTen, final EditText edtTg, final EditText edtThu, final EditText edtPhong) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsert,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            context.startActivity(new Intent(context, MainActivity.class));
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
                params.put("tenLop_Insert",edtTen.getText().toString().trim());
                params.put("thoiGian_Insert",edtTg.getText().toString().trim());
                params.put("thu_Insert",edtThu.getText().toString().trim());
                params.put("phongHoc_Insert",edtPhong.getText().toString().trim());
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_UPDATE(final int id, final EditText edtTen, final EditText edtTg, final EditText edtThu, final EditText edtPhong) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlUpdate,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")) {
                            Toast.makeText(context, "Update thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context, MainActivity.class);
                            context.startActivity(intent);
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

                paramsUpdate.put("id_update", String.valueOf(id));
                paramsUpdate.put("tenLop_update",edtTen.getText().toString().trim());
                paramsUpdate.put("thoiGian_update",edtTg.getText().toString().trim());
                paramsUpdate.put("thu_update",edtThu.getText().toString().trim());
                paramsUpdate.put("phongHoc_updaete",edtPhong.getText().toString().trim());

                return paramsUpdate;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }

    public void readJSON_DELETE(final int id) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, urlDelete,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.toString().trim().equals("success")){
                            Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
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
                paramsDelete.put("id_delete", String.valueOf(id));
                return paramsDelete;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }


}
