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

import com.example.duan1nhom7.Adapter.AdapterNganhHang;
import com.example.duan1nhom7.Adapter.AdapterNhaCC;
import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.DAO.NhaCungCapDAO;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentNganhHang extends Fragment {
    ListView lvNganhHang;
    NganhHangDAO nganhhangDAO;
    AdapterNganhHang adapter;
    NganhHang item;
    List<NganhHang> list;
    FloatingActionButton fab;
    Dialog dialog;
    ImageView btn_xoa,img_sp;

    EditText ed_ma_nh, ed_ten_nh,ed_trangthai_nh;
    Button btnSave, btnCancel;


    private SearchView searchView;


    public FragmentNganhHang() {
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
        View v = inflater.inflate(R.layout.activity_fragment_nganh_hang, container, false);
        lvNganhHang = v.findViewById(R.id.lvNganhHang);

        nganhhangDAO = new NganhHangDAO(getActivity());

        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvNganhHang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = new ArrayList<>(NganhHangDAO.getAll()); // Ensure list is initialized
        adapter = new AdapterNganhHang(getActivity(), this, list);
        lvNganhHang.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NganhHangDAO.delete(Id);
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
        List<NganhHang> listSearch = new ArrayList<>();
        for (NganhHang nganhhang : list) {
            if (nganhhang.getTen_nh().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(nganhhang);
            }
        }
        adapter = new AdapterNganhHang(getActivity(), this, listSearch);
        lvNganhHang.setAdapter(adapter);

    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_nh);
        ed_ma_nh = dialog.findViewById(R.id.ed_ma_nh);
        ed_ten_nh= dialog.findViewById(R.id.ed_ten_nh);
        ed_trangthai_nh = dialog.findViewById(R.id.ed_trangthai_nh);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_nh);
        btnSave = dialog.findViewById(R.id.btnSave_them_nh);






        // kiem tra tupe insert hay update

        if (type != 0) {
            ed_ma_nh.setText(String.valueOf(item.getMa_nh()));
            ed_ten_nh.setText(item.getTen_nh());
            ed_trangthai_nh.setText(item.getTrangthai_nh());

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
                item = new NganhHang();
                item.setMa_nh(parseInt(ed_ma_nh.getText().toString(), 0));
                item.setTen_nh(ed_ten_nh.getText().toString());
                item.setTrangthai_nh(ed_trangthai_nh.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (NganhHangDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_nh(Integer.parseInt(ed_ma_nh.getText().toString()));
                        if (NganhHangDAO.update(item) > 0) {
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
        if ( ed_ten_nh.getText().length() == 0 || ed_trangthai_nh.getText().length() == 0) {
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
        Collections.sort(list, new Comparator<NganhHang>() {
            @Override
            public int compare(NganhHang sp1,NganhHang sp2) {
                return sp1.getTen_nh().compareTo(sp2.getTen_nh());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<NganhHang>() {
            @Override
            public int compare(NganhHang sp1, NganhHang sp2) {
                return sp2.getTen_nh().compareTo(sp1.getTen_nh());
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