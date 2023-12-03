package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class NganhHangDAO {
    private static SQLiteDatabase db;
    public NganhHangDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(NganhHang obj) {
        ContentValues values = new ContentValues();
        values.put("maNganhHang", obj.getMa_nh());
        values.put("ten_nh", obj.getTen_nh());
        values.put("trangthai_nh", obj.getTrangthai_nh());
        return db.insert("NganhHang", null, values);

    }

    public static long update(NganhHang obj) {
        ContentValues values = new ContentValues();
        values.put("ten_nh", obj.getTen_nh());
        values.put("trangthai_nh", obj.getTrangthai_nh());
        return db.update("NganhHang", values, "maNganhHang = ?", new String[]{String.valueOf(obj.getMa_nh())});
    }

    public static long delete(String id) {
        return db.delete("NganhHang", "maNganhHang = ?", new String[]{String.valueOf(id)});
    }

    public static List<NganhHang> getAll() {
        String sql = "SELECT * FROM NganhHang";
        return getData(sql);
    }

    public NganhHang getID(String id) {
        String sql = "SELECT * FROM NganhHang WHERE maNganhHang = ?";
        List<NganhHang> list = getData(sql, id);

        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            // Trường hợp không tìm thấy dữ liệu với id cung cấp
            return null; // hoặc có thể trả về giá trị mặc định khác tùy vào yêu cầu của bạn
        }
    }

    @SuppressLint("Range")
    private static List<NganhHang> getData(String sql, String... selectionArgs) {
        List<NganhHang> list = new ArrayList<>();

        try (Cursor cursor = db.rawQuery(sql, selectionArgs)) {
            while (cursor != null && cursor.moveToNext()) {
                NganhHang obj = new NganhHang();
                obj.setMa_nh(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNganhHang"))));
                obj.setTen_nh(cursor.getString(cursor.getColumnIndex("ten_nh")));
                obj.setTrangthai_nh(cursor.getString(cursor.getColumnIndex("trangthai_nh")));
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý hoặc ghi log nếu có lỗi
        }

        return list;
    }
}
