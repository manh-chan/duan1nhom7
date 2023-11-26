package com.example.duan1nhom7.Model;

public class NhaCungCap {
private int ma_nhacc;
private String ten_nhacc;
private String sdt_nhacc;
private String diachi_nhacc;
private String trangthai_nhacc;

    public NhaCungCap(int ma_nhacc, String ten_nhacc, String sdt_nhacc, String diachi_nhacc, String trangthai_nhacc) {
        this.ma_nhacc = ma_nhacc;
        this.ten_nhacc = ten_nhacc;
        this.sdt_nhacc = sdt_nhacc;
        this.diachi_nhacc = diachi_nhacc;
        this.trangthai_nhacc = trangthai_nhacc;
    }

    public NhaCungCap() {
    }

    public int getMa_nhacc() {
        return ma_nhacc;
    }

    public void setMa_nhacc(int ma_nhacc) {
        this.ma_nhacc = ma_nhacc;
    }

    public String getTen_nhacc() {
        return ten_nhacc;
    }

    public void setTen_nhacc(String ten_nhacc) {
        this.ten_nhacc = ten_nhacc;
    }

    public String getSdt_nhacc() {
        return sdt_nhacc;
    }

    public void setSdt_nhacc(String sdt_nhacc) {
        this.sdt_nhacc = sdt_nhacc;
    }

    public String getDiachi_nhacc() {
        return diachi_nhacc;
    }

    public void setDiachi_nhacc(String diachi_nhacc) {
        this.diachi_nhacc = diachi_nhacc;
    }

    public String getTrangthai_nhacc() {
        return trangthai_nhacc;
    }

    public void setTrangthai_nhacc(String trangthai_nhacc) {
        this.trangthai_nhacc = trangthai_nhacc;
    }
}
