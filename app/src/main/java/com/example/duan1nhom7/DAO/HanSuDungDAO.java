package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.HanSuDung;

import java.util.ArrayList;
import java.util.List;

public class HanSuDungDAO {
    private static SQLiteDatabase db;

    public HanSuDungDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(HanSuDung obj) {
        ContentValues values = new ContentValues();
        values.put("maHanSuDung", obj.getMa_hsd());
        values.put("maSanPham", obj.getMasp_hsd());
        values.put("ngaysx_hsd", obj.getNgaysx_hsd());
        values.put("soluong_hsd", obj.getSoluong_hsd());
        return db.insert("HanSuDung", null, values);
    }

    public static long update(HanSuDung obj) {
        ContentValues values = new ContentValues();
        values.put("maSanPham", obj.getMasp_hsd());
        values.put("ngaysx_hsd", obj.getNgaysx_hsd());
        values.put("soluong_hsd", obj.getSoluong_hsd());
        return db.update("HanSuDung", values, "maHanSuDung = ?", new String[]{String.valueOf(obj.getMa_hsd())});
    }

    public long delete(String id) {
        return db.delete("HanSuDung", "maHanSuDung = ?", new String[]{String.valueOf(id)});
    }

    public static List<HanSuDung> getAll() {
        String sql = "SELECT * FROM HanSuDung";
        return getData(sql);
    }

    public HanSuDung getID(String id) {
        String sql = "SELECT * FROM HanSuDung WHERE maHanSuDung=?";
        List<HanSuDung> list = getData(sql, id);
        return list.get(0);
    }


    @SuppressLint("Range")
    private static List<HanSuDung> getData(String sql, String... selectionArgs) {
        List<HanSuDung> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HanSuDung obj = new HanSuDung();
            obj.setMa_hsd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHanSuDung"))));
            obj.setMasp_hsd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maSanPham"))));
            obj.setNgaysx_hsd(cursor.getString(cursor.getColumnIndex("ngaysx_hsd")));
            obj.setSoluong_hsd(cursor.getString(cursor.getColumnIndex("soluong_hsd")));
            list.add(obj);
        }
        return list;
    }
}
