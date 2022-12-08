package com.example.quanlyhocsinh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class InfoHsActivity extends AppCompatActivity {

    Button btnQuayLai;
    TextView txtTenHsHT, txtLopHsHT;
    ImageView imgHinhHT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_hs);

        btnQuayLai = (Button) findViewById(R.id.buttonQuayLai);
        txtTenHsHT = (TextView) findViewById(R.id.textViewHoVaTen);
        txtLopHsHT = (TextView) findViewById(R.id.textViewLopHoc);
        imgHinhHT = (ImageView) findViewById(R.id.imageViewHinhDD);

        Intent intent = getIntent();
        HocSinh hs = (HocSinh) intent.getSerializableExtra("dulieuTTHS");

        txtTenHsHT.setText(hs.getTen());
        txtLopHsHT.setText(hs.getLop());
        byte[] hinhAnh = hs.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        imgHinhHT.setImageBitmap(bitmap);

        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InfoHsActivity.this,ListStudentActivity.class));
            }
        });



    }
}