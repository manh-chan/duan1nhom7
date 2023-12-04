package com.example.duan1nhom7;

import static com.example.duan1nhom7.DAO.NganhHangDAO.update;

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

import com.example.duan1nhom7.Adapter.AdapterNganhHang;
import com.example.duan1nhom7.Adapter.AdapterNhanVien;
import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.DAO.NhanVienDAO;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhanVien;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentQuanLyNV extends Fragment {
    ListView lvNhanVien;
    NhanVienDAO nhanvienDAO;
    AdapterNhanVien adapter;
    NhanVien item;
    List<NhanVien> list;
    FloatingActionButton fab;
    Dialog dialog;
    ImageView btn_xoa,img_sp;

    EditText ed_ma_nv, ed_ten_nv,ed_email_nv,ed_sdt_nv,ed_pass_nv;
    Button btnSave, btnCancel;

    private boolean isAdmin = true;  // Mặc định là quản lý
    private SearchView searchView;

    public FragmentQuanLyNV() {
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
        View v = inflater.inflate(R.layout.activity_fragment_quan_ly_nv, container, false);
        lvNhanVien = v.findViewById(R.id.lvNhanVien);

        nhanvienDAO = new NhanVienDAO(getActivity());

        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvNhanVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = new ArrayList<>(NhanVienDAO.getAll()); // Ensure list is initialized
        adapter = new AdapterNhanVien(getActivity(), this, list);
        lvNhanVien.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NhanVienDAO.delete(Id);
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
        List<NhanVien> listSearch = new ArrayList<>();
        for (NhanVien nhanvien : list) {
            if (nhanvien.getHoten_nv().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(nhanvien);
            }
        }
        adapter = new AdapterNhanVien(getActivity(), this, listSearch);
        lvNhanVien.setAdapter(adapter);

    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_nv);
        ed_ma_nv = dialog.findViewById(R.id.ed_ma_nv);
        ed_ten_nv= dialog.findViewById(R.id.ed_ten_nv);
        ed_email_nv = dialog.findViewById(R.id.ed_email_nv);
        ed_sdt_nv = dialog.findViewById(R.id.ed_sdt_nv);
        ed_pass_nv = dialog.findViewById(R.id.ed_pass_nv);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_nv);
        btnSave = dialog.findViewById(R.id.btnSave_them_nv);






        // kiem tra tupe insert hay update

        if (type != 0) {
            ed_ma_nv.setText(String.valueOf(item.getMa_nv()));
            ed_ten_nv.setText(item.getHoten_nv());
            ed_email_nv.setText(item.getEmail_nv());
            ed_sdt_nv.setText(item.getSdt_nv());
            ed_pass_nv.setText(item.getMatkhau_nv());
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
                item = new NhanVien();
                item.setMa_nv(parseInt(ed_ma_nv.getText().toString(), 0));
                item.setHoten_nv(ed_ten_nv.getText().toString());
                item.setEmail_nv(ed_email_nv.getText().toString());
                item.setSdt_nv(ed_sdt_nv.getText().toString());
                item.setMatkhau_nv(ed_pass_nv.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (NhanVienDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_nv(Integer.parseInt(ed_ma_nv.getText().toString()));
                        if (NhanVienDAO.updatePass(item) > 0) {
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
        if ( ed_ten_nv.getText().length() == 0 || ed_sdt_nv.getText().length() == 0) {
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
        Collections.sort(list, new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien sp1,NhanVien sp2) {
                return sp1.getHoten_nv().compareTo(sp2.getHoten_nv());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien sp1, NhanVien sp2) {
                return sp2.getHoten_nv().compareTo(sp1.getHoten_nv());
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