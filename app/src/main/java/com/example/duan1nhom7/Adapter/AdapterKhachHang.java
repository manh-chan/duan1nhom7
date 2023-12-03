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
import com.example.duan1nhom7.FragmentQuanLyKH;
import com.example.duan1nhom7.Model.KhachHang;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterKhachHang extends ArrayAdapter<KhachHang> {

    private Context context;
    FragmentQuanLyKH fragment;
    List<KhachHang> list;
    TextView tv_ma_kh, tv_ten_kh, tv_sdt_kh;
    ImageView imgDel;
    public AdapterKhachHang(@NonNull Context context, FragmentQuanLyKH fragment, List<KhachHang> list) {
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
            v = inflater.inflate(R.layout.item_khachhang, null);
        }
        final KhachHang item = list.get(position);
        if (item != null) {

            tv_ma_kh = v.findViewById(R.id.tv_ma_kh);
            tv_ma_kh.setText("Mã Khách Hàng: " + item.getMa_kh() + "");
            tv_ten_kh = v.findViewById(R.id.tv_ten_kh);
            tv_ten_kh.setText("Tên Khách Hàng: " + item.getTen_kh());
            tv_sdt_kh = v.findViewById(R.id.tv_sdt_kh);
            tv_sdt_kh.setText("Số Điện Thoại: " + item.getSdt_kh());
            // Dynamically set the image based on the product name
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMa_kh()));

            }
        });
        return v;
    }
}
