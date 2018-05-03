package com.vanando.quanlysinhvien;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vanando.quanlysinhvien.activity.SuaLopHocActivity;

import java.util.List;

/**
 * Created by Admin on 5/3/2018.
 */

public class AdapterLvLopHoc extends BaseAdapter {

    private Context context;
    private int layout_Lh;
    private List<LopHoc> listLH;

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
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout_Lh, null);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LopHoc lopHoc = listLH.get(position);
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
                context.startActivity(intent);
            }
        });

        // click vao xoa
        holder.imgXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "xoa", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
