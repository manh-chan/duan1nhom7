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

import com.example.duan1nhom7.DAO.AdminDAO;
import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.DAO.NhaCungCapDAO;
import com.example.duan1nhom7.DAO.NhanVienDAO;
import com.example.duan1nhom7.DAO.SanPhamDAO;
import com.example.duan1nhom7.FragmentCongViec;
import com.example.duan1nhom7.FragmentHanSuDung;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.CongViec;
import com.example.duan1nhom7.Model.HanSuDung;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.Model.NhanVien;
import com.example.duan1nhom7.Model.SanPham;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterCongViec extends ArrayAdapter<CongViec> {
    private Context context;
    FragmentCongViec fragment;
    List<CongViec> list;
    NhanVienDAO nhanvienDAO;
    AdminDAO adminDAO;
    ImageView imgProduct;
    TextView tv_ma_cv ,tv_maad_cv,tv_manv_cv, tv_tieude_cv,tv_ghichu_cv,tv_thoihan_cv,tv_trangthai_cv;
    ImageView imgDel;
    public AdapterCongViec(@NonNull Context context, FragmentCongViec fragment, List<CongViec> list) {
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
            v = inflater.inflate(R.layout.item_congviec, null);
        }
        final CongViec item = list.get(position);
        if (item != null) {
            // Khởi tạo các view ở đây
            tv_ma_cv = v.findViewById(R.id.tv_ma_cv);
            tv_maad_cv = v.findViewById(R.id.tv_maad_cv);

            tv_tieude_cv = v.findViewById(R.id.tv_tieude_cv);
            tv_ghichu_cv = v.findViewById(R.id.tv_ghichu_cv);
            tv_thoihan_cv = v.findViewById(R.id.tv_thoihan_cv);
            tv_trangthai_cv = v.findViewById(R.id.tv_trangthai_cv);
            imgDel = v.findViewById(R.id.imgDeleteLS); // Khởi tạo imgDel ở đây

            adminDAO = new AdminDAO(context);
            Admin admin = adminDAO.getID(String.valueOf(item.getMaadmin_cv()));
            if (admin != null) {
                tv_maad_cv = v.findViewById(R.id.tv_maad_cv);
                tv_maad_cv.setText("Ma Quan Ly: " + admin.getMa_admin());
            }



            nhanvienDAO = new NhanVienDAO(context);
            NhanVien nhanvien = nhanvienDAO.getID(String.valueOf(item.getManv_cv()));
            if (nhanvien != null) {
                tv_manv_cv = v.findViewById(R.id.tv_manv_cv);
                tv_manv_cv.setText("Ma Nhan Vien: " + nhanvien.getMa_nv());
            }


            tv_ma_cv.setText("Mã Công Việc: " + item.getMa_cv() + "");
            tv_tieude_cv.setText("Tiêu Đề: " + item.getTieude_cv());
            tv_ghichu_cv.setText("Ghi Chú: " + item.getGhichu_cv());
            tv_thoihan_cv.setText("Thời Hạn: " + item.getThoihan_cv());
            tv_trangthai_cv.setText("Trạng Thái: " + item.getTrangthai_cv());

            // Thiết lập người nghe sự kiện cho imgDel
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Phương thức xóa
                    fragment.xoa(String.valueOf(item.getMa_cv()));
                }
            });
        }
        return v;
    }
}
