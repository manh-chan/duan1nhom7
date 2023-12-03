package com.example.duan1nhom7.Model;

public class CongViec {

    private int ma_cv;
    private int maadmin_cv;
    private int manv_cv;
    private String tieude_cv;
    private String ghichu_cv;
    private String thoihan_cv;
    private String trangthai_cv;

    public CongViec() {
    }

    public CongViec(int ma_cv, int maadmin_cv, int manv_cv, String tieude_cv, String ghichu_cv, String thoihan_cv, String trangthai_cv) {
        this.ma_cv = ma_cv;
        this.maadmin_cv = maadmin_cv;
        this.manv_cv = manv_cv;
        this.tieude_cv = tieude_cv;
        this.ghichu_cv = ghichu_cv;
        this.thoihan_cv = thoihan_cv;
        this.trangthai_cv = trangthai_cv;
    }

    public int getMa_cv() {
        return ma_cv;
    }

    public void setMa_cv(int ma_cv) {
        this.ma_cv = ma_cv;
    }

    public int getMaadmin_cv() {
        return maadmin_cv;
    }

    public void setMaadmin_cv(int maadmin_cv) {
        this.maadmin_cv = maadmin_cv;
    }

    public int getManv_cv() {
        return manv_cv;
    }

    public void setManv_cv(int manv_cv) {
        this.manv_cv = manv_cv;
    }

    public String getTieude_cv() {
        return tieude_cv;
    }

    public void setTieude_cv(String tieude_cv) {
        this.tieude_cv = tieude_cv;
    }

    public String getGhichu_cv() {
        return ghichu_cv;
    }

    public void setGhichu_cv(String ghichu_cv) {
        this.ghichu_cv = ghichu_cv;
    }

    public String getThoihan_cv() {
        return thoihan_cv;
    }

    public void setThoihan_cv(String thoihan_cv) {
        this.thoihan_cv = thoihan_cv;
    }

    public String getTrangthai_cv() {
        return trangthai_cv;
    }

    public void setTrangthai_cv(String trangthai_cv) {
        this.trangthai_cv = trangthai_cv;
    }
}
