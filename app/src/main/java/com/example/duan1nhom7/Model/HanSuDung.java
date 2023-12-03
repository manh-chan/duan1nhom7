package com.example.duan1nhom7.Model;

public class HanSuDung {

    private int ma_hsd;
    private int masp_hsd;
    private String ngaysx_hsd;
    private String soluong_hsd;

    public HanSuDung() {
    }

    public HanSuDung(int ma_hsd, int masp_hsd, String ngaysx_hsd, String soluong_hsd) {
        this.ma_hsd = ma_hsd;
        this.masp_hsd = masp_hsd;
        this.ngaysx_hsd = ngaysx_hsd;
        this.soluong_hsd = soluong_hsd;
    }

    public int getMa_hsd() {
        return ma_hsd;
    }

    public void setMa_hsd(int ma_hsd) {
        this.ma_hsd = ma_hsd;
    }

    public int getMasp_hsd() {
        return masp_hsd;
    }

    public void setMasp_hsd(int masp_hsd) {
        this.masp_hsd = masp_hsd;
    }

    public String getNgaysx_hsd() {
        return ngaysx_hsd;
    }

    public void setNgaysx_hsd(String ngaysx_hsd) {
        this.ngaysx_hsd = ngaysx_hsd;
    }

    public String getSoluong_hsd() {
        return soluong_hsd;
    }

    public void setSoluong_hsd(String soluong_hsd) {
        this.soluong_hsd = soluong_hsd;
    }
}
