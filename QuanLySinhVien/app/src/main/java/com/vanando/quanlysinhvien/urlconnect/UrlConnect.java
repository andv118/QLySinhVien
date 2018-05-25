package com.vanando.quanlysinhvien.urlconnect;

/**
 * Created by Admin on 5/13/2018.
 */

public class UrlConnect {

    //url localhost
    public static String ipV4 = "192.168.0.101";
    private String urlGetDatabase = "http://" + ipV4 + "/webserviceQLSV/database.php";
    private String urlInsert = "http://" + ipV4 + "/webserviceQLSV/insert.php";
    private String urlUpdate = "http://" + ipV4 + "/webserviceQLSV/update.php";
    private String urlDelete = "http://" + ipV4 + "/webserviceQLSV/delete.php";
    // url web
//    private String urlGetDatabase = "http://pritplust.000webhostapp.com/database.php";
//    private String urlInsert = "http://pritplust.000webhostapp.com/insert.php";
//    private String urlUpdate = "http://pritplust.000webhostapp.com/update.php";
//    private String urlDelete = "http://pritplust.000webhostapp.com/delete.php";


    public UrlConnect() {
    }

    public String getUrlGetDatabase() {
        return urlGetDatabase;
    }

    public void setUrlGetDatabase(String urlGetDatabase) {
        this.urlGetDatabase = urlGetDatabase;
    }

    public String getUrlInsert() {
        return urlInsert;
    }

    public void setUrlInsert(String urlInsert) {
        this.urlInsert = urlInsert;
    }

    public String getUrlUpdate() {
        return urlUpdate;
    }

    public void setUrlUpdate(String urlUpdate) {
        this.urlUpdate = urlUpdate;
    }

    public String getUrlDelete() {
        return urlDelete;
    }

    public void setUrlDelete(String urlDelete) {
        this.urlDelete = urlDelete;
    }
}
