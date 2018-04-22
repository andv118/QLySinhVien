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

public class FragmentTKB extends Fragment {

    private View view;

    public FragmentTKB() {
    }

    public static FragmentTKB newIntance() {
        FragmentTKB fragmentB = new FragmentTKB();
        return fragmentB;
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
    }

    private void initView() {
    }
}
