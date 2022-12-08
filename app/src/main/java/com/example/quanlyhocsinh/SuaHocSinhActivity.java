package com.example.quanlyhocsinh;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SuaHocSinhActivity extends AppCompatActivity {

    Button btnEdit, btnCancel;
    EditText edtTen, edtLop;
    ImageButton ibtnCamera2, ibtnFolder2;
    ImageView imgHinh2;

    final int REQUEST_CODE_CAMERA2 = 789;
    final int REQUEST_CODE_FOLDER2 = 987;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_hoc_sinh);

        AnhXa();

        Intent intent = getIntent();
        HocSinh hs = (HocSinh) intent.getSerializableExtra("dulieuHS");
        edtTen.setText(hs.getTen()+"");
        edtLop.setText(hs.getLop()+"");
        byte[] hinhAnh = hs.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        imgHinh2.setImageBitmap(bitmap);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Chuyển data imageview -> byte[]
                BitmapDrawable bitmapDrawable = (BitmapDrawable) imgHinh2.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100, byteArray);
                byte[] hinhAnh = byteArray.toByteArray();

                if(edtTen.getText().toString().trim().equals("") || edtLop.getText().toString().trim().equals("")){
                    Toast.makeText(SuaHocSinhActivity.this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else{
                    ListStudentActivity.DB.UPDATE_HOCSINH(
                            edtTen.getText().toString().trim(),
                            edtLop.getText().toString().trim(),
                            hinhAnh,
                            hs.getId());

                    Toast.makeText(SuaHocSinhActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SuaHocSinhActivity.this,ListStudentActivity.class));
                }

            }
        });

        ibtnCamera2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(intent, REQUEST_CODE_CAMERA);
                ActivityCompat.requestPermissions(
                        SuaHocSinhActivity.this,
                        new String[]{Manifest.permission.CAMERA},
                        REQUEST_CODE_CAMERA2
                );
            }
        });

        ibtnFolder2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");
//                startActivityForResult(intent, REQUEST_CODE_FOLDER);
                ActivityCompat.requestPermissions(
                        SuaHocSinhActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_FOLDER2
                );
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SuaHocSinhActivity.this, "Bạn đã hủy thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SuaHocSinhActivity.this,ListStudentActivity.class));
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE_CAMERA2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(intent, REQUEST_CODE_CAMERA2);
                }
                else {
                    Toast.makeText(this, "Bạn không cho phép mở camera!", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_CODE_FOLDER2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent, REQUEST_CODE_FOLDER2);
                }
                else {
                    Toast.makeText(this, "Bạn không cho phép truy cập thư viện ảnh!", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_CODE_CAMERA2 && resultCode == RESULT_OK && data != null){
            Bitmap bitmap = (Bitmap) data.getExtras().get("data");
            imgHinh2.setImageBitmap(bitmap);
        }

        if (requestCode == REQUEST_CODE_FOLDER2 && resultCode == RESULT_OK && data != null){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgHinh2.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void AnhXa(){
        btnEdit = (Button) findViewById(R.id.buttonSuaAdd);
        btnCancel = (Button) findViewById(R.id.buttonSuaHuy);
        edtTen = (EditText) findViewById(R.id.editTextSuaNameHS);
        edtLop = (EditText) findViewById(R.id.editTextSuaLopHS);
        imgHinh2 = (ImageView) findViewById(R.id.imageSuaViewHinh);
        ibtnCamera2 = (ImageButton) findViewById(R.id.imageSuaButtonCamera);
        ibtnFolder2 = (ImageButton) findViewById(R.id.imageSuaButtonFolder);
    }

}