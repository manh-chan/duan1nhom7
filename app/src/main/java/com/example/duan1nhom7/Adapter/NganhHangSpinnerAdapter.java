package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.R;

import java.util.ArrayList;

public class NganhHangSpinnerAdapter extends ArrayAdapter<NganhHang> {
    private Context context;
    ArrayList<NganhHang> list;
    TextView tv_manh_spin, tv_tennh_spin;

    public NganhHangSpinnerAdapter(@NonNull Context context, ArrayList<NganhHang> list) {
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
            v = inflater.inflate(R.layout.item_nganhhang_spinner, null);

        }
        final NganhHang item = list.get(position);
        if (item != null) {
            tv_manh_spin = v.findViewById(R.id.tv_manh_spin);
            tv_manh_spin.setText(item.getMa_nh() + ". ");

            tv_tennh_spin = v.findViewById(R.id.tv_tennh_spin);
            tv_tennh_spin.setText(item.getTen_nh());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_nganhhang_spinner, null);

        }
        final NganhHang item = list.get(position);
        if (item != null) {
            tv_manh_spin = v.findViewById(R.id.tv_manh_spin);
            tv_manh_spin.setText(item.getMa_nh() + ". ");

            tv_tennh_spin = v.findViewById(R.id.tv_tennh_spin);
            tv_tennh_spin.setText(item.getTen_nh());
        }
        return v;
    }
}
