package com.example.duan1nhom7.Model;

public class NhanVien {
    private int ma_nv;
    private String hoten_nv;
    private String email_nv;
    private String sdt_nv;
    private String matkhau_nv;

    public NhanVien(int ma_nv, String hoten_nv, String email_nv, String sdt_nv, String matkhau_nv) {
        this.ma_nv = ma_nv;
        this.hoten_nv = hoten_nv;
        this.email_nv = email_nv;
        this.sdt_nv = sdt_nv;
        this.matkhau_nv = matkhau_nv;
    }

    public NhanVien() {
    }

    public int getMa_nv() {
        return ma_nv;
    }

    public void setMa_nv(int ma_nv) {
        this.ma_nv = ma_nv;
    }

    public String getHoten_nv() {
        return hoten_nv;
    }

    public void setHoten_nv(String hoten_nv) {
        this.hoten_nv = hoten_nv;
    }

    public String getEmail_nv() {
        return email_nv;
    }

    public void setEmail_nv(String email_nv) {
        this.email_nv = email_nv;
    }

    public String getSdt_nv() {
        return sdt_nv;
    }

    public void setSdt_nv(String sdt_nv) {
        this.sdt_nv = sdt_nv;
    }

    public String getMatkhau_nv() {
        return matkhau_nv;
    }

    public void setMatkhau_nv(String matkhau_nv) {
        this.matkhau_nv = matkhau_nv;
    }
}
