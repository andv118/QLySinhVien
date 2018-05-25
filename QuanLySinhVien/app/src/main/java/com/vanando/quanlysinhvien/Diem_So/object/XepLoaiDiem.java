package com.vanando.quanlysinhvien.Diem_So.object;

public class XepLoaiDiem {
    private int idDiem;
    private Double diemTB;
    private String diemQuyDoi;
    private String xepLoai;
    private String tenSV;

    public XepLoaiDiem(int idDiem, Double diemTB, String diemQuyDoi, String xepLoai, String tenSV) {
        this.idDiem = idDiem;
        this.diemTB = diemTB;
        this.diemQuyDoi = diemQuyDoi;
        this.xepLoai = xepLoai;
        this.tenSV = tenSV;
    }

    public int getIdDiem() {
        return idDiem;
    }

    public void setIdDiem(int idDiem) {
        this.idDiem = idDiem;
    }

    public Double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(Double diemTB) {
        this.diemTB = diemTB;
    }

    public String getDiemQuyDoi() {
        return diemQuyDoi;
    }

    public void setDiemQuyDoi(String diemQuyDoi) {
        this.diemQuyDoi = diemQuyDoi;
    }

    public String getXepLoai() {
        return xepLoai;
    }

    public void setXepLoai(String xepLoai) {
        this.xepLoai = xepLoai;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }
}
