package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.Model.SanPham;
import com.example.duan1nhom7.R;

import java.util.ArrayList;

public class SanPhamSpinnerAdapter extends ArrayAdapter<SanPham> {
    private Context context;
    ArrayList<SanPham> list;
    TextView tv_masp_spin, tv_tensp_spin;

    public SanPhamSpinnerAdapter(@NonNull Context context, ArrayList<SanPham> list) {
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
            v = inflater.inflate(R.layout.item_sanpham_spinner, null);

        }
        final SanPham item = list.get(position);
        if (item != null) {
            tv_masp_spin = v.findViewById(R.id.tv_masp_spin);
            tv_masp_spin.setText(item.getMa_sp() + ". ");

            tv_tensp_spin = v.findViewById(R.id.tv_tensp_spin);
            tv_tensp_spin.setText(item.getTen_sp());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sanpham_spinner, null);

        }
        final SanPham item = list.get(position);
        if (item != null) {
            tv_masp_spin = v.findViewById(R.id. tv_masp_spin);
            tv_masp_spin.setText(item.getMa_sp() + ". ");

            tv_tensp_spin = v.findViewById(R.id. tv_tensp_spin);
            tv_tensp_spin.setText(item.getTen_sp());
        }
        return v;
    }
}
