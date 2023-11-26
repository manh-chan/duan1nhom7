package com.example.duan1nhom7.Model;

public class Admin {
    private int ma_admin;
    private String hoten_admin;
    private String email_admin;
    private String sdt_admin;
    private String matkhau_admin;

    public Admin(int ma_admin, String hoten_admin, String email_admin, String sdt_admin, String matkhau_admin) {
        this.ma_admin = ma_admin;
        this.hoten_admin = hoten_admin;
        this.email_admin = email_admin;
        this.sdt_admin = sdt_admin;
        this.matkhau_admin = matkhau_admin;
    }

    public Admin() {
    }

    public int getMa_admin() {
        return ma_admin;
    }

    public void setMa_admin(int ma_admin) {
        this.ma_admin = ma_admin;
    }

    public String getHoten_admin() {
        return hoten_admin;
    }

    public void setHoten_admin(String hoten_admin) {
        this.hoten_admin = hoten_admin;
    }

    public String getEmail_admin() {
        return email_admin;
    }

    public void setEmail_admin(String email_admin) {
        this.email_admin = email_admin;
    }

    public String getSdt_admin() {
        return sdt_admin;
    }

    public void setSdt_admin(String sdt_admin) {
        this.sdt_admin = sdt_admin;
    }

    public String getMatkhau_admin() {
        return matkhau_admin;
    }

    public void setMatkhau_admin(String matkhau_admin) {
        this.matkhau_admin = matkhau_admin;
    }
}
