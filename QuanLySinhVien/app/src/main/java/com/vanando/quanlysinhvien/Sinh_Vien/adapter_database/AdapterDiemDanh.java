package com.vanando.quanlysinhvien.Sinh_Vien.adapter_database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.vanando.quanlysinhvien.R;

import java.util.List;

public class AdapterDiemDanh extends BaseAdapter {

    private Context context;
    private int layoutDiemDanh;
    private List<String> listSV;
    private int diemCC;

    public AdapterDiemDanh(Context context, int layoutDiemDanh, List<String> listSV, int diemCC) {
        this.context = context;
        this.layoutDiemDanh = layoutDiemDanh;
        this.listSV = listSV;
        this.diemCC = diemCC;
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

    class ViewHolderDiemDanh {
        TextView txtTen;
        RadioGroup radioGroup;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolderDiemDanh holder;
        if (view == null) {
            holder = new ViewHolderDiemDanh();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layoutDiemDanh, null);
            view.setTag(holder);
        } else {
            holder = (ViewHolderDiemDanh) view.getTag();
        }
        // anh xa
        holder.txtTen = (TextView) view.findViewById(R.id.Cust_SV_Adapter_DiemDanh_Ten);
        holder.radioGroup = (RadioGroup) view.findViewById(R.id.Cust_SV_Adapter_DiemDanh_RadioGroup);
        String tenSV = listSV.get(position);

        // set click

        holder.txtTen.setText(tenSV);
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.Cust_SV_Adapter_DiemDanh_RadioButtonNghi:
                        diemCC = diemCC - 2;
                        break;
                    case R.id.Cust_SV_Adapter_DiemDanh_RadioButtonDi:
                        diemCC = diemCC - 0;
                        break;
                    default:
                        break;
                }
            }
        });
        return view;
    }
}
