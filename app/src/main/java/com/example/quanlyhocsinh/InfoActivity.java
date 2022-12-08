package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    TextView txtTDNCaplock, txtTenDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        txtTDNCaplock = (TextView) findViewById(R.id.textViewTDNcaplock);
        txtTenDangNhap = (TextView) findViewById(R.id.textViewTDangNhap);

        Intent intent = getIntent();
        String tenDangNhapCaplock = intent.getStringExtra("thongtinCN2");
        txtTDNCaplock.setText(tenDangNhapCaplock);
        txtTenDangNhap.setText(tenDangNhapCaplock);
    }
}