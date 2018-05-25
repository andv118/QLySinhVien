package com.vanando.quanlysinhvien.Sinh_Vien.adapter_database;

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

import com.vanando.quanlysinhvien.Diem_So.database.DatabaseDiem;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.Sinh_Vien.activity.DanhSachSinhVien;
import com.vanando.quanlysinhvien.Sinh_Vien.object.SinhVien;
import com.vanando.quanlysinhvien.Sinh_Vien.activity.SuaSinhVienActivity;
import com.vanando.quanlysinhvien.listener.OnDeleteLopHocListener;

import java.util.List;

import static com.vanando.quanlysinhvien.Constants.REQUEST_ADD_SV;
import static com.vanando.quanlysinhvien.MyApplication.app;

public class AdapterLvSinhVien extends BaseAdapter {

    private DanhSachSinhVien context;
    private int layout_SV;
    private List<SinhVien> list_SV;
    private OnDeleteLopHocListener mOnDeleteLopHocListener;

    private int id_SV;

    public AdapterLvSinhVien(Context context, int layout_SV, List<SinhVien> list_SV, OnDeleteLopHocListener mOnDeleteLopHocListener) {
        this.context = (DanhSachSinhVien) context;
        this.layout_SV = layout_SV;
        this.list_SV = list_SV;
        this.mOnDeleteLopHocListener = mOnDeleteLopHocListener;
    }

    @Override
    public int getCount() {
        return list_SV.size();
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
        TextView txtSTT_SV, txtTen_SV, txtMa_SV, txtIdLop_SV;
        ImageView imgMenu_SV;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        final AdapterLvSinhVien.ViewHolder holder;
        if (view == null) {
            holder = new AdapterLvSinhVien.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout_SV, null);
            view.setTag(holder);
        } else {
            holder = (AdapterLvSinhVien.ViewHolder) view.getTag();
        }
        // anh xa
        holder.txtTen_SV = (TextView) view.findViewById(R.id.txtLv_Ten_SV);
        holder.txtMa_SV = (TextView) view.findViewById(R.id.txtLv_Ma_SV);
        holder.txtIdLop_SV = (TextView) view.findViewById(R.id.txtLv_idLop_SV);
        holder.txtSTT_SV = (TextView) view.findViewById(R.id.txtLv_STT_SV);

        holder.imgMenu_SV = (ImageView) view.findViewById(R.id.imgLv_Menu_SV);

        final SinhVien sinhVien = list_SV.get(position);

        // set text
        holder.txtTen_SV.setText(sinhVien.getTenSv());
        holder.txtMa_SV.setText("MSV: " + sinhVien.getMaSv());
        holder.txtIdLop_SV.setText("Id lop: " + sinhVien.getIdLopHoc());
        holder.txtSTT_SV.setText(String.valueOf(position));

        // click imgMenu_SV
        holder.imgMenu_SV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.imgMenu_SV);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu_sinhvien, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.itemPopupSua_SV:
                                Intent intent = new Intent(context, SuaSinhVienActivity.class);
                                intent.putExtra("guiSinhVien", sinhVien);
                                context.startActivityForResult(intent, REQUEST_ADD_SV);
                                break;
                            case R.id.itemPopupXoa_SV:
                                deleteSinhVienDialog(position);
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

    private void deleteSinhVienDialog(final int i) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

        alertDialog.setIcon(R.drawable.ic_notifications_none_black_24dp);
        alertDialog.setTitle("Xác nhận xóa: ");
        alertDialog.setMessage("Bạn có muốn xóa " + list_SV.get(i).getTenSv() + " không?");
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
                id_SV = list_SV.get(i).getId();
                DatabaseSinhVien databaseSinhVien = new DatabaseSinhVien(context);
                databaseSinhVien.readJSON_DELETE_SV(id_SV, mOnDeleteLopHocListener);
            }
        });
        alertDialog.show();
    }

}
