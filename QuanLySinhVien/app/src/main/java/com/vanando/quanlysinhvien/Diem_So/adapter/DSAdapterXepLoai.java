package com.vanando.quanlysinhvien.Diem_So.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vanando.quanlysinhvien.Diem_So.object.XepLoaiDiem;
import com.vanando.quanlysinhvien.R;

import java.util.List;

public class DSAdapterXepLoai extends BaseAdapter {

    private Context context;
    private int layout;
    private List<XepLoaiDiem> listXL;

    public DSAdapterXepLoai(Context context, int layout, List<XepLoaiDiem> listXL) {
        this.context = context;
        this.layout = layout;
        this.listXL = listXL;
    }

    @Override
    public int getCount() {
        return listXL.size();
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
        TextView txtTenSV, txtDiemTB, txtDiemQD, txtXL;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            view.setTag(holder);
        } else {
            view.getTag();
        }
        // anh xa
        holder.txtTenSV = (TextView) view.findViewById(R.id.DS_txtTen_XepLoai);
        holder.txtDiemTB = (TextView) view.findViewById(R.id.DS_txtDiemTB_XepLoai);
        holder.txtDiemQD = (TextView) view.findViewById(R.id.DS_txtDiemQuyDoi_XepLoai);
        holder.txtXL = (TextView) view.findViewById(R.id.DS_txtXL_XepLoai);

        XepLoaiDiem xepLoai = listXL.get(position);

        // set text
        holder.txtTenSV.setText(xepLoai.getTenSV());
        holder.txtDiemTB.setText(xepLoai.getDiemTB()+ "");
        holder.txtDiemQD.setText(xepLoai.getDiemQuyDoi() + "");
        holder.txtXL.setText(xepLoai.getXepLoai() + "");

        return view;
    }
}
