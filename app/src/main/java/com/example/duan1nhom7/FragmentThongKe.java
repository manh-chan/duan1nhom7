package com.example.duan1nhom7;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


import com.example.duan1nhom7.Adapter.AdapterThongKe;
import com.example.duan1nhom7.DAO.HoaDonDAO;
import com.example.duan1nhom7.Model.HoaDon;
import com.example.duan1nhom7.Model.ThongKe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FragmentThongKe extends Fragment {
    private ListView listView;
    ListView lvThongKe;
    private List<ThongKe> originalList;
    List<ThongKe> list;
    AdapterThongKe adapter;
    HoaDonDAO hoaDonDAO;
    private AdapterThongKe thongKeAdapter;
    private SearchView searchView;
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

        if (list != null) {
            originalList = new ArrayList<>(list);  // Lưu trữ danh sách gốc
        } else {
            // Xử lý khi list là null
        } // Lưu trữ danh sách gốc
        super.onCreateOptionsMenu(menu, inflater);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_thong_ke, container, false);

        listView = view.findViewById(R.id.lvThongKe);
        thongKeAdapter = new AdapterThongKe(getContext(), this, new ArrayList<>());
        listView.setAdapter(thongKeAdapter);

        // Gọi hàm thực hiện thống kê và cập nhật dữ liệu trong Adapter
        themDuLieuVaoDanhSachHoaDon();

        return view;
    }

    private void themDuLieuVaoDanhSachHoaDon() {
        hoaDonDAO = new HoaDonDAO(getContext());
        List<HoaDon> danhSachHoaDon = hoaDonDAO.getAll();

        Map<String, ThongKe> thongKeMap = new HashMap<>();

        for (HoaDon hoaDon : danhSachHoaDon) {
            String ngay = hoaDon.getThoigian_hd().substring(0, 10);

            if (thongKeMap.containsKey(ngay)) {
                ThongKe thongKeNgay = thongKeMap.get(ngay);
                thongKeNgay.setTongTien(thongKeNgay.getTongTien() + hoaDon.getTongtien_hd());
                thongKeNgay.setKhuyenMai(thongKeNgay.getKhuyenMai() + hoaDon.getKhuyenmai_hd());
                thongKeNgay.setTongSoHoaDon(thongKeNgay.getTongSoHoaDon() + 1);
            } else {
                ThongKe thongKeNgay = new ThongKe(ngay, hoaDon.getTongtien_hd(), hoaDon.getKhuyenmai_hd(), 1);
                thongKeMap.put(ngay, thongKeNgay);
            }
        }

        List<ThongKe> thongKeNgayList = new ArrayList<>(thongKeMap.values());
        thongKeAdapter.clear();
        thongKeAdapter.addAll(thongKeNgayList);
        thongKeAdapter.notifyDataSetChanged();
    }




    public static int parseInt(String string, int defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }


    private void handleSearch(String query) {
        List<ThongKe> dataList;

        // Kiểm tra xem originalList có được khởi tạo và có giá trị không
        if (originalList == null) {
            // Xử lý khi originalList là null
            return;
        }

        if (query.isEmpty()) {
            dataList = new ArrayList<>(originalList);
        } else {
            dataList = new ArrayList<>();
            for (ThongKe thongKe : originalList) {
                if (thongKe != null && thongKe.getNgay() != null &&
                        thongKe.getNgay().toLowerCase().contains(query.toLowerCase())) {
                    dataList.add(thongKe);
                }
            }
        }

        updateAdapter(dataList);
    }
    private void updateAdapter(List<ThongKe> dataList) {
        adapter = new AdapterThongKe(getActivity(), this, dataList);
        lvThongKe.setAdapter(adapter);
    }

    // Sắp xếp sách theo tên tăng dần
    private void sortThongKeByDateAscending(List<ThongKe> thongKeList) {
        Collections.sort(thongKeList, new Comparator<ThongKe>() {
            @Override
            public int compare(ThongKe thongKe1, ThongKe thongKe2) {
                return thongKe1.getNgay().compareTo(thongKe2.getNgay());
            }
        });
    }

    private void sortThongKeByDateDescending(List<ThongKe> thongKeList) {
        Collections.sort(thongKeList, new Comparator<ThongKe>() {
            @Override
            public int compare(ThongKe thongKe1, ThongKe thongKe2) {
                return thongKe2.getNgay().compareTo(thongKe1.getNgay());
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.asc) {
            sortThongKeByDateAscending(list);
            updateAdapter(list);
            return true;
        } else if (id == R.id.desc) {
            sortThongKeByDateDescending(list);
            updateAdapter(list);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}