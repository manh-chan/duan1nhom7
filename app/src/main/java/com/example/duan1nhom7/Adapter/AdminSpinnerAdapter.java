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
import com.example.duan1nhom7.Model.SanPham;
import com.example.duan1nhom7.R;

import java.util.ArrayList;

public class AdminSpinnerAdapter extends ArrayAdapter<Admin> {
    private Context context;
    ArrayList<Admin> list;
    TextView tv_maadmin_spin, tv_tenadmin_spin;

    public AdminSpinnerAdapter(@NonNull Context context, ArrayList<Admin> list) {
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
            v = inflater.inflate(R.layout.item_admin_spinner, null);

        }
        final Admin item = list.get(position);
        if (item != null) {
            tv_maadmin_spin = v.findViewById(R.id.tv_maadmin_spin);
            tv_maadmin_spin.setText(item.getMa_admin() + ". ");

            tv_tenadmin_spin = v.findViewById(R.id.tv_tenadmin_spin);
            tv_tenadmin_spin.setText(item.getHoten_admin());
        }
        return v;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_admin_spinner, null);

        }
        final Admin item = list.get(position);
        if (item != null) {
            tv_maadmin_spin = v.findViewById(R.id.tv_maadmin_spin);
            tv_maadmin_spin.setText(item.getMa_admin() + ". ");

            tv_tenadmin_spin = v.findViewById(R.id.tv_tenadmin_spin);
            tv_tenadmin_spin.setText(item.getHoten_admin());
        }
        return v;
    }
}
