package com.example.duan1nhom7.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "PNLIB";
    public static final int DB_VERSION = 19;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        //Admin
        String createTableAdmin = "create table Admin(" +
                "maAdmin TEXT PRIMARY KEY, " +
                "hoten_admin TEXT NOT NULL, " +
                "email_admin TEXT NOT NULL, " +
                "sdt_admin TEXT NOT NULL, " +
                "matkhau_admin TEXT NOT NULL)";
        db.execSQL(createTableAdmin);

        // Nhan Vien
        String createTableNhanVien = "create table NhanVien(" +
                "maNhanVien TEXT PRIMARY KEY, " +
                "hoTen_nv TEXT NOT NULL, " +
                "email_nv TEXT NOT NULL, " +
                "sdt_nv TEXT NOT NULL, " +
                "matKhau_nv TEXT NOT NULL)";
        db.execSQL(createTableNhanVien);

        // khach hang
        String createTableKhachHang = "create table KhachHang(" +
                "maKhachHang TEXT PRIMARY KEY, " +
                "hoTen_kh TEXT NOT NULL, " +
                "sdt_kh TEXT NOT NULL)";
        db.execSQL(createTableKhachHang);


        //Hoa don


        //san pham
        String createTableSanPham = "create table SanPham(" +
                "maSanPham INTEGER PRIMARY KEY AUTOINCREMENT," +
                "maNhaCC TEXT REFERENCES NhaCungCap(maNhaCC), " +
                "maNganhHang TEXT REFERENCES NganhHang(maNganhHang), " +
                "ten_sp TEXT NOT NULL, " +
                "gianhap_sp TEXT NOT NULL, " +
                "giaban_sp TEXT NOT NULL, " +
                "soluong_sp TEXT NOT NULL, " +
                "trangthai_sp TEXT NOT NULL)";
        db.execSQL(createTableSanPham);

        //Nha Cung Cap
        String createTableNhaCungCap = "create table NhaCungCap(" +
                "maNhaCC INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten_nhacc TEXT NOT NULL, " +
                "sdt_nhacc TEXT NOT NULL, " +
                "diachi_nhacc TEXT NOT NULL, " +
                 "trangthai_nhacc TEXT  NOT NULL)";
        db.execSQL(createTableNhaCungCap);

        //nganh Hang

        String createTableNganhHang = "create table NganhHang(" +
                "maNganhHang INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "ten_nh TEXT NOT NULL, " +
                "trangthai_nh TEXT NOT NULL)";
        db.execSQL(createTableNganhHang);


        //Han Su Dung


        String createTableHanSuDung = "create table HanSuDung(" +
                "maHanSuDung INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maSanPham TEXT REFERENCES SanPham(maSanPham), " +
                "ngaysx_hsd TEXT NOT NULL, " +
                "soluong_hsd TEXT NOT NULL)";
        db.execSQL(createTableHanSuDung);

        //Cong Viec


        String createTableCongViec = "create table CongViec(" +
                "maCongViec INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maAdmin TEXT REFERENCES Admin(maAdmin), " +
                "maNhanVien TEXT REFERENCES NhanVien(maNhanVien), " +
                "tieude_cv TEXT NOT NULL, " +
                "ghichu_cv TEXT NOT NULL, " +
                "thoihan_cv TEXT NOT NULL, " +
                "trangthai_cv TEXT NOT NULL)";
        db.execSQL(createTableCongViec);



//          Hoa Don

        String createTableHoaDon = "create table HoaDon(" +
                "maHoaDon INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "maNhanVien TEXT REFERENCES NhanVien(maNhanVien), " +
                "maKhachHang TEXT REFERENCES KhachHang(maKhachHang), " +
                "thoigian_hd TEXT NOT NULL, " +
                "soluong_sp TEXT NOT NULL, " +
                "khuyenmai_hd DOUBLE NOT NULL, " +
                "tongtien_hd DOUBLE NOT NULL)";
        db.execSQL(createTableHoaDon);

        db.execSQL("INSERT INTO HoaDon (maHoaDon,maNhanVien,maKhachHang,thoigian_hd,soluong_sp,khuyenmai_hd,tongtien_hd) VALUES" +
                "('45745','8888','1111','12/05/2023','4','40000','1200000')");

        db.execSQL("INSERT INTO CongViec (maCongViec,maAdmin,maNhanVien,tieude_cv,ghichu_cv,thoihan_cv,trangthai_cv) VALUES" +
                "('4444','32114','8888','Hop nhan Vien','Cuoc hop bat buoc','12/05/2023','Dang lam')");

        db.execSQL("INSERT INTO HanSuDung (maHanSuDung,maSanPham,ngaysx_hsd,soluong_hsd) VALUES" +
                "('11343','1','18/05/2004','4')");

        db.execSQL("INSERT INTO Admin (maAdmin,hoten_admin,email_admin,sdt_admin,matkhau_admin) VALUES" +
                "('32114','Phạm Kiều Trinh','manh@gmail.com','0399898172','0399898172')");

        db.execSQL("INSERT INTO KhachHang (maKhachHang,hoTen_kh,sdt_kh) VALUES" +
                "('1111','Kieu Anh Tuan','0399898172')");

        db.execSQL("INSERT INTO NhanVien (maNhanVien,hoTen_nv,email_nv,sdt_nv,matKhau_nv) VALUES" +
                "('8888','Tran Duy Manh','manh@gmail.com','0399898172','32114')");

        db.execSQL("INSERT INTO SanPham (maSanPham,maNhaCC, maNganhHang, ten_sp, gianhap_sp, giaban_sp, soluong_sp, trangthai_sp) VALUES " +
                "('1','2', '3', 'NuocCoCa', '10000', '11000', '200', 'true')," +
                "('2','3', '4', 'NuocDua', '20000', '21000', '400', 'true')");

        db.execSQL("INSERT INTO NhaCungCap (maNhaCC ,ten_nhacc, sdt_nhacc, diachi_nhacc, trangthai_nhacc) VALUES " +
                "('2','Doremon', '0399898172', 'Ha Noi', 'true')," +
                "('3','Nobita', '0399898172', 'Bac Giang', 'true')");

        db.execSQL("INSERT INTO NganhHang (maNganhHang,ten_nh, trangthai_nh) VALUES " +
                "( '3','HuuCo', 'true')," +
                "('4','Thuc Pham Chuc Nang', 'true')");
    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1) {
            db.execSQL("drop table if exists NhanVien");
            db.execSQL("drop table if exists Admin");
            db.execSQL("drop table if exists KhachHang");
            db.execSQL("drop table if exists HoaDon");
            db.execSQL("drop table if exists SanPham");
            db.execSQL("drop table if exists NhaCungCap");
            db.execSQL("drop table if exists NganhHang");
            db.execSQL("drop table if exists HanSuDung");
            db.execSQL("drop table if exists CongViec");
            onCreate(db);
        }
    }
}
