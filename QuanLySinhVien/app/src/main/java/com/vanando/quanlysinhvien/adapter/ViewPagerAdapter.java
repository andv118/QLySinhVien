package com.vanando.quanlysinhvien.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.vanando.quanlysinhvien.activity.FragmentLop;
import com.vanando.quanlysinhvien.activity.FragmentTKB;

/**
 * Created by Admin on 4/20/2018.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag = null;
        switch (position) {
            case 0:
               frag = FragmentLop.newIntance();
               break;
            case 1:
                frag = FragmentTKB.newIntance();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        switch (position) {
            case 0:
                return  "Danh sach lop";
            case 1:
                return  "Thoi khoa bieu";
        }
        return title;
    }
}