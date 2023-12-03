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
import com.example.duan1nhom7.FragmentQuanLyNV;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhanVien;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterNhanVien extends ArrayAdapter<NhanVien> {
    private Context context;
    FragmentQuanLyNV fragment;
    List<NhanVien> list;
    TextView tv_ma_nv, tv_ten_nv, tv_email_nv ,tv_sdt_nv,tv_pass_nv;
    ImageView imgDel;
    public AdapterNhanVien(@NonNull Context context, FragmentQuanLyNV fragment, List<NhanVien> list) {
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
            v = inflater.inflate(R.layout.item_nhanvien, null);
        }
        final NhanVien item = list.get(position);
        if (item != null) {

            tv_ma_nv = v.findViewById(R.id.tv_ma_nv);
            tv_ma_nv .setText("Mã Nhân Viên: " + item.getMa_nv() + "");
            tv_ten_nv = v.findViewById(R.id.tv_ten_nv);
            tv_ten_nv.setText("Tên Nhân Viên: " + item.getHoten_nv());
            tv_email_nv = v.findViewById(R.id.tv_email_nv);
            tv_email_nv.setText("Email: " + item.getEmail_nv());
            tv_sdt_nv = v.findViewById(R.id.tv_sdt_nv);
            tv_sdt_nv.setText("Số Điện Thoại: " + item.getSdt_nv());
            tv_pass_nv= v.findViewById(R.id.tv_pass_nv);
            tv_pass_nv.setText("PassWord: " + item.getMatkhau_nv());
            // Dynamically set the image based on the product name
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
                fragment.xoa(String.valueOf(item.getMa_nv()));

            }
        });
        return v;
    }
}
