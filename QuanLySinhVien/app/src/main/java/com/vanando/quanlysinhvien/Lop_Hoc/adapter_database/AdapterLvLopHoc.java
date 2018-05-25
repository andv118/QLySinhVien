package com.vanando.quanlysinhvien.Lop_Hoc.adapter_database;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.vanando.quanlysinhvien.Lop_Hoc.object.LopHoc;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.Lop_Hoc.activity.SuaLopHocActivity;
import com.vanando.quanlysinhvien.Lop_Hoc.adapter_database.DatabaseManager;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;
import com.vanando.quanlysinhvien.urlconnect.UrlConnect;

import java.util.List;

/**
 * Created by Admin on 5/3/2018.
 */

public class AdapterLvLopHoc extends BaseAdapter {

    // url
    UrlConnect url = new UrlConnect();

    private Context context;
    private int layout_Lh;
    private List<LopHoc> listLH;

    private int id;
    OnDeleteLopHocListener mOnDeleteLopHocListener;

    public AdapterLvLopHoc(Context context, int layout_Lh, List<LopHoc> listLH, OnDeleteLopHocListener mOnDeleteLopHocListener) {
        this.context = context;
        this.layout_Lh = layout_Lh;
        this.listLH = listLH;
        this.mOnDeleteLopHocListener = mOnDeleteLopHocListener;
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
        ImageView imgMenu;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
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

        holder.imgMenu = (ImageView) view.findViewById(R.id.imgLvMenu);

        // set text
        holder.txtTenLH.setText("MH: " + lopHoc.getTenLopHoc());
        holder.txtThoiGian.setText("TG: " + lopHoc.getThoiGian() + " - " + lopHoc.getThuHoc());
        holder.txtPhongHoc.setText("PH: " + lopHoc.getPhongHoc());
        holder.txtSTT.setText(String.valueOf(position));

        // click imgMenu
        holder.imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imgMenu);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itemPopupXoa:
                                deleteLopHocDialog(position);
                                break;
                            case R.id.itemPopupSua:
                                Intent intent = new Intent(context, SuaLopHocActivity.class);
                                intent.putExtra("guiLopHoc", lopHoc);
                                context.startActivity(intent);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        return view;
    }

    private void deleteLopHocDialog(final int i) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setIcon(R.drawable.ic_notifications_none_black_24dp);
        alertDialog.setTitle("Xác nhận xóa: ");
        alertDialog.setMessage("Bạn có muốn xóa " + listLH.get(i).getTenLopHoc() + " không?");
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
                DatabaseManager database = new DatabaseManager(context);
                database.readJSON_DELETE(id, mOnDeleteLopHocListener);
            }
        });
        alertDialog.show();
    }

}
