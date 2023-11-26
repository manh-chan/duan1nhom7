package com.example.duan1nhom7.Model;

public class SanPham {
    private int ma_sp;
    private int ma_nhacc;
    private  int ma_nh;
    private String ten_sp;
    private String gianhap_sp;
    private String giaban_sp;
    private String soluong_sp;
    private String trangthai_sp;

    public SanPham(int ma_sp, int ma_nhacc, int ma_nh, String ten_sp, String gianhap_sp, String giaban_sp, String soluong_sp, String trangthai_sp) {
        this.ma_sp = ma_sp;
        this.ma_nhacc = ma_nhacc;
        this.ma_nh = ma_nh;
        this.ten_sp = ten_sp;
        this.gianhap_sp = gianhap_sp;
        this.giaban_sp = giaban_sp;
        this.soluong_sp = soluong_sp;
        this.trangthai_sp = trangthai_sp;
    }

    public SanPham() {
    }

    public int getMa_sp() {
        return ma_sp;
    }

    public void setMa_sp(int ma_sp) {
        this.ma_sp = ma_sp;
    }

    public int getMa_nhacc() {
        return ma_nhacc;
    }

    public void setMa_nhacc(int ma_nhacc) {
        this.ma_nhacc = ma_nhacc;
    }

    public int getMa_nh() {
        return ma_nh;
    }

    public void setMa_nh(int ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

    public String getGianhap_sp() {
        return gianhap_sp;
    }

    public void setGianhap_sp(String gianhap_sp) {
        this.gianhap_sp = gianhap_sp;
    }

    public String getGiaban_sp() {
        return giaban_sp;
    }

    public void setGiaban_sp(String giaban_sp) {
        this.giaban_sp = giaban_sp;
    }

    public String getSoluong_sp() {
        return soluong_sp;
    }

    public void setSoluong_sp(String soluong_sp) {
        this.soluong_sp = soluong_sp;
    }

    public String getTrangthai_sp() {
        return trangthai_sp;
    }

    public void setTrangthai_sp(String trangthai_sp) {
        this.trangthai_sp = trangthai_sp;
    }
}
