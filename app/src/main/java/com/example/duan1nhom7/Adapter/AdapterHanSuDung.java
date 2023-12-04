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
import com.example.duan1nhom7.DAO.SanPhamDAO;
import com.example.duan1nhom7.FragmentHanSuDung;
import com.example.duan1nhom7.FragmentSanPham;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.HanSuDung;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.Model.SanPham;
import com.example.duan1nhom7.R;

import java.util.List;

public class AdapterHanSuDung extends ArrayAdapter<HanSuDung> {
    private Context context;
    FragmentHanSuDung fragment;
    List<HanSuDung> list;
    SanPhamDAO sanphamDAO;
    ImageView imgProduct;
    TextView tv_ma_hsd ,tv_masp_hsd, tv_ngaysx_hsd, tv_soluong_hsd;
    ImageView imgDel;
    public AdapterHanSuDung(@NonNull Context context,  FragmentHanSuDung fragment, List<HanSuDung> list) {
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
            v = inflater.inflate(R.layout.item_hansudung, null);
        }
        final HanSuDung item = list.get(position);
        if (item != null) {
            // Khởi tạo các view ở đây
            sanphamDAO = new SanPhamDAO(context);
            SanPham sanPham = sanphamDAO.getID(String.valueOf(item.getMasp_hsd()));

            tv_ma_hsd = v.findViewById(R.id.tv_ma_hsd);
            tv_ngaysx_hsd = v.findViewById(R.id.tv_ngaysx_hsd);
            tv_soluong_hsd = v.findViewById(R.id.tv_soluong_hsd);
            imgDel = v.findViewById(R.id.imgDeleteLS); // Khởi tạo imgDel ở đây
            if (sanPham != null) {
                tv_masp_hsd = v.findViewById(R.id.tv_masp_hsd);
                tv_masp_hsd.setText("Ma San Pham: " + sanPham.getMa_sp());
            }

            tv_ma_hsd.setText("Mã Hạn Sử Dụng: " + item.getMa_hsd() + "");
            tv_ngaysx_hsd.setText("Ngày sản xuất" + item.getNgaysx_hsd());
            tv_soluong_hsd.setText("Giá Nhập: " + item.getSoluong_hsd());

            // Thiết lập người nghe sự kiện cho imgDel
            imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Phương thức xóa
                    fragment.xoa(String.valueOf(item.getMa_hsd()));
                }
            });
        }
        return v;
    }
}
