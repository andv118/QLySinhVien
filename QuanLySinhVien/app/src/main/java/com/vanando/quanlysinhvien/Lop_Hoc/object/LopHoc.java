package com.vanando.quanlysinhvien.Lop_Hoc.object;

import java.io.Serializable;

/**
 * Created by Admin on 5/3/2018.
 */

public class LopHoc implements Serializable{

    private int id;
    private String tenLopHoc;
    private String thoiGian;
    private String thuHoc;
    private String phongHoc;

    public LopHoc() {
    }

    public LopHoc(int id, String tenLopHoc, String thoiGian, String thuHoc, String phongHoc) {
        this.id = id;
        this.tenLopHoc = tenLopHoc;
        this.thoiGian = thoiGian;
        this.thuHoc = thuHoc;
        this.phongHoc = phongHoc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLopHoc() {
        return tenLopHoc;
    }

    public void setTenLopHoc(String tenLopHoc) {
        this.tenLopHoc = tenLopHoc;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getThuHoc() {
        return thuHoc;
    }

    public void setThuHoc(String thuHoc) {
        this.thuHoc = thuHoc;
    }

    public String getPhongHoc() {
        return phongHoc;
    }

    public void setPhongHoc(String phongHoc) {
        this.phongHoc = phongHoc;
    }

}
