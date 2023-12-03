package com.example.duan1nhom7.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duan1nhom7.Database.DBHelper;
import com.example.duan1nhom7.Model.CongViec;
import com.example.duan1nhom7.Model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private static SQLiteDatabase db;

    public  HoaDonDAO(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public static long insert(HoaDon obj) {
        ContentValues values = new ContentValues();
//        maHoaDon,maNhanVien,maKhachHang,thoigian_hd,soluong_sp,khuyenmai_hd,tongtien_hd
        values.put("maHoaDon", obj.getMa_hd());
        values.put("maNhanVien", obj.getMa_nv_hd());
        values.put("maKhachHang", obj.getMa_kh_hd());
        values.put("thoigian_hd", obj.getThoigian_hd());
        values.put("soluong_sp", obj.getSoluong_hd());
        values.put("khuyenmai_hd", obj.getKhuyenmai_hd());
        values.put("tongtien_hd", obj.getTongtien_hd());
        return db.insert("HoaDon", null, values);
    }

    public static long update(HoaDon obj) {
        ContentValues values = new ContentValues();
        values.put("maNhanVien", obj.getMa_nv_hd());
        values.put("maKhachHang", obj.getMa_kh_hd());
        values.put("thoigian_hd", obj.getThoigian_hd());
        values.put("soluong_sp", obj.getSoluong_hd());
        values.put("khuyenmai_hd", obj.getKhuyenmai_hd());
        values.put("tongtien_hd", obj.getTongtien_hd());
        return db.update("HoaDon", values, "maHoaDon = ?", new String[]{String.valueOf(obj.getMa_hd())});
    }

    public static long delete(String id) {
        return db.delete("HoaDon", "maHoaDon = ?", new String[]{String.valueOf(id)});
    }

    public static List<HoaDon> getAll() {
        String sql = "SELECT * FROM HoaDon";
        return getData(sql);
    }

    public HoaDon getID(String id) {
        String sql = "SELECT * FROM HoaDon WHERE maHoaDon=?";
        List<HoaDon> list = getData(sql, id);
        if (!list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }


    @SuppressLint("Range")
    private static List<HoaDon> getData(String sql, String... selectionArgs) {
        List<HoaDon> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectionArgs);
        while (cursor.moveToNext()) {
            HoaDon obj = new HoaDon();
//        maHoaDon,maNhanVien,maKhachHang,thoigian_hd,soluong_sp,khuyenmai_hd,tongtien_hd
            obj.setMa_hd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maHoaDon"))));
            obj.setMa_nv_hd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maNhanVien"))));
            obj.setMa_kh_hd(Integer.parseInt(cursor.getString(cursor.getColumnIndex("maKhachHang"))));
            obj.setThoigian_hd(cursor.getString(cursor.getColumnIndex("thoigian_hd")));
            obj.setSoluong_hd(cursor.getString(cursor.getColumnIndex("soluong_sp")));
            obj.setKhuyenmai_hd(Double.parseDouble(cursor.getString(cursor.getColumnIndex("khuyenmai_hd"))));
            obj.setTongtien_hd(Double.parseDouble(cursor.getString(cursor.getColumnIndex("tongtien_hd"))));
            list.add(obj);
        }
        return list;
    }
}
