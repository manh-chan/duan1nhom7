package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private static SQLiteDatabase db;
    public SanPhamDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("maSanPham", obj.getMa_sp());
        values.put("maNhaCC", obj.getMa_nhacc());
        values.put("maNganhHang", obj.getMa_nh());
        values.put("ten_sp", obj.getTen_sp());
        values.put("gianhap_sp", obj.getGianhap_sp());
        values.put("giaban_sp", obj.getGiaban_sp());
        values.put("soluong_sp", obj.getSoluong_sp());
        values.put("trangthai_sp", obj.getTrangthai_sp());
        return db.insert("SanPham", null, values);
    }

    public static long update(SanPham obj) {
        ContentValues values = new ContentValues();
        values.put("maNhaCC", obj.getMa_nhacc());
        values.put("maNganhHang", obj.getMa_nh());
        values.put("ten_sp", obj.getTen_sp());
        values.put("gianhap_sp", obj.getGianhap_sp());
        values.put("giaban_sp", obj.getGiaban_sp());
        values.put("soluong_sp", obj.getSoluong_sp());
        values.put("trangthai_sp", obj.getTrangthai_sp());
        return db.update("SanPham", values, "maSanPham = ?", new String[]{String.valueOf(obj.getMa_sp())});
    }

    public static long delete(String id) {
        return db.delete("SanPham", "maSanPham = ?", new String[]{String.valueOf(id)});
    }

    public static List<SanPham> getAll() {
        String sql = "SELECT * FROM SanPham";
        return getData(sql);
    }

    public SanPham getID(String id) {
        String sql = "SELECT * FROM SanPham WHERE maSanPham = ?";
        List<SanPham> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;// Trả về null nếu không tìm thấy dữ liệu
    }

    @SuppressLint("Range")
    private static List<SanPham> getData(String sql, String... selectionArgs) {
        List<SanPham> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                SanPham obj = new SanPham();
                obj.setMa_sp(cursor.getInt(cursor.getColumnIndex("maSanPham")));
                obj.setMa_nhacc(cursor.getInt(cursor.getColumnIndex("maNhaCC")));
                obj.setMa_nh(cursor.getInt(cursor.getColumnIndex("maNganhHang")));
                obj.setTen_sp(cursor.getString(cursor.getColumnIndex("ten_sp")));
                obj.setGianhap_sp(cursor.getString(cursor.getColumnIndex("gianhap_sp")));
                obj.setGiaban_sp(cursor.getString(cursor.getColumnIndex("giaban_sp")));
                obj.setSoluong_sp(cursor.getString(cursor.getColumnIndex("soluong_sp")));
                obj.setTrangthai_sp(cursor.getString(cursor.getColumnIndex("trangthai_sp")));
                list.add(obj);
            }
            cursor.close(); // Đóng cursor sau khi sử dụng
        }

        return list;
    }}
