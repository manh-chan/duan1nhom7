package com.example.duan1nhom7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1nhom7.Adapter.AdapterSanPham;
import com.example.duan1nhom7.Adapter.AdminSpinnerAdapter;
import com.example.duan1nhom7.Adapter.NganhHangSpinnerAdapter;
import com.example.duan1nhom7.Adapter.NhaCCSpinnerAdapter;
import com.example.duan1nhom7.DAO.AdminDAO;
import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.DAO.NhaCungCapDAO;
import com.example.duan1nhom7.DAO.SanPhamDAO;
import com.example.duan1nhom7.Model.Admin;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.Model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.Arrays;
import java.util.List;

public class FragmentSanPham extends Fragment {
    ListView lvSanPham;
    SanPhamDAO sanphamDAO;
    AdapterSanPham adapter;
    SanPham item;
    List<SanPham> list;
    FloatingActionButton fab;
    Dialog dialog;
    ImageView btn_xoa,img_sp;

    EditText ed_ma_sp,ed_gianhap_sp,ed_giaban_sp,ed_soluong_sp,ed_trangthai_sanpham;
    Button btnSave, btnCancel;
    Spinner spin_ten_sp ,spin_manhacc_sp , spin_manh_sp;

    ArrayList<NhaCungCap> listNhaCungCap;
    ArrayList<NganhHang> listNganhhang;
    NhaCungCapDAO nhacungcapDAO;
    NganhHangDAO nganhHangDAO;
    NhaCungCap nhaCungCap;
    NganhHang nganhHang;
    NhaCCSpinnerAdapter nhaCCSpinnerAdapter;
    NganhHangSpinnerAdapter nganhHangSpinnerAdapter;
    int  position,maNhaCC,maNH;

    private SearchView searchView;


    public FragmentSanPham() {
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
        View v = inflater.inflate(R.layout.activity_fragment_san_pham, container, false);
        lvSanPham = v.findViewById(R.id.lvSanPham);

        sanphamDAO = new SanPhamDAO(getActivity());

        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = new ArrayList<>(sanphamDAO.getAll()); // Ensure list is initialized
        adapter = new AdapterSanPham(getActivity(), this, list);
        lvSanPham.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SanPhamDAO.delete(Id);
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
    private void handleSearch(String query) {
        List<SanPham> listSearch = new ArrayList<>();
        for (SanPham sanpham : list) {
            if (sanpham.getTen_sp().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(sanpham);
            }
        }
        adapter = new AdapterSanPham(getActivity(), this, listSearch);
        lvSanPham.setAdapter(adapter);

    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_sp);
        ed_ma_sp = dialog.findViewById(R.id.ed_ma_sp);
        spin_manhacc_sp = dialog.findViewById(R.id.spin_manhacc_sp);
        spin_manh_sp = dialog.findViewById(R.id.spin_manh_sp);
       spin_ten_sp = dialog.findViewById(R.id.spin_ten_sp);
        ed_gianhap_sp = dialog.findViewById(R.id.ed_gianhap_sp);
        ed_giaban_sp = dialog.findViewById(R.id.ed_giaban_sp);
        ed_soluong_sp = dialog.findViewById(R.id.ed_soluong_sp);
        ed_trangthai_sanpham = dialog.findViewById(R.id.ed_trangthai_sanpham);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_sp);
        btnSave = dialog.findViewById(R.id.btnSave_them_sp);

        listNhaCungCap = new ArrayList<NhaCungCap>();
        nhacungcapDAO= new NhaCungCapDAO(context);
        listNhaCungCap = (ArrayList<NhaCungCap>) nhacungcapDAO.getAll();
        nhaCCSpinnerAdapter = new NhaCCSpinnerAdapter(context, listNhaCungCap);
        spin_manhacc_sp.setAdapter(nhaCCSpinnerAdapter);

        listNganhhang = new ArrayList<NganhHang>();
        nganhHangDAO= new NganhHangDAO(context);
        listNganhhang = (ArrayList<NganhHang>) nganhHangDAO.getAll();
        nganhHangSpinnerAdapter = new NganhHangSpinnerAdapter(context, listNganhhang);
        spin_manh_sp.setAdapter(nganhHangSpinnerAdapter);
///spinner

        List<String> listTenSP = Arrays.asList("CoCa", "NuocDua", "PepSi");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, listTenSP);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spin_ten_sp.setAdapter(spinnerAdapter);

        spin_ten_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Lấy giá trị được chọn từ Spinner
                String tenSP = listTenSP.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Xử lý khi không có mục nào được chọn
            }
        });

        spin_manhacc_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maNhaCC = listNhaCungCap.get(position).getMa_nhacc();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spin_manh_sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maNH = listNganhhang.get(position).getMa_nh();
//                Toast.makeText(context, "Chọn "+listLoaiSach.get(position).getTenLoai(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        // kiem tra tupe insert hay update

        if (type != 0) {
            ed_ma_sp.setText(String.valueOf(item.getMa_sp()));
            int position = listTenSP.indexOf(item.getTen_sp());

            // Nếu tên sản phẩm có trong danh sách, chọn nó trên Spinner
            if (position != -1) {
                spin_ten_sp.setSelection(position);
            }
            ed_gianhap_sp.setText(item.getGianhap_sp());
            ed_giaban_sp.setText(item.getGiaban_sp());
            ed_soluong_sp.setText(item.getSoluong_sp());
            ed_trangthai_sanpham.setText(item.getTrangthai_sp());

            for (int i = 0; i < listNhaCungCap.size(); i++)
                if (item.getMa_nhacc() == (listNhaCungCap.get(i).getMa_nhacc())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spin_manhacc_sp.setSelection(position);

            for (int i = 0; i < listNganhhang.size(); i++)
                if (item.getMa_nh() == (listNganhhang.get(i).getMa_nh())) {
                    position = i;
                }
            Log.i("demo", "posSach " + position);
            spin_manh_sp.setSelection(position);
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
                item = new SanPham();
                item.setMa_sp(parseInt(ed_ma_sp.getText().toString(), 0));
                item.setMa_nhacc(maNhaCC);
                item.setMa_nh(maNH);
                String tenSanPham = spin_ten_sp.getSelectedItem().toString();
                item.setTen_sp(tenSanPham);
                item.setGianhap_sp(ed_gianhap_sp.getText().toString());
                item.setGiaban_sp(ed_giaban_sp.getText().toString());
                item.setSoluong_sp(ed_soluong_sp.getText().toString());
                item.setTrangthai_sp(ed_trangthai_sanpham.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (SanPhamDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_sp(Integer.parseInt(ed_ma_sp.getText().toString()));
                        if (SanPhamDAO.update(item) > 0) {
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

    private int validate() {
        int check = 1;
        if ( ed_gianhap_sp.getText().length() == 0 || ed_giaban_sp.getText().length() == 0) {
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

    private void sortBooksByNameAscending() {
        Collections.sort(list, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                return sp1.getTen_sp().compareTo(sp2.getTen_sp());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<SanPham>() {
            @Override
            public int compare(SanPham sp1, SanPham sp2) {
                return sp2.getTen_sp().compareTo(sp1.getTen_sp());
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



}