package com.example.duan1nhom7;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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

public class FragmentHoaDon extends Fragment {
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

    KhachHangSpinnerAdapter khachhangspinnerAdapter;
    NhanVienSpinnerAdapter nhanvienspinnerAdapter;
    ArrayList<KhachHang> listkhachhang;
    ArrayList<NhanVien> listnhanvien;
    KhachHangDAO khachhangDAO;
    NhanVienDAO nhanvienDAO;
    int ma_KhachHang,maNhanVien, position;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;

    private SearchView searchView;


    public FragmentHoaDon() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.drawer_view1, menu);
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchItem = menu.findItem(R.id.search);
        searchView = (SearchView) searchItem.getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                handleSearch(newText);
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.activity_fragment_hoa_don, container, false);
        lvHoaDon = v.findViewById(R.id.lvHoaDon);
        hoadonDAO = new HoaDonDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvHoaDon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                item = list.get(position);
                openDialog(getActivity(), 1);
                return false;
            }
        });
        return v;


    }

    void capNhatLv() {
        list = (ArrayList<HoaDon>) HoaDonDAO.getAll();
        adapter = new AdapterHoaDon(getActivity(), this, list);
        lvHoaDon.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                HoaDonDAO.delete(Id);
                capNhatLv();
                dialog.cancel();
                Toast.makeText(getContext(), "Đã xóa", Toast.LENGTH_SHORT).show();

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Toast.makeText(getContext(), "Không xóa", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alert = builder.create();
        builder.show();
    }
    //maHoaDon,maNhanVien,maKhachHang,thoigian_hd,soluong_sp,khuyenmai_hd,tongtien_hd
    public void chuyenFragmentCTHoaDon(String maHoaDon , String SoLuongSP , double KhuyenMai , String ThoiGian, double TongTien) {
        // Tạo Fragment mới hoặc truyền dữ liệu cần thiết
        FragmentHoaDonCT fragmentCTHoaDon = new FragmentHoaDonCT();
        Bundle bundle = new Bundle();
        bundle.putString("MA_HOA_DON", maHoaDon);
        bundle.putString("SO_LUONG", String.valueOf(SoLuongSP));
        bundle.putString("KHUYEN_MAI", String.valueOf(KhuyenMai));
        bundle.putString("THOI_GIAN", String.valueOf(ThoiGian));
        bundle.putString("TONG_TIEN", String.valueOf(TongTien));

        fragmentCTHoaDon.setArguments(bundle);

        // Chuyển Fragment
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_hd, fragmentCTHoaDon);
        transaction.addToBackStack(null); // Để có thể quay lại Fragment trước đó
        transaction.commit();
    }

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_hoadon);
        ed_ma_hd = dialog.findViewById(R.id.ed_ma_hd);
        spin_manv_hd = dialog.findViewById(R.id.spin_manv_hd);
        spin_makh_hd = dialog.findViewById(R.id.spin_makh_hd);
        ed_thoigian_hd = dialog.findViewById(R.id. ed_thoigian_hd );
        btn_thoigian_hd = dialog.findViewById(R.id.btn_thoigian_hd);
        ed_soluong_hd = dialog.findViewById(R.id.ed_soluong_hd);
        ed_khuyenmai_hd = dialog.findViewById(R.id.ed_khuyenmai_hd);
        ed_tongtien_hd = dialog.findViewById(R.id.ed_tongtien_hd);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_hd);
        btnSave = dialog.findViewById(R.id.btnSave_them_hd);



        listkhachhang = new ArrayList<KhachHang>();
        khachhangDAO = new KhachHangDAO(context);
        listkhachhang = (ArrayList<KhachHang>) KhachHangDAO.getAll();
        khachhangspinnerAdapter = new KhachHangSpinnerAdapter(context, listkhachhang);
        spin_makh_hd.setAdapter(khachhangspinnerAdapter);

        listnhanvien = new ArrayList<NhanVien>();
        nhanvienDAO = new NhanVienDAO(context);
        listnhanvien = (ArrayList<NhanVien>) NhanVienDAO.getAll();
        nhanvienspinnerAdapter = new NhanVienSpinnerAdapter(context, listnhanvien);
        spin_manv_hd.setAdapter(nhanvienspinnerAdapter);


        btn_thoigian_hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        // lay ma admin
        spin_makh_hd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ma_KhachHang = listkhachhang.get(position).getMa_kh();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_manv_hd.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maNhanVien = listnhanvien.get(position).getMa_nv();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // kiem tra tupe insert hay update
        if (item != null) {
            ed_ma_hd.setText(String.valueOf(item.getMa_hd()));
            ed_thoigian_hd.setText(String.valueOf(item.getThoigian_hd()));
            ed_soluong_hd.setText(String.valueOf(item.getSoluong_hd()));
            ed_khuyenmai_hd.setText(String.valueOf(item.getKhuyenmai_hd()));
            ed_tongtien_hd.setText(String.valueOf(item.getTongtien_hd()));
            for (int i = 0; i < listkhachhang.size(); i++)
                if (item.getMa_kh_hd() == (listkhachhang.get(i).getMa_kh())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);


            spin_manv_hd.setSelection(position);
            for (int i = 0; i < listnhanvien.size(); i++)
                if (item.getMa_nv_hd() == (listnhanvien.get(i).getMa_nv())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spin_manv_hd.setSelection(position);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                item = new HoaDon();
                item.setMa_hd(parseInt(ed_ma_hd.getText().toString(), 0));
                item.setMa_nv_hd(ma_KhachHang);
                item.setMa_kh_hd(maNhanVien);
                item.setThoigian_hd(ed_thoigian_hd.getText().toString());
                item.setSoluong_hd(ed_soluong_hd.getText().toString());
                item.setKhuyenmai_hd(Double.parseDouble(ed_khuyenmai_hd.getText().toString()));
                item.setTongtien_hd(Double.parseDouble(ed_tongtien_hd.getText().toString()));

                if (validate() > 0) {
                    if (type == 0) {
                        if (HoaDonDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_hd(Integer.parseInt(ed_ma_hd.getText().toString()));
                        if (HoaDonDAO.update(item) > 0) {
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Sứa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }

            }
        });
        dialog.show();
    }

    public int validate() {
        int check = 1;
        if (ed_soluong_hd.getText().length() == 0 || ed_tongtien_hd.getText().length() == 0 ) {
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private void handleSearch(String query) {
        List<HoaDon> listSearch = new ArrayList<>();
        for (HoaDon hoadon : list) {
            if (String.valueOf(hoadon.getThoigian_hd()).contains(query.toLowerCase())) {
                listSearch.add(hoadon);
            }
        }
        adapter = new AdapterHoaDon(getActivity(), this, listSearch);
        lvHoaDon.setAdapter(adapter);

    }

    // Sắp xếp sách theo tên tăng dần
    private void sortBooksByNameAscending() {
        Collections.sort(list, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon sp1, HoaDon sp2) {
                return sp1.getThoigian_hd().compareTo(sp2.getThoigian_hd());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<HoaDon>() {
            @Override
            public int compare(HoaDon sp1, HoaDon sp2) {
                return sp2.getThoigian_hd().compareTo(sp1.getThoigian_hd());
//                return sach2.getGiaThue() - sach1.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.asc){
            sortBooksByNameAscending();
            return true;
        }else if(id == R.id.desc){
            sortBooksByNameDescending();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    DatePickerDialog.OnDateSetListener mThoiGian = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            if (ed_thoigian_hd != null) {
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_thoigian_hd.setText(sdf.format(c.getTime()));
            } else {
                Log.e(TAG, "ed_thoihan_cv is null");
            }
        }
    };
    private void showDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),mThoiGian, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}