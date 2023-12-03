package com.example.duan1nhom7;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1nhom7.Adapter.AdapterHoaDon;
import com.example.duan1nhom7.Adapter.KhachHangSpinnerAdapter;
import com.example.duan1nhom7.Adapter.NhanVienSpinnerAdapter;
import com.example.duan1nhom7.DAO.HoaDonDAO;
import com.example.duan1nhom7.DAO.KhachHangDAO;
import com.example.duan1nhom7.DAO.NhanVienDAO;
import com.example.duan1nhom7.Model.HoaDon;
import com.example.duan1nhom7.Model.KhachHang;
import com.example.duan1nhom7.Model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class FragmentHoaDonCT extends Fragment {
    ListView lvHoaDon;
    HoaDonDAO hoadonDAO;
    AdapterHoaDon adapter;
    HoaDon item;
    List<HoaDon> list;

    ImageView btn_thoigian_hd;
    FloatingActionButton fab;
    Dialog dialog;
    EditText ed_ma_hd, ed_thoigian_hd , ed_soluong_hd,ed_khuyenmai_hd,ed_tongtien_hd;
    Spinner spin_manv_hd,spin_makh_hd;
    Button btnSave, btnCancel;
    TextView tvMaHDCT,tvSoLuongSPHDCT,tvTongTienHDCT,tvMaSPHDCT,tvThoiGian,tvKhuyenMaiHDCT;
    KhachHangSpinnerAdapter khachhangspinnerAdapter;
    NhanVienSpinnerAdapter nhanvienspinnerAdapter;
    ArrayList<KhachHang> listkhachhang;
    ArrayList<NhanVien> listnhanvien;
    KhachHangDAO khachhangDAO;
    NhanVienDAO nhanvienDAO;
    int maKhachHang,maNhanVien, position;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;

    private SearchView searchView;


    public FragmentHoaDonCT() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        menu.clear(); // Xóa tất cả các item của menu
        super.onPrepareOptionsMenu(menu);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_fragment_hoa_don_ct, container, false);
        tvMaHDCT = v.findViewById(R.id.tv_mahd_ct);
        tvSoLuongSPHDCT = v.findViewById(R.id.tv_soluongsp_hdct);
        tvTongTienHDCT = v.findViewById(R.id.tv_tongtien_hdct);
        tvMaSPHDCT = v.findViewById(R.id.tv_masp_hdct);
        tvThoiGian = v.findViewById(R.id.tv_thoigian_hd);
        tvKhuyenMaiHDCT = v.findViewById(R.id.tv_khuyenmai_hdct);

        // Nhận dữ liệu từ Bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            String maHoaDon = bundle.getString("MA_HOA_DON", "");
            String SoLuongSp = bundle.getString("SO_LUONG", "");
            String KhuyenMai = bundle.getString("KHUYEN_MAI", "");
            String ThoiGian = bundle.getString("THOI_GIAN", "");
            String TongTien = bundle.getString("TONG_TIEN", "");

            // Bạn có thể sử dụng maHoaDon để lấy dữ liệu từ CSDL hoặc bất cứ nguồn dữ liệu nào khác
            // Dưới đây chỉ là ví dụ, bạn phải thay thế dữ liệu thực tế từ nguồn của bạn
            tvMaHDCT.setText(maHoaDon);
            tvSoLuongSPHDCT.setText(SoLuongSp);  // Giả sử số lượng sản phẩm là 5
            tvTongTienHDCT.setText(TongTien);
            tvThoiGian.setText(ThoiGian);
            tvKhuyenMaiHDCT.setText(KhuyenMai);  // Thay bằng khuyến mãi thực tế
        }
        return v;
    }



}