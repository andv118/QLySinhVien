package com.vanando.quanlysinhvien.Diem_So.object;

import java.io.Serializable;

public class DiemSo implements Serializable{

    private int id;
    private int idLop;
    private int idSV;
    private String tenSV;
    private String maSV;
    private int diemCC;
    private int diemKT;
    private int diemTH;
    private int diemKTM;
    private String xepLoai;

    public DiemSo(int id, int idLop, int idSV, String tenSV, String maSV, int diemCC, int diemKT, int diemTH, int diemKTM, String xepLoai) {
        this.id = id;
        this.idLop = idLop;
        this.idSV = idSV;
        this.tenSV = tenSV;
        this.maSV = maSV;
        this.diemCC = diemCC;
        this.diemKT = diemKT;
        this.diemTH = diemTH;
        this.diemKTM = diemKTM;
        this.xepLoai = xepLoai;
    }
    // contructor dành cho xem điểm số
    public DiemSo(String tenSV, int diemCC, int diemKT, int diemTH, int diemKTM) {
        this.tenSV = tenSV;
        this.diemCC = diemCC;
        this.diemKT = diemKT;
        this.diemTH = diemTH;
        this.diemKTM = diemKTM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLop() {
        return idLop;
    }

    public void setIdLop(int idLop) {
        this.idLop = idLop;
    }

    public int getIdSV() {
        return idSV;
    }

    public void setIdSV(int idSV) {
        this.idSV = idSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public int getDiemCC() {
        return diemCC;
    }

    public void setDiemCC(int diemCC) {
        this.diemCC = diemCC;
    }

    public int getDiemKT() {
        return diemKT;
    }

    public void setDiemKT(int diemKT) {
        this.diemKT = diemKT;
    }

    public int getDiemTH() {
        return diemTH;
    }

    public void setDiemTH(int diemTH) {
        this.diemTH = diemTH;
    }

    public int getDiemKTM() {
        return diemKTM;
    }

    public void setDiemKTM(int diemKTM) {
        this.diemKTM = diemKTM;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }
}
