package com.vanando.quanlysinhvien;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.vanando.quanlysinhvien.activity.ThemLopHocActivity;

/**
 * Created by Admin on 5/13/2018.
 */

public class DialogThoiGian {

    private Context context;
    private Spinner spinTietHoc, spinThu;
    private Button btnHuyDialog, btnDongY;

    final String[] mangTietHoc = {"Kíp 1: 7h-9h", "Kíp 2: 9h-11h","Kíp 3: 12h-14h","Kíp 4: 14h-16h",
            "Kíp 5: 16h-18h","Kíp 6: 18h-20h"};
    String[] mangThu = {"Thứ 2","Thứ 3","Thứ 4","Thứ 5","Thứ 6","Thứ 7","Cn"};

    int positionTiet;
    int positionThu;

    public DialogThoiGian(Context context) {
        this.context = context;
    }

    public void setDialog(final EditText edtTiet, final EditText edtThu) {
        final Dialog dialog = new Dialog(context);
        dialog.setTitle("Thêm thời gian: ");
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.custom_dialog_tg);
        // anh xa
        spinTietHoc = (Spinner) dialog.findViewById(R.id.spinnerTietHoc);
        spinThu = (Spinner) dialog.findViewById(R.id.spinnerThu);
        btnHuyDialog = (Button) dialog.findViewById(R.id.btnHuyDialogTg);
        btnDongY = (Button) dialog.findViewById(R.id.btnDongYDialogTg);

        // spinner
        ArrayAdapter adapterTietHoc = new ArrayAdapter(context,android.R.layout.simple_spinner_item,mangTietHoc);
        ArrayAdapter adapterThu = new ArrayAdapter(context,android.R.layout.simple_spinner_item,mangThu);
        adapterTietHoc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterThu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinTietHoc.setAdapter(adapterTietHoc);
        spinThu.setAdapter(adapterThu);

        // click item spinner
        spinTietHoc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                positionTiet = position;

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinThu.isClickable();

        spinThu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, final int position, long id) {
                positionThu = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // ckick dong y
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtTiet.setText(mangTietHoc[positionTiet]);
                edtThu.setText(mangThu[positionThu]);
                dialog.cancel();
            }
        });

        // click button huy Dialog
        btnHuyDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialog.show();
    }

}
