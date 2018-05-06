package com.vanando.quanlysinhvien;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.vanando.quanlysinhvien.activity.SuaLopHocActivity;
import com.vanando.quanlysinhvien.database.DatabaseManager;

import java.util.List;

/**
 * Created by Admin on 5/3/2018.
 */

public class AdapterLvLopHoc extends BaseAdapter {

    private Context context;
    private int layout_Lh;
    private List<LopHoc> listLH;

    private int id;

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
                id = listLH.get(i).getId();
                DatabaseManager databaseManager = new DatabaseManager(context);
                databaseManager.readJSON_DELETE(id);

            }
        });
        alertDialog.show();
    }

}
