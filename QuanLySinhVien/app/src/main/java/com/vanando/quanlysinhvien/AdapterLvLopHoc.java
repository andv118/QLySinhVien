package com.vanando.quanlysinhvien;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vanando.quanlysinhvien.activity.MainActivity;
import com.vanando.quanlysinhvien.activity.SuaLopHocActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Admin on 5/3/2018.
 */

public class AdapterLvLopHoc extends BaseAdapter {

    private String urlDelete = "http://" + MainActivity.ipConfig + "/webserviceQLSV/delete.php";

    private Context context;
    private int layout_Lh;
    private List<LopHoc> listLH;

//    private String name; // gán tên cho lớp học trong dialog xóa
//    private int idDelete; // gán id cho lớp de lay ra truyen vao method

    public AdapterLvLopHoc(Context context, int layout_Lh, List<LopHoc> listLH) {
        this.context = context;
        this.layout_Lh = layout_Lh;
        this.listLH = listLH;
    }

    @Override
    public int getCount() {
        return listLH.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    class ViewHolder {
        TextView txtSTT, txtTenLH, txtThoiGian, txtPhongHoc;
        ImageView imgXoa, imgSua;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout_Lh, null);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final LopHoc lopHoc = listLH.get(position);

        // anh xa
        holder.txtTenLH = (TextView) view.findViewById(R.id.txtLvTenMonHoc);
        holder.txtThoiGian = (TextView) view.findViewById(R.id.txtLvThoiGian);
        holder.txtPhongHoc = (TextView) view.findViewById(R.id.txtLvPhongHoc);
        holder.txtSTT = (TextView) view.findViewById(R.id.txtLvSTT);

        holder.imgXoa = (ImageView) view.findViewById(R.id.imgLvXoa);
        holder.imgSua = (ImageView) view.findViewById(R.id.imgLvSua);

        // set text
        holder.txtTenLH.setText("MH: " + lopHoc.getTenLopHoc());
        holder.txtThoiGian.setText("TG: " + lopHoc.getThoiGian() + " - " + lopHoc.getThuHoc());
        holder.txtPhongHoc.setText("PH: " + lopHoc.getPhongHoc());
        holder.txtSTT.setText(String.valueOf(position));

        // ckick vao sua
        holder.imgSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, SuaLopHocActivity.class);
                intent.putExtra("guiLopHoc", lopHoc);
                context.startActivity(intent);
            }
        });

        // click vao xoa
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLopHocDialog(position);
            }
        });

        return view;
    }

    private void deleteLopHocDialog(final int i) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setTitle("Xác nhận xóa: ");
        alertDialog.setMessage("Bạn có muốn xóa " + listLH.get(i).getTenLopHoc() + " không?");
        alertDialog.setIcon(R.drawable.ic_notifications_none_black_24dp);
        // khong
        alertDialog.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        // co
        alertDialog.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteLopHoc_volley(urlDelete,i);
            }
        });
        alertDialog.show();
    }

    private void deleteLopHoc_volley(String url, final int i) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
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
                paramsDelete.put("id_delete", String.valueOf(listLH.get(i).getId()));
                return paramsDelete;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);

    }

}
