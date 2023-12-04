package com.example.duan1nhom7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duan1nhom7.Adapter.ImageSliderAdapter;
import com.google.android.material.navigation.NavigationView;

public class Trangchu extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    NavigationView nv;
    ImageView btn_ql_sp ,btn_ql_hsd,btn_ql_hd,btn_thongke;
    private ViewPager viewPager;
    private ImageSliderAdapter adapter;
    private int currentPage = 0;
    private final int DELAY_MS = 3000; // Độ trễ giữa các trang (3 giây ở đây)
    private final Handler handler = new Handler();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trangchu);


        drawer = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar1);
        nv = findViewById(R.id.nvView);
        // set toolbar thay actionbar
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.baseline_menu_24);
        ab.setDisplayHomeAsUpEnabled(true);
        // set mau icon ve ban goc
        nv.setItemIconTintList(null);


        //quan lý san pham
        FragmentSanPham fragmentSanPham = new FragmentSanPham();
        btn_ql_sp =findViewById(R.id.btn_ql_sp);
        btn_ql_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(fragmentSanPham);
            }
        });

        //quan lý hạn sử dụng
        FragmentHanSuDung fragmenthansudung = new FragmentHanSuDung();
        btn_ql_hsd =findViewById(R.id.btn_quanly_hsd);
        btn_ql_hsd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(fragmenthansudung);
            }
        });

        //quan lý hoa don
        FragmentHoaDon fragmenthoadon = new FragmentHoaDon();
        btn_ql_hd =findViewById(R.id.btn_quanly_hd);
        btn_ql_hd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(fragmenthoadon);
            }
        });

        //Thong ke
        FragmentThongKe fragmentthongke = new FragmentThongKe();
        btn_thongke =findViewById(R.id.btn_thongke);
        btn_thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(fragmentthongke);
            }
        });

        //slide show
        viewPager = findViewById(R.id.viewPager);
        adapter = new ImageSliderAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        // Tự động chuyển trang sau khoảng thời gian DELAY_MS
        handler.postDelayed(runnable, DELAY_MS);

        // Xử lý sự kiện khi trang thay đổi
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });


        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.nav_trangchu) {
                    setTitle("Trang chủ");
                    Intent intent = new Intent(Trangchu.this, Trangchu.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);


                } else if (id == R.id.nav_quanlycv) {
                    setTitle("Quản lý công việc");
                    FragmentCongViec frcv = new FragmentCongViec();
                    replaceFrg(frcv);

                }else if (id == R.id.nav_quanlynv) {
                    setTitle("Quản lý Nhân Viên");
                    FragmentQuanLyNV frnhanv = new FragmentQuanLyNV();
                    replaceFrg(frnhanv);

                } else if (id == R.id.nav_quanlykh) {
                    setTitle("Quản lý Khách Hàng");
                    FragmentQuanLyKH frkhachhang = new FragmentQuanLyKH();
                    replaceFrg(frkhachhang);

                } else if (id == R.id.nav_quanlyncc) {
                    setTitle("Quản lý Nhà Cung Cấp");
                    FragmentNhaCC frnhacc = new FragmentNhaCC();
                    replaceFrg(frnhacc);

                } else if (id == R.id.nav_quanlynh) {
                    setTitle("Quản lý ngành hàng");
                    FragmentNganhHang frnganhhang = new FragmentNganhHang();
                    replaceFrg(frnganhhang);

                } else if (id == R.id.sub_Logout) {
                    android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(Trangchu.this);
                    builder.setTitle("Đăng xuất");
                    builder.setMessage("Bạn có muốn đăng xuất không?");
                    builder.setCancelable(true);

                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Trangchu.this, Login.class);
                            Toast.makeText(Trangchu.this, "Đã đăng xuất", Toast.LENGTH_SHORT).show();
                            startActivity(intent);
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Toast.makeText(Trangchu.this, "Không đăng xuất", Toast.LENGTH_SHORT).show();
                        }
                    });
                    AlertDialog alert = builder.create();
                    alert.show();

                }
                drawer.closeDrawers();
                return true;
            }
        });
    }

    // Runnable để tự động chuyển trang
    private final Runnable runnable = new Runnable() {
        public void run() {
            if (currentPage == adapter.getCount() - 1) {
                currentPage = 0;
            } else {
                currentPage++;
            }
            viewPager.setCurrentItem(currentPage, true);
            handler.postDelayed(this, DELAY_MS);
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();

        // Kiểm tra xem có fragment nào đang hiển thị hay không
        Fragment currentFragment = fm.findFragmentById(R.id.flContent);
        if (currentFragment != null) {
            // Nếu có, loại bỏ fragment hiện tại
            fm.beginTransaction().remove(currentFragment).commit();
        }

        // Thêm fragment mới
        fm.beginTransaction()
                .replace(R.id.flContent, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit();
    }
    public void replaceFrg(Fragment frg) {
        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.flContent, frg).commit();
    }

}