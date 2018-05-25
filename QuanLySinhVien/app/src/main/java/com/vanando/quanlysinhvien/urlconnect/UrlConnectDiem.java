package com.vanando.quanlysinhvien.urlconnect;

public class UrlConnectDiem {

    //url localhost
    public static String ipV4 = "192.168.0.101";
    private String urlGetDatabase_Diem = "http://" + ipV4 + "/webserviceQLSV/databasediem.php";
    private String urlInsert_Diem = "http://" + ipV4 + "/webserviceQLSV/insertdiem.php";
    private String urlUpdate_Diem = "http://" + ipV4 + "/webserviceQLSV/updatediem.php";
    private String urlUpdate_DiemXL = "http://" + ipV4 + "/webserviceQLSV/updatexeploai.php";
    private String urlDelete_Diem = "http://" + ipV4 + "/webserviceQLSV/deletediem.php";
    // url web
//    private String urlGetDatabase_Diem = "http://pritplust.000webhostapp.com/databasediem.php";
//    private String urlInsert_Diem = "http://pritplust.000webhostapp.com/insertdiem.php";
//    private String urlUpdate_Diem = "http://pritplust.000webhostapp.com/ updatediem.php";
//    private String urlUpdate_DiemXL = "http://pritplust.000webhostapp.com/ updatexeploai.php";
//    private String urlDelete_Diem = "http://pritplust.000webhostapp.com/deletediem.php/";

    public UrlConnectDiem() {
    }

    public String getUrlGetDatabase_Diem() {
        return urlGetDatabase_Diem;
    }

    public void setUrlGetDatabase_Diem(String urlGetDatabase_Diem) {
        this.urlGetDatabase_Diem = urlGetDatabase_Diem;
    }

    public String getUrlInsert_Diem() {
        return urlInsert_Diem;
    }

    public void setUrlInsert_Diem(String urlInsert_Diem) {
        this.urlInsert_Diem = urlInsert_Diem;
    }

    public String getUrlUpdate_Diem() {
        return urlUpdate_Diem;
    }

    public void setUrlUpdate_Diem(String urlUpdate_Diem) {
        this.urlUpdate_Diem = urlUpdate_Diem;
    }

    public String getUrlDelete_Diem() {
        return urlDelete_Diem;
    }

    public void setUrlDelete_Diem(String urlDelete_Diem) {
        this.urlDelete_Diem = urlDelete_Diem;
    }

    public String getUrlUpdate_DiemXL() {
        return urlUpdate_DiemXL;
    }

    public void setUrlUpdate_DiemXL(String urlUpdate_DiemXL) {
        this.urlUpdate_DiemXL = urlUpdate_DiemXL;
    }
}
