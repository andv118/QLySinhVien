package com.vanando.quanlysinhvien.Diem_So.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vanando.quanlysinhvien.Diem_So.database.DatabaseDiem;
import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.R;


import static com.vanando.quanlysinhvien.Constants.DEFAULT_VALUES_PUT_INT;

public class ThemDiemSoActivity extends AppCompatActivity {
    // view
    private TextView txtTen, txtMaSV;
    private EditText edtDiemCC, edtDiemKT, edtDiemTH, edtDiemKTM;
    private Button btnInsert, btnUpdate, btnBack;
    // bien
    private int id_Diem;
    private DiemSo diemSo;
    // SV
    private int idLop;
    private int id_SV;
    private String maSV;
    private String name_SV;

    private DatabaseDiem databaseDiem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_diem_so);
        initView();
        // get inten diem so chuyen ve
        getIntentDSSV();
        //set text
        setText();
        // set click
        setClick();
    }

    private void setClick() {
        final String diemKT = String.valueOf(edtDiemKT.getText());
        final String diemTH = String.valueOf(edtDiemTH.getText());
        final String diemKTM = String.valueOf(edtDiemKTM.getText());
        // insert
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (diemKT.isEmpty() && diemTH.isEmpty() && diemKTM.isEmpty()) {
                    databaseDiem.raedJSON_INSERT_Diem(idLop, id_SV, name_SV, maSV, edtDiemCC, edtDiemKT, edtDiemTH, edtDiemKTM);
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(ThemDiemSoActivity.this, name_SV + " đã có điểm ko insert được nữa!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // update
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (diemKT.isEmpty() && diemTH.isEmpty() && diemKTM.isEmpty()) {
                    Toast.makeText(ThemDiemSoActivity.this, "Chưa có điểm. không update được", Toast.LENGTH_SHORT).show();
                } else {
                    databaseDiem.readJSON_UPDATE_DiemSo(id_Diem, edtDiemCC, edtDiemKT, edtDiemTH, edtDiemKTM);
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        // back
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getIntentDSSV() {
        // get inten
        Intent intent = getIntent();
        name_SV = intent.getStringExtra("DanhSachSV_TenSV");
        maSV = intent.getStringExtra("DanhSachSV_MaSV");
        id_SV = intent.getIntExtra("DanhSachSV_IdSV", DEFAULT_VALUES_PUT_INT);
        idLop = intent.getIntExtra("DanhSachSV_IdLopHoc", DEFAULT_VALUES_PUT_INT);
        diemSo = (DiemSo) intent.getSerializableExtra("DanhSachSV_ObjectDiemSo");
    }

    private void setText() {

        txtTen.setText(name_SV);
        txtMaSV.setText(maSV);
        if (diemSo != null) {
            id_Diem = diemSo.getId();
            edtDiemKT.setText(diemSo.getDiemKT() + "");
            edtDiemTH.setText(diemSo.getDiemTH() + "");
            edtDiemKTM.setText(diemSo.getDiemKTM() + "");
            edtDiemCC.setText(diemSo.getDiemCC() + "");
        }

    }

//    private void insert_Diem() {
//        // insert
//        btnInsert.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                databaseDiem.raedJSON_INSERT_Diem(id_SV, edtDiemCC, edtDiemKT, edtDiemTH, edtDiemKTM);
//                if (diemSo != null) {
//                    // th1: KT: null, TH:null, KTM:null
//                    if (diemKT.isEmpty() && diemTH.isEmpty() && diemKTM.isEmpty()) {
//                        Toast.makeText(ThemDiemSoActivity.this, "Chưa nhập điểm", Toast.LENGTH_SHORT).show();
//                    }
//                    // th2: KT: #null, TH:null, KTM:null
//                    else if (!diemKT.isEmpty() && diemTH.isEmpty() && diemKTM.isEmpty()) {
//                        databaseDiem.raedJSON_INSERT_Diem(id_SV, edtDiemCC, edtDiemKT, edtDiemTH, edtDiemKTM);
//                        // setText
//                        edtDiemKT.setText(diemSo.getDiemKT() + "");
//                    }
//                    // th3: KT: #null, TH: #null, KTM:null
//                    else if (!diemKT.isEmpty() && !diemTH.isEmpty() && diemKTM.isEmpty()) {
//                        // setText
//                        edtDiemKT.setText(diemSo.getDiemKT() + "");
//                        edtDiemTH.setText(diemSo.getDiemTH() + "");
//                    }
//                    // th4: KT: #null, TH: null, KTM: #null
//                    else if (!diemKT.isEmpty() && diemTH.isEmpty() && !diemKTM.isEmpty()) {
//                        // setText
//                        edtDiemKT.setText(diemSo.getDiemKT() + "");
//                        edtDiemKTM.setText(diemSo.getDiemKTM() + "");
//                    }
//                    // th5: KT: null, TH: #null, KTM: null
//                    else if (diemKT.isEmpty() && !diemTH.isEmpty() && diemKTM.isEmpty()) {
//                        edtDiemTH.setText(diemSo.getDiemTH() + "");
//                    }
//                    // th6: KT: null, TH: #null, KTM: #null
//                    else if (diemKT.isEmpty() && !diemTH.isEmpty() && !diemKTM.isEmpty()) {
//                        edtDiemTH.setText(diemSo.getDiemTH() + "");
//                        edtDiemKTM.setText(diemSo.getDiemKTM() + "");
//                    }
//                    // th7: KT: null, TH: null, KTM: #null
//                    else if (diemKT.isEmpty() && diemTH.isEmpty() && !diemKTM.isEmpty()) {
//                        edtDiemKTM.setText(diemSo.getDiemKTM() + "");
//                    }
//                    // th8: KT: #null, TH: #null, KTM: #null
//                    else {
//                        edtDiemKT.setText(diemSo.getDiemKT() + "");
//                        edtDiemTH.setText(diemSo.getDiemTH() + "");
//                        edtDiemKTM.setText(diemSo.getDiemKTM() + "");
//                    }
//                    // hear
//                }
//            }
//        });
//    }

    private void initView() {
        txtTen = (TextView) findViewById(R.id.diemSo_txtTen_ThemDiem);
        txtMaSV = (TextView) findViewById(R.id.diemSo_txtMaSV_ThemDiem);
        edtDiemCC = (EditText) findViewById(R.id.diemSo_edtCC_ThemDiem);
        edtDiemKT = (EditText) findViewById(R.id.diemSo_edtKT_ThemDiem);
        edtDiemTH = (EditText) findViewById(R.id.diemSo_edtTH_ThemDiem);
        edtDiemKTM = (EditText) findViewById(R.id.diemSo_edtKTM_ThemDiem);
        btnInsert = (Button) findViewById(R.id.diemSo_btnInsert_ThemDiem);
        btnUpdate = (Button) findViewById(R.id.diemSo_btnUpdate_ThemDiem);
        btnBack = (Button) findViewById(R.id.diemSo_btnBack_ThemDiem);

        databaseDiem = new DatabaseDiem(ThemDiemSoActivity.this);

    }
}
