package com.example.duan1nhom7.Model;

public class HoaDon {
    private int ma_hd;
    private int ma_nv_hd;
    private int ma_kh_hd;

    private String thoigian_hd;
    private String soluong_hd;
    private double tongtien_hd;
    private double khuyenmai_hd;

    public HoaDon() {
    }

    public HoaDon(int ma_hd, int ma_nv_hd, int ma_kh_hd, String thoigian_hd, String soluong_hd, double tongtien_hd, double khuyenmai_hd) {
        this.ma_hd = ma_hd;
        this.ma_nv_hd = ma_nv_hd;
        this.ma_kh_hd = ma_kh_hd;
        this.thoigian_hd = thoigian_hd;
        this.soluong_hd = soluong_hd;
        this.tongtien_hd = tongtien_hd;
        this.khuyenmai_hd = khuyenmai_hd;
    }

    public String getSoluong_hd() {
        return soluong_hd;
    }

    public void setSoluong_hd(String soluong_hd) {
        this.soluong_hd = soluong_hd;
    }

    public int getMa_hd() {
        return ma_hd;
    }

    public void setMa_hd(int ma_hd) {
        this.ma_hd = ma_hd;
    }

    public int getMa_nv_hd() {
        return ma_nv_hd;
    }

    public void setMa_nv_hd(int ma_nv_hd) {
        this.ma_nv_hd = ma_nv_hd;
    }

    public int getMa_kh_hd() {
        return ma_kh_hd;
    }

    public void setMa_kh_hd(int ma_kh_hd) {
        this.ma_kh_hd = ma_kh_hd;
    }

    public String getThoigian_hd() {
        return thoigian_hd;
    }

    public void setThoigian_hd(String thoigian_hd) {
        this.thoigian_hd = thoigian_hd;
    }

    public double getTongtien_hd() {
        return tongtien_hd;
    }

    public void setTongtien_hd(double tongtien_hd) {
        this.tongtien_hd = tongtien_hd;
    }

    public double getKhuyenmai_hd() {
        return khuyenmai_hd;
    }

    public void setKhuyenmai_hd(double khuyenmai_hd) {
        this.khuyenmai_hd = khuyenmai_hd;
    }

    public double KhuyenMai(){
        double total =  tongtien_hd;
        return total - khuyenmai_hd;
    }

}
