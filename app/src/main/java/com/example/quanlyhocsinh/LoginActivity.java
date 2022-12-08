package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    TextView txtDangKy;
    EditText txtTenDangNhap, txtMatKhau;
    Button btnDangNhap;
    DBHelper DB;
    CheckBox cbNhoTaiKhoan;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Ánh xạ
        txtDangKy = (TextView) findViewById(R.id.textDangKy);
        txtTenDangNhap = (EditText) findViewById(R.id.username);
        txtMatKhau = (EditText) findViewById(R.id.password);
        btnDangNhap = (Button) findViewById(R.id.loginbtn);
        DB = new DBHelper(this);
        cbNhoTaiKhoan = (CheckBox) findViewById(R.id.nhoTaiKhoan);

        sharedPreferences = getSharedPreferences("dataLogin",MODE_PRIVATE);

        txtTenDangNhap.setText(sharedPreferences.getString("taikhoan",""));
        txtMatKhau.setText(sharedPreferences.getString("matkhau",""));
        cbNhoTaiKhoan.setChecked(sharedPreferences.getBoolean("checked",false));

        // Chuyển sang trang đăng ký
        txtDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(intent);
            }
        });

        // Đăng nhập vào trang Index
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userDN = txtTenDangNhap.getText().toString().trim();
                String passDN = txtMatKhau.getText().toString().trim();




                if(userDN.equals("")||passDN.equals(""))
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đủ thông tin!", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(userDN, passDN);
                    if(checkuserpass==true){

                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                        if(cbNhoTaiKhoan.isChecked()){
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("taikhoan",userDN);
                            editor.putString("matkhau",passDN);
                            editor.putBoolean("checked",true);
                            editor.commit();
                        }
                        else {
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.remove("taikhoan");
                            editor.remove("matkhau");
                            editor.remove("checked");
                            editor.commit();
                        }


                        Intent intent  = new Intent(LoginActivity.this, IndexActivity.class);
                        intent.putExtra("thongtinCN",userDN);
                        startActivity(intent);

                    }else{
                        Toast.makeText(LoginActivity.this, "Thông tin không hợp lệ!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


    }
}