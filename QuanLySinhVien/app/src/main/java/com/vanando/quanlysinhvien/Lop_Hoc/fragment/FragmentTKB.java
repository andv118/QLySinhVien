package com.vanando.quanlysinhvien.Lop_Hoc.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.vanando.quanlysinhvien.R;

import static com.vanando.quanlysinhvien.MyApplication.app;
import static com.vanando.quanlysinhvien.MyApplication.appArrLopHoc;

/**
 * Created by Admin on 4/19/2018.
 */

public class FragmentTKB extends Fragment {

    private View view;
    private Button btnXemTKB;
    private TextView txtTKB;

    public FragmentTKB() {
    }

    public static FragmentTKB newIntance() {
        FragmentTKB fragmentB = new FragmentTKB();
        return fragmentB;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tkb, container, false);
        initView();
        onClickView();
        return view;
    }

    private void onClickView() {
        btnXemTKB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TKB();
            }
        });
    }

    private void TKB() {
        int i = app.appArrLopHoc.size();
        txtTKB.setText(app.appArrLopHoc.get(2).getTenLopHoc() + "");



    }

    private void initView() {
        btnXemTKB = (Button) view.findViewById(R.id.fragment_TKB_btnXem);
        txtTKB = (TextView) view.findViewById(R.id.fragment_TKB_txtTKB);

    }
}
