package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.KhachHang;
import com.example.duan1nhom7.Model.NganhHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private static SQLiteDatabase db;
    public KhachHangDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("maKhachHang", obj.getMa_kh());
        values.put("hoTen_kh", obj.getTen_kh());
        values.put("sdt_kh", obj.getSdt_kh());
        return db.insert("KhachHang", null, values);

    }

    public static long update(KhachHang obj) {
        ContentValues values = new ContentValues();
        values.put("hoTen_kh", obj.getTen_kh());
        values.put("sdt_kh", obj.getSdt_kh());
        return db.update("KhachHang", values, "maKhachHang = ?", new String[]{String.valueOf(obj.getMa_kh())});
    }

    public static long delete(String id) {
        return db.delete("KhachHang", "maKhachHang = ?", new String[]{String.valueOf(id)});
    }

    public static List<KhachHang> getAll() {
        String sql = "SELECT * FROM KhachHang";
        return getData(sql);
    }

    public KhachHang getID(String id) {
        String sql = "SELECT * FROM KhachHang WHERE maKhachHang = ?";
        List<KhachHang> list = getData(sql, id);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            // Trường hợp không tìm thấy dữ liệu với id cung cấp
            return null; // hoặc có thể trả về giá trị mặc định khác tùy vào yêu cầu của bạn
        }
    }

    @SuppressLint("Range")
    private static List<KhachHang> getData(String sql, String... selectionArgs) {
        List<KhachHang> list = new ArrayList<>();

        try (Cursor cursor = db.rawQuery(sql, selectionArgs)) {
            while (cursor != null && cursor.moveToNext()) {
                KhachHang obj = new KhachHang();
                obj.setMa_kh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKhachHang"))));
                obj.setTen_kh(cursor.getString(cursor.getColumnIndex("hoTen_kh")));
                obj.setSdt_kh(cursor.getString(cursor.getColumnIndex("sdt_kh")));
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý hoặc ghi log nếu có lỗi
        }

        return list;
    }
}
