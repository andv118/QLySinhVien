package com.vanando.quanlysinhvien;

import android.content.res.Configuration;
import android.app.Application;

import com.vanando.quanlysinhvien.Diem_So.object.DiemSo;
import com.vanando.quanlysinhvien.Lop_Hoc.object.LopHoc;
import com.vanando.quanlysinhvien.Sinh_Vien.object.SinhVien;

import java.util.ArrayList;

public class MyApplication extends Application {

    public static MyApplication app;
    public static ArrayList<LopHoc> appArrLopHoc = new ArrayList<>();
    public static ArrayList<SinhVien> appArrSinhVien = new ArrayList<>();
    public static ArrayList<DiemSo> appArrDiemSo = new ArrayList<>();

    public static MyApplication getApp() {
        app = new MyApplication();
        return app;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Required initialization logic here!
    }

    // Called by the system when the device configuration changes while your component is running.
    // Overriding this method is totally optional!
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    // This is called when the overall system is running low on memory,
    // and would like actively running processes to tighten their belts.
    // Overriding this method is totally optional!
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }
}
