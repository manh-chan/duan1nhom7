package com.example.duan1nhom7.Model;

public class KhachHang {
    private int ma_kh;
    private String ten_kh;
    private String sdt_kh;

    public KhachHang() {
    }

    public KhachHang(int ma_kh, String ten_kh, String sdt_kh) {
        this.ma_kh = ma_kh;
        this.ten_kh = ten_kh;
        this.sdt_kh = sdt_kh;
    }

    public int getMa_kh() {
        return ma_kh;
    }

    public void setMa_kh(int ma_kh) {
        this.ma_kh = ma_kh;
    }

    public String getTen_kh() {
        return ten_kh;
    }

    public void setTen_kh(String ten_kh) {
        this.ten_kh = ten_kh;
    }

    public String getSdt_kh() {
        return sdt_kh;
    }

    public void setSdt_kh(String sdt_kh) {
        this.sdt_kh = sdt_kh;
    }
}
