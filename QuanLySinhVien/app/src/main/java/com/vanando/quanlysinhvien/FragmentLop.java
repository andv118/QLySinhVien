package com.vanando.quanlysinhvien;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Admin on 4/19/2018.
 */

public class FragmentLop extends Fragment {

    private View view;

    public FragmentLop() {
    }

    public static FragmentLop newIntance() {
        FragmentLop fragmentA = new FragmentLop();
        return fragmentA;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_lop, container, false);
        initView();
        onClickView();
        return view;
    }

    private void onClickView() {
    }

    private void initView() {
    }
}
