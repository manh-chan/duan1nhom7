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

import com.example.duan1nhom7.FragmentNhaCC;
import com.example.duan1nhom7.FragmentSanPham;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterNhaCC extends ArrayAdapter<NhaCungCap> {
    private Context context;
    FragmentNhaCC fragment;
    List<NhaCungCap> list;
    TextView tv_ma_nhacc, tv_ten_nhacc, tv_sdt_nhacc,tv_diachi_nhacc, tv_trangthai_nhacc;
    ImageView imgDel;
    public AdapterNhaCC(@NonNull Context context, FragmentNhaCC fragment, List<NhaCungCap> list) {
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
            v = inflater.inflate(R.layout.item_nhacc, null);
        }
        final NhaCungCap item = list.get(position);
        if (item != null) {

            tv_ma_nhacc = v.findViewById(R.id.tv_ma_nhacc);
            tv_ma_nhacc.setText("Mã Nhà Cung Cấp: " + item.getMa_nhacc() + "");
            tv_ten_nhacc = v.findViewById(R.id.tv_ten_nhacc);
            tv_ten_nhacc.setText("Tên Nhà Cung Cấp: " + item.getTen_nhacc());
            tv_sdt_nhacc = v.findViewById(R.id.tv_sdt_nhacc);
            tv_sdt_nhacc.setText("Số Điện Thoại: " + item.getSdt_nhacc());
            tv_diachi_nhacc = v.findViewById(R.id.tv_diachi_nhacc);
            tv_diachi_nhacc.setText("Địa Chỉ: " + item.getDiachi_nhacc());
            tv_trangthai_nhacc = v.findViewById(R.id.tv_trangthai_nhacc);
            tv_trangthai_nhacc.setText("Trạng Thái: " + item.getTrangthai_nhacc());
// Dynamically set the image based on the product name
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMa_nhacc()));

            }
        });
        return v;
    }

}
