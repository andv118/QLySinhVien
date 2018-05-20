package com.vanando.quanlysinhvien.Sinh_Vien.object;

import java.io.Serializable;

public class SinhVien implements Serializable {

    private int id;
    private String tenSv;
    private String maSv;
    private int idLopHoc;

    public SinhVien(int id, String tenSv, String maSv, int idLopHoc) {
        this.id = id;
        this.tenSv = tenSv;
        this.maSv = maSv;
        this.idLopHoc = idLopHoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getMaSv() {
        return maSv;
    }

    public void setMaSv(String maSv) {
        this.maSv = maSv;
    }

    public int getIdLopHoc() {
        return idLopHoc;
    }

    public void setIdLopHoc(int idLopHoc) {
        this.idLopHoc = idLopHoc;
    }
}
