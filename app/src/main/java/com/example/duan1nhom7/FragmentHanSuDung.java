package com.example.duan1nhom7;

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

import com.example.duan1nhom7.Adapter.AdapterHanSuDung;
import com.example.duan1nhom7.Adapter.AdapterSanPham;
import com.example.duan1nhom7.Adapter.SanPhamSpinnerAdapter;
import com.example.duan1nhom7.DAO.HanSuDungDAO;
import com.example.duan1nhom7.DAO.SanPhamDAO;
import com.example.duan1nhom7.Model.HanSuDung;
import com.example.duan1nhom7.Model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.List;

public class FragmentHanSuDung extends Fragment {
    ListView lvHanSuDung;
    HanSuDungDAO hansudungDAO;
    AdapterHanSuDung adapter;
    HanSuDung item;
    List<HanSuDung> list;

    ImageView btn_ngaysx;
    FloatingActionButton fab;
    Dialog dialog;
    EditText ed_ma_hsd, ed_masp_hsd, ed_ngaysx_hsd, ed_soluong_hsd;
    Spinner spinner;
    Button btnSave, btnCancel;

    SanPhamSpinnerAdapter spinnerAdapter;
    ArrayList<SanPham> listsanpham;
    SanPhamDAO sanphamDAO;
    SanPham sanpham;
    int maSanPham, position;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    int mYear,mMonth,mDay;

    private SearchView searchView;


    public FragmentHanSuDung() {
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
        View v = inflater.inflate(R.layout.activity_fragment_han_su_dung, container, false);
        lvHanSuDung = v.findViewById(R.id.lvHanSuDung);
        hansudungDAO = new HanSuDungDAO(getActivity());
        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvHanSuDung.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = (ArrayList<HanSuDung>) HanSuDungDAO.getAll();
        adapter = new AdapterHanSuDung(getActivity(), this, list);
        lvHanSuDung.setAdapter(adapter);
    }

    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                hansudungDAO.delete(Id);
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
        dialog.setContentView(R.layout.dialog_them_hansudung);
        ed_ma_hsd = dialog.findViewById(R.id.ed_ma_hsd);
        spinner = dialog.findViewById(R.id.spin_masp_hsd);
        ed_ngaysx_hsd = dialog.findViewById(R.id.ed_ngaysx_hsd);
        ed_soluong_hsd = dialog.findViewById(R.id.ed_soluong_hsd);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_hsd);
        btnSave = dialog.findViewById(R.id.btnSave_them_hsd);
        btn_ngaysx = dialog.findViewById(R.id.btn_hsd);
        listsanpham = new ArrayList<SanPham>();
        sanphamDAO = new SanPhamDAO(context);
        listsanpham = (ArrayList<SanPham>) SanPhamDAO.getAll();

        spinnerAdapter = new SanPhamSpinnerAdapter(context, listsanpham);
        spinner.setAdapter(spinnerAdapter);


        btn_ngaysx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog d = new DatePickerDialog(getActivity(),0,mNgaySX,mYear,mMonth,mDay);
                d.show();
            }
        });
        // lay maSanpham
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maSanPham = listsanpham.get(position).getMa_sp();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        // kiem tra tupe insert hay update
        if (type != 0) {
            ed_ma_hsd.setText(String.valueOf(item.getMa_hsd()));
            ed_ngaysx_hsd.setText(String.valueOf(item.getNgaysx_hsd()));
            ed_soluong_hsd.setText(String.valueOf(item.getSoluong_hsd()));
            for (int i = 0; i < listsanpham.size(); i++)
                if (item.getMasp_hsd() == (listsanpham.get(i).getMa_sp())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spinner.setSelection(position);
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
                item = new HanSuDung();
                item.setMa_hsd(parseInt(ed_ma_hsd.getText().toString(), 0));
                item.setMasp_hsd(maSanPham);
                item.setNgaysx_hsd(ed_ngaysx_hsd.getText().toString());
                item.setSoluong_hsd(ed_soluong_hsd.getText().toString());
                if (validate() > 0) {
                    if (type == 0) {
                        if (HanSuDungDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_hsd(Integer.parseInt(ed_ma_hsd.getText().toString()));
                        if (HanSuDungDAO.update(item) > 0) {
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
        if (ed_ngaysx_hsd.getText().length() == 0 || ed_soluong_hsd.getText().length() == 0 ) {
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
        List<HanSuDung> listSearch = new ArrayList<>();
        for (HanSuDung hansudung : list) {
            if (String.valueOf(hansudung.getMa_hsd()).contains(query.toLowerCase())) {
                listSearch.add(hansudung);
            }
        }
        adapter = new AdapterHanSuDung(getActivity(), this, listSearch);
        lvHanSuDung.setAdapter(adapter);

    }

    // Sắp xếp sách theo tên tăng dần
    private void sortBooksByNameAscending() {
        Collections.sort(list, new Comparator<HanSuDung>() {
            @Override
            public int compare(HanSuDung sach1, HanSuDung sach2) {
                // Sắp xếp theo số nguyên ma_hsd
                return Integer.compare(sach1.getMa_hsd(), sach2.getMa_hsd());
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<HanSuDung>() {
            @Override
            public int compare(HanSuDung sach1, HanSuDung sach2) {
                // Sắp xếp theo số nguyên ma_hsd giảm dần
                return Integer.compare(sach2.getMa_hsd(), sach1.getMa_hsd());
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
    DatePickerDialog.OnDateSetListener mNgaySX = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            mYear = year;
            mMonth = month;
            mDay = dayOfMonth;
            GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
            ed_ngaysx_hsd.setText(sdf.format(c.getTime()));
        }
    };
}