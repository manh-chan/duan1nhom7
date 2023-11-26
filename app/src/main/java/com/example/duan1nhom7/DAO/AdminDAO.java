package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    private SQLiteDatabase db;

    public AdminDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public long insert(Admin obj) {
        ContentValues values = new ContentValues();
        values.put("maAdmin", obj.getMa_admin());
        values.put("hoTen_admin", obj.getHoten_admin());
        values.put("email_admin", obj.getEmail_admin());
        values.put("sdt_admin", obj.getSdt_admin());
        values.put("matkhau_admin", obj.getMatkhau_admin());
        return db.insert("Admin", null, values);
    }

    public long updatePass(Admin obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen_admin", obj.getHoten_admin());
        values.put("email_admin", obj.getEmail_admin());
        values.put("sdt_admin", obj.getSdt_admin());
        values.put("matkhau_admin", obj.getMatkhau_admin());
        return db.update("Admin", values, "maAdmin = ?", new String[]{String.valueOf(obj.getMa_admin())});
    }

    public long delete(String id) {
        return db.delete("Admin", "maAdmin = ?", new String[]{String.valueOf(id)});
    }

    public List<Admin> getAll() {
        String sql = "SELECT * FROM Admin";
        return getData(sql);
    }

    public Admin getID(String id) {
        String sql = "SELECT * FROM Admin WHERE maAdmin=?";
        List<Admin> list = getData(sql, id);
        return list.get(0);
    }

    // check login
    public int checkLogin(String id, String password) {
        String sql = "SELECT * FROM Admin WHERE maAdmin=? AND matKhau_admin=?";
        List<Admin> list = getData(sql, id, password);
        if (list.size() == 0) {
            return -1;
        }
        return 1;
    }

    @SuppressLint("Range")
    private List<Admin> getData(String sql, String... selectionArgs) {
        List<Admin> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            Admin obj = new Admin();
            obj.setMa_admin(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maAdmin"))));
            obj.setHoten_admin(cursor.getString(cursor.getColumnIndex("hoTen_admin")));
            obj.setEmail_admin(cursor.getString(cursor.getColumnIndex("email_admin")));
            obj.setSdt_admin(cursor.getString(cursor.getColumnIndex("sdt_admin")));
            obj.setMatkhau_admin(cursor.getString(cursor.getColumnIndex("matkhau_admin")));
            list.add(obj);
        }
        return list;
    }
}
