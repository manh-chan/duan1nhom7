package com.example.duan1nhom7.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.duan1nhom7.FragmentQuanLyNV;
import com.example.duan1nhom7.FragmentThongKe;
import com.example.duan1nhom7.Model.NhanVien;
import com.example.duan1nhom7.Model.ThongKe;
import com.example.duan1nhom7.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterThongKe extends ArrayAdapter<ThongKe> {
    private Context context;
    FragmentThongKe fragment;
    List<ThongKe> list;
    TextView tv_soluong_hd_tk,tv_banhang_hd_tk,tv_khuyenmai_hd_tk,tv_tongtien_hd_tk,
            tv_ngay_tk,tv_tongtien_tk,tv_soluong_tk,tv_banhang_tk,tv_Khuyenmai_tk;
    public AdapterThongKe(@NonNull Context context, FragmentThongKe fragment, List<ThongKe> list) {
        super(context, 0, list);
        this.context = context;
        this.fragment = fragment;
        this.list = list;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_thongke, parent, false);
        }

        ThongKe thongKeNgay = getItem(position);

        if (thongKeNgay != null) {
            tv_soluong_hd_tk= itemView.findViewById(R.id.tv_soluong_hd_tk);
            tv_banhang_hd_tk= itemView.findViewById(R.id. tv_banhang_hd_tk);
            tv_khuyenmai_hd_tk= itemView.findViewById(R.id.tv_khuyenmai_hd_tk);
            tv_tongtien_hd_tk= itemView.findViewById(R.id.tv_tongtien_hd_tk);

            tv_ngay_tk= itemView.findViewById(R.id.tv_ngay_tk);
            tv_tongtien_tk= itemView.findViewById(R.id.tv_tongtien_tk);
            tv_soluong_tk= itemView.findViewById(R.id.tv_soluong_tk);
            tv_banhang_tk= itemView.findViewById(R.id.tv_banhang_tk);
            tv_Khuyenmai_tk= itemView.findViewById(R.id.tv_Khuyenmai_tk);

            // Set giá trị cho TextView
            tv_soluong_hd_tk.setText("Hoa Don\n" + thongKeNgay.getTongSoHoaDon());
            tv_banhang_hd_tk.setText("Ban Hang\n" + thongKeNgay.getTongTien());
            tv_khuyenmai_hd_tk.setText("Khuyen Mai\n" + thongKeNgay.getKhuyenMai());
            tv_tongtien_hd_tk.setText("Tong Tien\n" + thongKeNgay.TongTienDoanhThu());


            tv_ngay_tk.setText("" + thongKeNgay.getNgay());
            tv_tongtien_tk.setText("" + thongKeNgay.TongTienDoanhThu());
            tv_soluong_tk.setText("Khuyen Mai: " + thongKeNgay.getTongSoHoaDon());
            tv_banhang_tk.setText("Tong Tien: " + thongKeNgay.getTongTien());
            tv_Khuyenmai_tk.setText("Khuyen Mai: " + thongKeNgay.getKhuyenMai());
        }

        return itemView;
    }
}
