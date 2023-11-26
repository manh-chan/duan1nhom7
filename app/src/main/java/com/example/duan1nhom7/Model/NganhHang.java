package com.example.duan1nhom7.Model;

public class NganhHang{
private int ma_nh;
private String ten_nh;
private String trangthai_nh;

    public NganhHang(int ma_nh, String ten_nh, String trangthai_nh) {
        this.ma_nh = ma_nh;
        this.ten_nh = ten_nh;
        this.trangthai_nh = trangthai_nh;
    }

    public NganhHang() {
    }

    public int getMa_nh() {
        return ma_nh;
    }

    public void setMa_nh(int ma_nh) {
        this.ma_nh = ma_nh;
    }

    public String getTen_nh() {
        return ten_nh;
    }

    public void setTen_nh(String ten_nh) {
        this.ten_nh = ten_nh;
    }

    public String getTrangthai_nh() {
        return trangthai_nh;
    }

    public void setTrangthai_nh(String trangthai_nh) {
        this.trangthai_nh = trangthai_nh;
    }
}
