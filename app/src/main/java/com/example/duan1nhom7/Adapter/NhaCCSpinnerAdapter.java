package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.R;

import java.util.ArrayList;

public class NhaCCSpinnerAdapter extends ArrayAdapter<NhaCungCap> {
    private Context context;
    ArrayList<NhaCungCap> list;
    TextView tv_manhacc_spin, tv_tennhacc_spin;

    public NhaCCSpinnerAdapter(@NonNull Context context, ArrayList<NhaCungCap> list) {
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
            v = inflater.inflate(R.layout.item_nhacc_spinner, null);

        }
        final NhaCungCap item = list.get(position);
        if (item != null) {
            tv_manhacc_spin = v.findViewById(R.id.tv_manhacc_spin);
            tv_manhacc_spin.setText(item.getMa_nhacc() + ". ");

            tv_tennhacc_spin = v.findViewById(R.id.tv_tennhacc_spin);
            tv_tennhacc_spin.setText(item.getTen_nhacc());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_nhacc_spinner, null);

        }
        final NhaCungCap item = list.get(position);
        if (item != null) {
            tv_manhacc_spin = v.findViewById(R.id.tv_manhacc_spin);
            tv_manhacc_spin.setText(item.getMa_nhacc() + ". ");

            tv_tennhacc_spin = v.findViewById(R.id.tv_tennhacc_spin);
            tv_tennhacc_spin.setText(item.getTen_nhacc());
        }
        return v;
    }

}
