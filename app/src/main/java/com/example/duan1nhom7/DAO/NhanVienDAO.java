package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    DBHelper dbHelper;
    private static SQLiteDatabase db;

    public NhanVienDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("maNhanVien", obj.getMa_nv());
        values.put("hoTen_nv", obj.getHoten_nv());
        values.put("email_nv", obj.getEmail_nv());
        values.put("sdt_nv", obj.getSdt_nv());
        values.put("matKhau_nv", obj.getMatkhau_nv());
        return db.insert("NhanVien", null, values);
    }


    public static long updatePass(NhanVien obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen_nv", obj.getHoten_nv());
        values.put("email_nv", obj.getEmail_nv());
        values.put("sdt_nv", obj.getSdt_nv());
        values.put("matKhau_nv", obj.getMatkhau_nv());
        return db.update("NhanVien", values, "maNhanVien = ?", new String[]{String.valueOf(obj.getMa_nv())});
    }

    public static long delete(String id) {
        return db.delete("NhanVien", "maNhanVien = ?", new String[]{String.valueOf(id)});
    }

    public static List<NhanVien> getAll() {
        String sql = "SELECT * FROM NhanVien";
        return getData(sql);
    }

    public NhanVien getID(String id) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien=?";
        List<NhanVien> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;

    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM NhanVien WHERE maNhanVien=? AND matKhau_nv=?";
        List<NhanVien> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    public boolean Register(String username,String password,String hoten,String email,String sdt){
        SQLiteDatabase sqLiteDatabase=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("maNhanVien",username);
        contentValues.put("hoTen_nv",hoten);
        contentValues.put("email_nv",email);
        contentValues.put("sdt_nv",sdt);
        contentValues.put("matKhau_nv",password);


        long check=sqLiteDatabase.insert("NhanVien",null,contentValues);
        return check!=-1;

    }


    //Quen mat khau
    public String ForgotPass(String email){
        SQLiteDatabase sqLiteDatabase=dbHelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT sdt_nv FROM NhanVien WHERE maNhanVien=?",new String[]{email});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }else{
            return "";
        }
    }


    @SuppressLint("Range")
    private static List<NhanVien> getData(String sql, String... selectionArgs) {
        List<NhanVien> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            NhanVien obj = new NhanVien();
            obj.setMa_nv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNhanVien"))));
            obj.setHoten_nv(cursor.getString(cursor.getColumnIndex("hoTen_nv")));
            obj.setEmail_nv(cursor.getString(cursor.getColumnIndex("email_nv")));
            obj.setSdt_nv(cursor.getString(cursor.getColumnIndex("sdt_nv")));
            obj.setMatkhau_nv(cursor.getString(cursor.getColumnIndex("matKhau_nv")));
            list.add(obj);
        }
        return list;
    }
}
