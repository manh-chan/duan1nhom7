package com.example.duan1nhom7;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duan1nhom7.Adapter.ImageSliderAdapter;
import com.google.android.material.navigation.NavigationView;

public class Trangchu extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    TextView tvUser;
    NavigationView nv;
    ImageView btn_ql_sp;
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
        FragmentSanPham fragmentSanPham = new FragmentSanPham();
        btn_ql_sp =findViewById(R.id.btn_ql_sp);
        btn_ql_sp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(fragmentSanPham);
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

}