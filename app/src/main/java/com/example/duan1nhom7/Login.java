package com.example.duan1nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7.DAO.AdminDAO;
import com.example.duan1nhom7.Model.Admin;


public class Login extends AppCompatActivity {
    EditText ed_sdt_dn, ed_pass_dn;
    Button btnLogin;
    CheckBox chkRememberPass;
    AdminDAO adminDAO;
    Admin admin;
    String strUser, strPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        setTitle("Đăng nhập");
        ed_sdt_dn = findViewById(R.id.ed_sdt_dn);
        ed_pass_dn = findViewById(R.id.ed_pass_dn);
        btnLogin = findViewById(R.id.btn_login);
        chkRememberPass = findViewById(R.id.ck_nhomk);
        adminDAO = new AdminDAO(this);
        //
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        String user = pref.getString("USERNAME", "");
        String pass = pref.getString("PASSWORD", "");
        Boolean rem = pref.getBoolean("REMEMBER", false);

        ed_sdt_dn.setText(user);
        ed_pass_dn.setText(pass);
        chkRememberPass.setChecked(rem);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
    }

    public void rememberUser(String u, String p, boolean status) {
        SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        if (!status) {
            // xoa trang thai luu truoc do
            edit.clear();
        } else {
            edit.putString("USERNAME", u);
            edit.putString("PASSWORD", p);
            edit.putBoolean("REMEMBER", status);
        }
        // luu lai toan bo du lieu
        edit.commit();
    }

    public void checkLogin() {
        strUser = ed_sdt_dn.getText().toString();
        strPass = ed_pass_dn.getText().toString();
        if (strUser.trim().isEmpty() || strPass.trim().isEmpty()) {
            Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        } else {
            if (adminDAO.checkLogin(strUser, strPass) > 0) {
                Toast.makeText(getApplicationContext(), "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkRememberPass.isChecked());
                Intent intent = new Intent(getApplicationContext(), Trangchu.class);
                intent.putExtra("user", strUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
}