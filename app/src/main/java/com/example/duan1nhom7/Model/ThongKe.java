package com.example.duan1nhom7.Model;

public class ThongKe {
    private String ngay;
    private double tongTien;
    private double KhuyenMai;
    private int tongSoHoaDon;


    public ThongKe(String ngay, double tongtienHd, int i) {
    }

    public ThongKe(String ngay, double tongTien, double khuyenMai, int tongSoHoaDon) {
        this.ngay = ngay;
        this.tongTien = tongTien;
        KhuyenMai = khuyenMai;
        this.tongSoHoaDon = tongSoHoaDon;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getKhuyenMai() {
        return KhuyenMai;
    }

    public void setKhuyenMai(double khuyenMai) {
        KhuyenMai = khuyenMai;
    }

    public int getTongSoHoaDon() {
        return tongSoHoaDon;
    }

    public void setTongSoHoaDon(int tongSoHoaDon) {
        this.tongSoHoaDon = tongSoHoaDon;
    }
    public double TongTienDoanhThu(){
        double total =  tongTien;
        return total - KhuyenMai;
    }
}
