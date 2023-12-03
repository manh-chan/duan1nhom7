package com.example.duan1nhom7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
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
import android.widget.Toast;

import com.example.duan1nhom7.Adapter.AdapterKhachHang;
import com.example.duan1nhom7.Adapter.AdapterNganhHang;
import com.example.duan1nhom7.DAO.KhachHangDAO;
import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.Model.KhachHang;
import com.example.duan1nhom7.Model.NganhHang;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentQuanLyKH extends Fragment {
    ListView lvKhachHang;
    KhachHangDAO khachhangDAO;
    AdapterKhachHang adapter;
    KhachHang item;
    List<KhachHang> list;
    FloatingActionButton fab;
    Dialog dialog;
    ImageView btn_xoa,img_sp;

    EditText ed_ma_kh, ed_ten_kh,ed_sdt_kh;
    Button btnSave, btnCancel;


    private SearchView searchView;

    public FragmentQuanLyKH() {
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
        View v = inflater.inflate(R.layout.activity_fragment_quan_ly_kh, container, false);
        lvKhachHang = v.findViewById(R.id.lvKhachHang);

        khachhangDAO = new KhachHangDAO(getActivity());

        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvKhachHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = new ArrayList<>(KhachHangDAO.getAll()); // Ensure list is initialized
        adapter = new AdapterKhachHang(getActivity(), this, list);
        lvKhachHang.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               KhachHangDAO.delete(Id);
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
        List<KhachHang> listSearch = new ArrayList<>();
        for (KhachHang khachhang : list) {
            if (khachhang.getTen_kh().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(khachhang);
            }
        }
        adapter = new AdapterKhachHang(getActivity(), this, listSearch);
        lvKhachHang.setAdapter(adapter);

    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_kh);
        ed_ma_kh = dialog.findViewById(R.id.ed_ma_kh);
        ed_ten_kh= dialog.findViewById(R.id.ed_ten_kh);
        ed_sdt_kh = dialog.findViewById(R.id.ed_sdt_kh);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_kh);
        btnSave = dialog.findViewById(R.id.btnSave_them_kh);






        // kiem tra tupe insert hay update

        if (type != 0) {
            ed_ma_kh.setText(String.valueOf(item.getMa_kh()));
            ed_ten_kh.setText(item.getTen_kh());
            ed_sdt_kh.setText(item.getSdt_kh());

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
                item = new KhachHang();
                item.setMa_kh(parseInt(ed_ma_kh.getText().toString(), 0));
                item.setTen_kh(ed_ten_kh.getText().toString());
                item.setSdt_kh(ed_sdt_kh.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (KhachHangDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_kh(Integer.parseInt(ed_ma_kh.getText().toString()));
                        if (KhachHangDAO.update(item) > 0) {
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
        if ( ed_ten_kh.getText().length() == 0 || ed_sdt_kh.getText().length() == 0) {
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
        Collections.sort(list, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang sp1,KhachHang sp2) {
                return sp1.getTen_kh().compareTo(sp2.getTen_kh());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang sp1, KhachHang sp2) {
                return sp2.getTen_kh().compareTo(sp1.getTen_kh());
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