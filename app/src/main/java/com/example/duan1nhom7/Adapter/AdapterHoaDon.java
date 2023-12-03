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

import com.example.duan1nhom7.DAO.AdminDAO;
import com.example.duan1nhom7.DAO.KhachHangDAO;
import com.example.duan1nhom7.DAO.NhanVienDAO;
import com.example.duan1nhom7.FragmentCongViec;
import com.example.duan1nhom7.FragmentHoaDon;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.CongViec;
import com.example.duan1nhom7.Model.HoaDon;
import com.example.duan1nhom7.Model.KhachHang;
import com.example.duan1nhom7.Model.NhanVien;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterHoaDon extends ArrayAdapter<HoaDon> {
    private Context context;
    FragmentHoaDon fragment;
    List<HoaDon> list;
    NhanVienDAO nhanvienDAO;
    KhachHangDAO khachhangDAO;
    ImageView imgProduct;
    TextView tv_ma_hd ,tv_manv_hd,tv_makh_hd, tv_thoigian_hd,tv_soluong_hd,tv_khuyenmai_hd,tv_tongtien_hd;
    ImageView imgDel,imgHoaDonCT;
    public AdapterHoaDon(@NonNull Context context, FragmentHoaDon fragment, List<HoaDon> list) {
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
            v = inflater.inflate(R.layout.item_hoadon, null);
        }
        final HoaDon item = list.get(position);
        if (item != null) {
            // Khởi tạo các view ở đây
            tv_ma_hd = v.findViewById(R.id. tv_ma_hd);
            tv_thoigian_hd = v.findViewById(R.id.tv_thoigian_hd);
            tv_soluong_hd = v.findViewById(R.id.tv_soluong_hd);
            tv_khuyenmai_hd = v.findViewById(R.id.tv_khuyenmai_hd);
            tv_tongtien_hd = v.findViewById(R.id. tv_tongtien_hd);
            imgDel = v.findViewById(R.id.imgDeleteLS); // Khởi tạo imgDel ở đây
            imgHoaDonCT = v.findViewById(R.id.imgCTHoaDon);

            nhanvienDAO = new NhanVienDAO(context);
            NhanVien nhanvien = nhanvienDAO.getID(String.valueOf(item.getMa_nv_hd()));
            if (nhanvien != null) {
                tv_manv_hd= v.findViewById(R.id.tv_manv_hd);
                tv_manv_hd.setText("Ma Nhan Vien: " + nhanvien.getMa_nv());
            }

            khachhangDAO = new KhachHangDAO(context);
            KhachHang khachHang = khachhangDAO.getID(String.valueOf(item.getMa_kh_hd()));
            if (khachHang != null) {
                tv_makh_hd  = v.findViewById(R.id.tv_makh_hd);
                tv_makh_hd.setText("Ma Khach Hang: " + khachHang.getMa_kh());
            }



            tv_ma_hd.setText("Mã hoa don: " + item.getMa_hd() + "");
            tv_thoigian_hd.setText("Thoi Gian: " + item.getThoigian_hd());
            tv_soluong_hd .setText("So luong san pham: " + item.getSoluong_hd());
            tv_khuyenmai_hd.setText("Khuyen Mai: " + item.getKhuyenmai_hd());
            tv_tongtien_hd.setText("Tong Tien: " + item.getTongtien_hd());
            imgHoaDonCT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Gọi phương thức chuyển Fragment khi nút được nhấn
                    fragment.chuyenFragmentCTHoaDon(String.valueOf(item.getMa_hd()) ,item.getSoluong_hd() ,item.getKhuyenmai_hd(),item.getThoigian_hd(),item.getTongtien_hd() );
                }
            });
            // Thiết lập người nghe sự kiện cho imgDel
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Phương thức xóa
                    fragment.xoa(String.valueOf(item.getMa_hd()));
                }
            });

        }
        return v;
    }
}
