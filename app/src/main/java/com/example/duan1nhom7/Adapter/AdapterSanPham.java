package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.DAO.NhaCungCapDAO;
import com.example.duan1nhom7.FragmentSanPham;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.Model.SanPham;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterSanPham extends ArrayAdapter<SanPham> {
    private Context context;
    FragmentSanPham fragment;
    List<SanPham> list;

ImageView  imgProduct;
    TextView tv_ma_sp ,tv_ma_nhacc, tv_ma_nh, tv_ten_sp,tv_gianhap_sp, tv_giaban_sp,tv_trangthai_sp ,tv_soluong_sp;
    ImageView imgDel;
    public AdapterSanPham(@NonNull Context context, FragmentSanPham fragment, List<SanPham> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment    = fragment;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.item_sanpham, null);
        }
        final SanPham item = list.get(position);
        if (item != null) {




            NhaCungCapDAO nhaCungCapDAO = new NhaCungCapDAO(context);
            NganhHangDAO nganhHangDAO = new NganhHangDAO(context);
            NhaCungCap nhaCungCap = nhaCungCapDAO.getID(String.valueOf(item.getMa_nhacc()));
            NganhHang nganhHang = nganhHangDAO.getID(String.valueOf(item.getMa_nh()));

            tv_ma_sp = v.findViewById(R.id.tv_ma_sp);
            tv_ma_sp.setText("Mã san pham: " + item.getMa_sp() + "");

            tv_ma_nhacc = v.findViewById(R.id.tv_ma_nhacc);
            tv_ma_nh = v.findViewById(R.id.tv_ma_nh);
            // Kiểm tra nhaCungCap và nganhHang có giá trị hay không
            if (nhaCungCap != null && nganhHang != null) {

                // Kiểm tra xem tv_ma_nh và tv_ma_nhacc có giá trị hay không
                if (tv_ma_nh != null && tv_ma_nhacc != null) {
                    tv_ma_nh.setText("Mã nganh hang: " + nganhHang.getMa_nh() + "");
                    tv_ma_nhacc.setText("Mã nha cung cap: " + nhaCungCap.getMa_nhacc() + "");
                } else {
                    Log.e("AdapterSanPham", "tv_ma_nh or tv_ma_nhacc is null");
                }
            } else {
                Log.e("AdapterSanPham", "nhaCungCap or nganhHang is null");
            }


            tv_ten_sp = v.findViewById(R.id.tv_ten_sp);
            tv_ten_sp.setText("Tên san pham: " + item.getTen_sp());
            tv_gianhap_sp = v.findViewById(R.id.tv_gianhap_sp);
            tv_gianhap_sp.setText("Giá nhap: " + item.getGianhap_sp());
            tv_giaban_sp = v.findViewById(R.id.tv_giaban_sp);
            tv_giaban_sp.setText("Gia ban: " +item.getGiaban_sp());
            tv_soluong_sp = v.findViewById(R.id.tv_soluong_sp);
            tv_soluong_sp.setText("Năm xuất bản: "+item.getSoluong_sp());
            tv_trangthai_sp = v.findViewById(R.id.tv_trangthai_sp);
            tv_trangthai_sp.setText("Năm xuất bản: "+item.getTrangthai_sp());
            imgProduct = v.findViewById(R.id.img_sp);
// Dynamically set the image based on the product name
            int imageResId = getImageResourceId(item.getTen_sp());
            imgProduct.setImageResource(imageResId);
            imgDel = v.findViewById(R.id.imgDeleteLS);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pt xóa
               fragment.xoa(String.valueOf(item.getMa_sp()));

            }
        });
        return v;
    }
    private int getImageResourceId(String productName) {
        switch (productName) {
            case "CoCa":
                return R.drawable.img_coca;
            case "PepSi":
                return R.drawable.img_pepsi;


            case "NuocDua":
                return R.drawable.img_dua;
            default:
                return R.drawable.logo;
        }
    }
}

