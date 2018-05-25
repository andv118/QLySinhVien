package com.vanando.quanlysinhvien.urlconnect;

public class UrlConnectSinhVien {

    //url localhost
    public static String ipV4 = "192.168.0.101";
    private String urlGetDatabase_SV = "http://" + ipV4 + "/webserviceQLSV/databasesinhvien.php";
    private String urlInsert_SV = "http://" + ipV4 + "/webserviceQLSV/insertsinhvien.php";
    private String urlUpdate_SV = "http://" + ipV4 + "/webserviceQLSV/updatesinhvien.php";
    private String urlDelete_SV = "http://" + ipV4 + "/webserviceQLSV/deletesinhvien.php";
    // url web
//    private String urlGetDatabase_SV = "http://pritplust.000webhostapp.com/databasesinhvien.php";
//    private String urlInsert_SV = "http://pritplust.000webhostapp.com/insertsinhvien.php";
//    private String urlUpdate_SV = "http://pritplust.000webhostapp.com/updatesinhvien.php";
//    private String urlDelete_SV = "http://pritplust.000webhostapp.com/deletesinhvien.php";

    public UrlConnectSinhVien() {
    }

    public String getUrlGetDatabase_SV() {
        return urlGetDatabase_SV;
    }

    public void setUrlGetDatabase_SV(String urlGetDatabase_SV) {
        this.urlGetDatabase_SV = urlGetDatabase_SV;
    }

    public String getUrlInsert_SV() {
        return urlInsert_SV;
    }

    public void setUrlInsert_SV(String urlInsert_SV) {
        this.urlInsert_SV = urlInsert_SV;
    }

    public String getUrlUpdate_SV() {
        return urlUpdate_SV;
    }

    public void setUrlUpdate_SV(String urlUpdate_SV) {
        this.urlUpdate_SV = urlUpdate_SV;
    }

    public String getUrlDelete_SV() {
        return urlDelete_SV;
    }

    public void setUrlDelete_SV(String urlDelete_SV) {
        this.urlDelete_SV = urlDelete_SV;
    }
}
