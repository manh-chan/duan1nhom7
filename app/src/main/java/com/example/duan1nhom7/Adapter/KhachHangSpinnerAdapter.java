package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.Model.KhachHang;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.R;

import java.util.ArrayList;

public class KhachHangSpinnerAdapter extends ArrayAdapter<KhachHang> {
    private Context context;
    ArrayList<KhachHang> list;
    TextView tv_makh_spin, tv_tenkh_spin;

    public KhachHangSpinnerAdapter(@NonNull Context context, ArrayList<KhachHang> list) {
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
            v = inflater.inflate(R.layout.item_khachhang_spinner, null);

        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tv_makh_spin = v.findViewById(R.id.tv_makh_spin);
            tv_makh_spin.setText(item.getMa_kh() + ". ");

            tv_tenkh_spin = v.findViewById(R.id.tv_tenkh_spin);
            tv_tenkh_spin.setText(item.getTen_kh());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_khachhang_spinner, null);

        }
        final KhachHang item = list.get(position);
        if (item != null) {
            tv_makh_spin = v.findViewById(R.id.tv_makh_spin);
            tv_makh_spin.setText(item.getMa_kh() + ". ");

            tv_tenkh_spin = v.findViewById(R.id.tv_tenkh_spin);
            tv_tenkh_spin.setText(item.getTen_kh());
        }
        return v;
    }
}
