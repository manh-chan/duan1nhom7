package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.FragmentNganhHang;
import com.example.duan1nhom7.FragmentNhaCC;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterNganhHang extends ArrayAdapter<NganhHang> {
    private Context context;
    FragmentNganhHang fragment;
    List<NganhHang> list;
    TextView tv_ma_nh, tv_ten_nh, tv_trangthai_nh;
    ImageView imgDel;
    public AdapterNganhHang(@NonNull Context context, FragmentNganhHang fragment, List<NganhHang> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_nganhhang, null);
        }
        final NganhHang item = list.get(position);
        if (item != null) {

            tv_ma_nh = v.findViewById(R.id.tv_ma_nh);
            tv_ma_nh.setText("Mã Ngành Hàng: " + item.getMa_nh() + "");
            tv_ten_nh = v.findViewById(R.id.tv_ten_nh);
            tv_ten_nh.setText("Tên Ngành Hàng: " + item.getTen_nh());
            tv_trangthai_nh = v.findViewById(R.id.tv_trangthai_nh);
            tv_trangthai_nh.setText("Trạng Thái: " + item.getTrangthai_nh());
        // Dynamically set the image based on the product name
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMa_nh()));

            }
        });
        return v;
    }
}
