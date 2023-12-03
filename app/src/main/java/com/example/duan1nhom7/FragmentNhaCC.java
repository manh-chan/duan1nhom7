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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duan1nhom7.Adapter.AdapterNhaCC;
import com.example.duan1nhom7.Adapter.AdapterSanPham;
import com.example.duan1nhom7.DAO.NganhHangDAO;
import com.example.duan1nhom7.DAO.NhaCungCapDAO;
import com.example.duan1nhom7.DAO.SanPhamDAO;
import com.example.duan1nhom7.Model.NganhHang;
import com.example.duan1nhom7.Model.NhaCungCap;
import com.example.duan1nhom7.Model.SanPham;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FragmentNhaCC extends Fragment {
    ListView lvNhaCC;
    NhaCungCapDAO nhacungcapDAO;
    AdapterNhaCC adapter;
    NhaCungCap item;
    List<NhaCungCap> list;
    FloatingActionButton fab;
    Dialog dialog;
    ImageView btn_xoa,img_sp;

    EditText ed_ma_nhacc, ed_ten_nhacc,ed_sdt_nhacc,ed_diachi_nhacc,ed_trangthai_nhacc;
    Button btnSave, btnCancel;


    private SearchView searchView;


    public FragmentNhaCC() {
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
        View v = inflater.inflate(R.layout.activity_fragment_nha_cc, container, false);
        lvNhaCC = v.findViewById(R.id.lvNhaCC);

        nhacungcapDAO = new NhaCungCapDAO(getActivity());

        capNhatLv();
        fab = v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(), 0);
            }
        });
        lvNhaCC.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
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
        list = new ArrayList<>(nhacungcapDAO.getAll()); // Ensure list is initialized
        adapter = new AdapterNhaCC(getActivity(), this, list);
        lvNhaCC.setAdapter(adapter);
    }
    public void xoa(final String Id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Delete");
        builder.setMessage("Bạn có muốn xóa không?");
        builder.setCancelable(true);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NhaCungCapDAO.delete(Id);
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
        List<NhaCungCap> listSearch = new ArrayList<>();
        for (NhaCungCap nhacc : list) {
            if (nhacc.getTen_nhacc().toLowerCase().contains(query.toLowerCase())) {
                listSearch.add(nhacc);
            }
        }
        adapter = new AdapterNhaCC(getActivity(), this, listSearch);
        lvNhaCC.setAdapter(adapter);

    }
    protected void openDialog(final Context context, final int type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_them_nhacc);
        ed_ma_nhacc = dialog.findViewById(R.id.ed_ma_nhacc);
        ed_ten_nhacc = dialog.findViewById(R.id.ed_ten_nhacc);
        ed_sdt_nhacc = dialog.findViewById(R.id.ed_sdt_nhacc);
        ed_diachi_nhacc = dialog.findViewById(R.id.ed_diachi_nhacc);
        ed_trangthai_nhacc = dialog.findViewById(R.id.ed_trangthai_nhacc);
        btnCancel = dialog.findViewById(R.id.btnCancel_them_nhacc);
        btnSave = dialog.findViewById(R.id.btnSave_them_nhacc);






        // kiem tra tupe insert hay update

        if (type != 0) {
            ed_ma_nhacc.setText(String.valueOf(item.getMa_nhacc()));
            ed_ten_nhacc.setText(item.getTen_nhacc());
            ed_sdt_nhacc.setText(item.getSdt_nhacc());
            ed_diachi_nhacc.setText(item.getDiachi_nhacc());
            ed_trangthai_nhacc.setText(item.getTrangthai_nhacc());

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
                item = new NhaCungCap();
                item.setMa_nhacc(parseInt(ed_ma_nhacc.getText().toString(), 0));
                item.setTen_nhacc(ed_ten_nhacc.getText().toString());
                item.setSdt_nhacc(ed_sdt_nhacc.getText().toString());
                item.setDiachi_nhacc(ed_diachi_nhacc.getText().toString());
                item.setTrangthai_nhacc(ed_trangthai_nhacc.getText().toString());

                if (validate() > 0) {
                    if (type == 0) {
                        if (NhaCungCapDAO.insert(item) > 0) {
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        item.setMa_nhacc(Integer.parseInt(ed_ma_nhacc.getText().toString()));
                        if (NhaCungCapDAO.update(item) > 0) {
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
        if ( ed_ten_nhacc.getText().length() == 0 || ed_diachi_nhacc.getText().length() == 0) {
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
        Collections.sort(list, new Comparator<NhaCungCap>() {
            @Override
            public int compare(NhaCungCap sp1,NhaCungCap sp2) {
                return sp1.getTen_nhacc().compareTo(sp2.getTen_nhacc());
//                return sach1.getGiaThue() - sach2.getGiaThue();
            }
        });
        adapter.notifyDataSetChanged();
    }

    // Sắp xếp sách theo tên giảm dần
    private void sortBooksByNameDescending() {
        Collections.sort(list, new Comparator<NhaCungCap>() {
            @Override
            public int compare(NhaCungCap sp1, NhaCungCap sp2) {
                return sp2.getTen_nhacc().compareTo(sp1.getTen_nhacc());
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