package com.example.duan1nhom7;

import static androidx.constraintlayout.widget.ConstraintLayoutStates.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

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

import com.example.duan1nhom7.Adapter.AdapterCongViec;
import com.example.duan1nhom7.Adapter.AdapterHanSuDung;
import com.example.duan1nhom7.Adapter.AdminSpinnerAdapter;
import com.example.duan1nhom7.Adapter.NhanVienSpinnerAdapter;
import com.example.duan1nhom7.Adapter.SanPhamSpinnerAdapter;
import com.example.duan1nhom7.DAO.AdminDAO;
import com.example.duan1nhom7.DAO.CongViecDAO;
import com.example.duan1nhom7.DAO.HanSuDungDAO;
import com.example.duan1nhom7.DAO.NhanVienDAO;
import com.example.duan1nhom7.DAO.SanPhamDAO;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.CongViec;
import com.example.duan1nhom7.Model.HanSuDung;
import com.example.duan1nhom7.Model.NhanVien;
import com.example.duan1nhom7.Model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class FragmentCongViec extends Fragment {
    ListView lvCongViec;
    CongViecDAO congviecDAO;
    AdapterCongViec adapter;
    CongViec item;
    List<CongViec> list;

    ImageView btn_thoihan_cv;
    FloatingActionButton fab;
    Dialog dialog;
    EditText ed_ma_cv, ed_tieude_cv , ed_ghichu_cv,ed_thoihan_cv,ed_trangthai_cv;
    Spinner spinnerAdmin,spinnerNhanVien;
    Button btnSave, btnCancel;

    AdminSpinnerAdapter adminspinnerAdapter;
    NhanVienSpinnerAdapter nhanvienspinnerAdapter;
    ArrayList<Admin> listadmin;
    ArrayList<NhanVien> listnhanvien;
    AdminDAO adminDAO;
    NhanVienDAO nhanvienDAO;
    int maAdmin,maNhanVien, position;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;

    private SearchView searchView;


    public FragmentCongViec() {
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
        View v = inflater.inflate(R.layout.activity_fragment_cong_viec, container, false);
        lvCongViec = v.findViewById(R.id.lvCongViec);
        congviecDAO = new CongViecDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvCongViec.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<CongViec>) CongViecDAO.getAll();
        adapter = new AdapterCongViec(getActivity(), this, list);
        lvCongViec.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                congviecDAO.delete(Id);
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

    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_congviec);
        ed_ma_cv = dialog.findViewById(R.id.ed_ma_cv);
        spinnerAdmin = dialog.findViewById(R.id.spin_maadmin_cv);
        spinnerNhanVien = dialog.findViewById(R.id.spin_manv_cv);
        ed_tieude_cv = dialog.findViewById(R.id.ed_tieude_cv);
        ed_ghichu_cv = dialog.findViewById(R.id.ed_ghichu_cv);
        ed_thoihan_cv = dialog.findViewById(R.id.ed_thoihan_cv);
        ed_trangthai_cv = dialog.findViewById(R.id.ed_trangthai_cv);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_cv);
        btnSave = dialog.findViewById(R.id.btnSave_them_cv);
        btn_thoihan_cv = dialog.findViewById(R.id.btn_thoihan_cv);

        listadmin = new ArrayList<Admin>();
        adminDAO = new AdminDAO(context);
        listadmin = (ArrayList<Admin>) AdminDAO.getAll();
        adminspinnerAdapter = new AdminSpinnerAdapter(context, listadmin);
        spinnerAdmin.setAdapter(adminspinnerAdapter);

        listnhanvien = new ArrayList<NhanVien>();
        nhanvienDAO = new NhanVienDAO(context);
        listnhanvien = (ArrayList<NhanVien>) NhanVienDAO.getAll();
        nhanvienspinnerAdapter = new NhanVienSpinnerAdapter(context, listnhanvien);
        spinnerNhanVien.setAdapter(nhanvienspinnerAdapter);


        btn_thoihan_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        // lay ma admin
        spinnerAdmin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maAdmin = listadmin.get(position).getMa_admin();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerNhanVien.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
            ed_ma_cv.setText(String.valueOf(item.getMa_cv()));
            ed_tieude_cv.setText(String.valueOf(item.getTieude_cv()));
            ed_ghichu_cv.setText(String.valueOf(item.getGhichu_cv()));
            ed_trangthai_cv.setText(String.valueOf(item.getTrangthai_cv()));
            ed_thoihan_cv.setText(String.valueOf(item.getThoihan_cv()));
            for (int i = 0; i < listadmin.size(); i++)
                if (item.getMaadmin_cv() == (listadmin.get(i).getMa_admin())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);


            spinnerNhanVien.setSelection(position);
            for (int i = 0; i < listnhanvien.size(); i++)
                if (item.getManv_cv() == (listnhanvien.get(i).getMa_nv())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spinnerNhanVien.setSelection(position);
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
                item = new CongViec();
                item.setMa_cv(parseInt(ed_ma_cv.getText().toString(), 0));
                item.setMaadmin_cv(maAdmin);
                item.setManv_cv(maNhanVien);
                item.setTieude_cv(ed_tieude_cv.getText().toString());
                item.setGhichu_cv(ed_ghichu_cv.getText().toString());
                item.setThoihan_cv(ed_thoihan_cv.getText().toString());
                item.setTrangthai_cv(ed_trangthai_cv.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (CongViecDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_cv(Integer.parseInt(ed_ma_cv.getText().toString()));
                        if (CongViecDAO.update(item) > 0) {
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
        if (ed_ghichu_cv.getText().length() == 0 || ed_tieude_cv.getText().length() == 0 ) {
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
        List<CongViec> listSearch = new ArrayList<>();
        for (CongViec congviec : list) {
            if (String.valueOf(congviec.getMa_cv()).contains(query.toLowerCase())) {
                listSearch.add(congviec);
            }
        }
        adapter = new AdapterCongViec(getActivity(), this, listSearch);
        lvCongViec.setAdapter(adapter);

    }

    // Sắp xếp sách theo tên tăng dần
    private void sortBooksByNameAscending() {
        Collections.sort(list, new Comparator<CongViec>() {
            @Override
            public int compare(CongViec sp1, CongViec sp2) {
                return sp1.getTieude_cv().compareTo(sp2.getTieude_cv());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<CongViec>() {
            @Override
            public int compare(CongViec sp1, CongViec sp2) {
                return sp2.getTieude_cv().compareTo(sp1.getTieude_cv());
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
    DatePickerDialog.OnDateSetListener mThoiHan = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            if (ed_thoihan_cv != null) {
                GregorianCalendar c = new GregorianCalendar(mYear, mMonth, mDay);
                ed_thoihan_cv.setText(sdf.format(c.getTime()));
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
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), mThoiHan, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

}