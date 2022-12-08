package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class IndexActivity extends AppCompatActivity {

    RelativeLayout rLayout1, rLayout2, rLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        rLayout1 = (RelativeLayout) findViewById(R.id.relativeLayout1);
        rLayout2 = (RelativeLayout) findViewById(R.id.relativeLayout2);
        rLayout3 = (RelativeLayout) findViewById(R.id.relativeLayout3);

        rLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(IndexActivity.this, "Đăng xuất thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(IndexActivity.this, LoginActivity.class));
            }
        });

        rLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(IndexActivity.this, ListStudentActivity.class));
            }
        });

        rLayout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = getIntent();
                String tenDNC = intent2.getStringExtra("thongtinCN");
                Intent intent = new Intent(IndexActivity.this, InfoActivity.class);
                intent.putExtra("thongtinCN2",tenDNC);
                startActivity(intent);
            }
        });

    }
}