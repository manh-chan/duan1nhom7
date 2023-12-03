package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.CongViec;
import com.example.duan1nhom7.Model.HanSuDung;

import java.util.ArrayList;
import java.util.List;

public class CongViecDAO {
    private static SQLiteDatabase db;

    public  CongViecDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(CongViec obj) {
        ContentValues values = new ContentValues();
        //maCongViec,maAdmin,maNhanVien,tieude_cv,ghichu_cv,thoihan_cv,trangthai_cv
        values.put("maCongViec", obj.getMa_cv());
        values.put("maAdmin", obj.getMaadmin_cv());
        values.put("maNhanVien", obj.getManv_cv());
        values.put("tieude_cv", obj.getTieude_cv());
        values.put("ghichu_cv", obj.getGhichu_cv());
        values.put("thoihan_cv", obj.getThoihan_cv());
        values.put("trangthai_cv", obj.getTrangthai_cv());
        return db.insert("CongViec", null, values);
    }

    public static long update(CongViec obj) {
        ContentValues values = new ContentValues();
        values.put("maAdmin", obj.getMaadmin_cv());
        values.put("maNhanVien", obj.getManv_cv());
        values.put("tieude_cv", obj.getTieude_cv());
        values.put("ghichu_cv", obj.getGhichu_cv());
        values.put("thoihan_cv", obj.getThoihan_cv());
        values.put("trangthai_cv", obj.getTrangthai_cv());
        return db.update("CongViec", values, "maCongViec = ?", new String[]{String.valueOf(obj.getMa_cv())});
    }

    public long delete(String id) {
        return db.delete("CongViec", "maCongViec = ?", new String[]{String.valueOf(id)});
    }

    public static List<CongViec> getAll() {
        String sql = "SELECT * FROM CongViec";
        return getData(sql);
    }

    public CongViec getID(String id) {
        String sql = "SELECT * FROM CongViec WHERE maCongViec=?";
        List<CongViec> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    @SuppressLint("Range")
    private static List<CongViec> getData(String sql, String... selectionArgs) {
        List<CongViec> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            CongViec obj = new CongViec();
            //maCongViec,maAdmin,maNhanVien,tieude_cv,ghichu_cv,thoihan_cv,trangthai_cv
            obj.setMa_cv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maCongViec"))));
            obj.setMaadmin_cv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maAdmin"))));
            obj.setManv_cv(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNhanVien"))));
            obj.setTieude_cv(cursor.getString(cursor.getColumnIndex("tieude_cv")));
            obj.setGhichu_cv(cursor.getString(cursor.getColumnIndex("ghichu_cv")));
            obj.setThoihan_cv(cursor.getString(cursor.getColumnIndex("thoihan_cv")));
            obj.setTrangthai_cv(cursor.getString(cursor.getColumnIndex("trangthai_cv")));
            list.add(obj);
        }
        return list;
    }
}
