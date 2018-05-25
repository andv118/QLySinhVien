package com.vanando.quanlysinhvien.Diem_So.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.R;

import java.util.List;

public class DSAdapterXemDiem extends BaseAdapter {

    private Context context;
    private int layout;
    private List<DiemSo> listSV;

    public DSAdapterXemDiem(Context context, int layout, List<DiemSo> listSV) {
        this.context = context;
        this.layout = layout;
        this.listSV = listSV;
    }

    @Override
    public int getCount() {
        return listSV.size();
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
        TextView txtTenSV, txtCC, txtKT, txtTH, txtKTM;
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
        holder.txtTenSV = (TextView) view.findViewById(R.id.DS_txtTen_XemDiem);
        holder.txtCC = (TextView) view.findViewById(R.id.DS_txtCC_XemDiem);
        holder.txtKT = (TextView) view.findViewById(R.id.DS_txtKT_XemDiem);
        holder.txtTH = (TextView) view.findViewById(R.id.DS_txtTH_XemDiem);
        holder.txtKTM = (TextView) view.findViewById(R.id.DS_txtKTM_XemDiem);

        DiemSo diemSo = listSV.get(position);
        // set text
        holder.txtTenSV.setText(diemSo.getTenSV());
        holder.txtCC.setText(diemSo.getDiemCC()+ "");
        holder.txtKT.setText(diemSo.getDiemKT() + "");
        holder.txtTH.setText(diemSo.getDiemTH() + "");
        holder.txtKTM.setText(diemSo.getDiemKTM() + "");

        return view;
    }
}
