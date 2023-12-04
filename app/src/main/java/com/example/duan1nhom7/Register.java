package com.example.duan1nhom7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duan1nhom7.DAO.NhanVienDAO;

public class Register extends AppCompatActivity {
    private NhanVienDAO nhanVienDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        EditText edUserDK=findViewById(R.id.edUserNameDK);
        EditText edPassDK=findViewById(R.id.edPasswordDk);
        EditText edRePassDK=findViewById(R.id.edRePasswordDK);
        EditText edFullnameDK=findViewById(R.id.edUserFullNameDK);
        EditText edEmail=findViewById(R.id.edEmail);
        EditText edSdt=findViewById(R.id.edSdt);

        Button btnRegisterDk = findViewById(R.id.btnRegisterDK);
        Button btnHuyDk = findViewById(R.id.btnHuyDK);

        nhanVienDAO=new NhanVienDAO(this);

        btnRegisterDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt=edSdt.getText().toString();
                String user=edUserDK.getText().toString();
                String email=edEmail.getText().toString();
                String pass = edPassDK.getText().toString();
                String repass = edRePassDK.getText().toString();
                String fullname = edFullnameDK.getText().toString();

                if (!pass.equals(repass)){
                    Toast.makeText(Register.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check= nhanVienDAO.Register(user,pass,fullname,sdt,email);
                    if (check){
                        Toast.makeText(Register.this, "Đăng kí thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(Register.this, "Đăng kí thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btnHuyDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}