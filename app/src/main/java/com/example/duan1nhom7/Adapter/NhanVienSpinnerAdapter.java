package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.NhanVien;
import com.example.duan1nhom7.R;

import java.util.ArrayList;

public class NhanVienSpinnerAdapter extends ArrayAdapter<NhanVien> {
    private Context context;
    ArrayList<NhanVien> list;
    TextView tv_manv_spin, tv_tennv_spin;

    public NhanVienSpinnerAdapter(@NonNull Context context, ArrayList<NhanVien> list) {
        super(context, 0, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_nhanvien_spinner, null);

        }
        final NhanVien item = list.get(position);
        if (item != null) {
            tv_manv_spin = v.findViewById(R.id.tv_manv_spin);
            tv_manv_spin.setText(item.getMa_nv() + ". ");

            tv_tennv_spin = v.findViewById(R.id.tv_tennv_spin);
            tv_tennv_spin.setText(item.getHoten_nv());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_nhanvien_spinner, null);

        }
        final NhanVien item = list.get(position);
        if (item != null) {
            tv_manv_spin = v.findViewById(R.id.tv_manv_spin);
            tv_manv_spin.setText(item.getMa_nv() + ". ");

            tv_tennv_spin = v.findViewById(R.id.tv_tennv_spin);
            tv_tennv_spin.setText(item.getHoten_nv());
        }
        return v;
    }

}
