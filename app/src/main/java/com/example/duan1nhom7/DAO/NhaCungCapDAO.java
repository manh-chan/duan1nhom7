package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.Model.SanPham;

import java.util.ArrayList;
import java.util.List;

public class NhaCungCapDAO {
    private static SQLiteDatabase db;
    public NhaCungCapDAO (Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(NhaCungCap obj) {
        ContentValues values = new ContentValues();
        values.put("maNhaCC", obj.getMa_nhacc());
        values.put("ten_nhacc", obj.getTen_nhacc());
        values.put("sdt_nhacc", obj.getSdt_nhacc());
        values.put("diachi_nhacc", obj.getDiachi_nhacc());
        values.put("trangthai_nhacc", obj.getTrangthai_nhacc());
        return db.insert("NhaCungCap", null, values);
    }

    public static long update(NhaCungCap obj) {
        ContentValues values = new ContentValues();
        values.put("ten_nhacc", obj.getTen_nhacc());
        values.put("sdt_nhacc", obj.getSdt_nhacc());
        values.put("diachi_nhacc", obj.getDiachi_nhacc());
        values.put("trangthai_nhacc", obj.getTrangthai_nhacc());
        return db.update("NhaCungCap", values, "maNhaCC = ?", new String[]{String.valueOf(obj.getMa_nhacc())});
    }

    public static long delete(String id) {
        return db.delete("NhaCungCap", "maNhaCC = ?", new String[]{String.valueOf(id)});
    }

    public List<NhaCungCap> getAll() {
        String sql = "SELECT * FROM NhaCungCap";
        return getData(sql);
    }

    public NhaCungCap getID(String id) {
        String sql = "SELECT * FROM NhaCungCap WHERE maNhaCC=?";
        List<NhaCungCap> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        } else {
            // Trường hợp không tìm thấy dữ liệu với id cung cấp
            return null; // hoặc có thể trả về giá trị mặc định khác tùy vào yêu cầu của bạn
        }
    }

    @SuppressLint("Range")
    private List<NhaCungCap> getData(String sql, String... selectionArgs) {
        List<NhaCungCap> list = new ArrayList<>();

        try (Cursor cursor = db.rawQuery(sql, selectionArgs)) {
            while (cursor != null && cursor.moveToNext()) {
                NhaCungCap obj = new NhaCungCap();
                obj.setMa_nhacc(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNhaCC"))));
                obj.setTen_nhacc(cursor.getString(cursor.getColumnIndex("ten_nhacc")));
                obj.setSdt_nhacc(cursor.getString(cursor.getColumnIndex("sdt_nhacc")));
                obj.setDiachi_nhacc(cursor.getString(cursor.getColumnIndex("diachi_nhacc")));
                obj.setTrangthai_nhacc(cursor.getString(cursor.getColumnIndex("trangthai_nhacc")));
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Xử lý hoặc ghi log nếu có lỗi
        }

        return list;
    }
}
