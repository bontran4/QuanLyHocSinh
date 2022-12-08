package com.example.quanlyhocsinh;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListStudentActivity extends AppCompatActivity {

    public static DBHelper DB;
    ListView lvHocSinh;
    ArrayList<HocSinh> arrayHocSinh;
    HocSinhAdapter adapter;
    Button btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_student);

        // ánh xạ
        lvHocSinh = (ListView) findViewById(R.id.listViewHocSinh);
        btnThem = (Button) findViewById(R.id.buttonThem);
        arrayHocSinh = new ArrayList<>();

        adapter = new HocSinhAdapter(this, R.layout.dong_hoc_sinh, arrayHocSinh);

        lvHocSinh.setAdapter(adapter);

        // tạo database
        DB = new DBHelper(this);

        // tạo bảng
        DB.QueryData("CREATE TABLE IF NOT EXISTS HocSinh(Id INTEGER PRIMARY KEY AUTOINCREMENT, TenHS VARCHAR(200), Lop VARCHAR(200), HinhAnh BLOB)");


        // select dữ liệu
        GetDataHocSinh();

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListStudentActivity.this, ThemHocSinhActivity.class));
            }
        });

        lvHocSinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(ListStudentActivity.this,InfoHsActivity.class);
                intent.putExtra("dulieuTTHS",arrayHocSinh.get(i));
                startActivity(intent);
            }
        });
    }

    public void tt(HocSinh hs){
        Intent intent = new Intent(ListStudentActivity.this, SuaHocSinhActivity.class);
        intent.putExtra("dulieuHS",hs);
        startActivity(intent);
    }

    public void DialogXoaHS(String tenHS, int id){
        AlertDialog.Builder dialogXoa = new AlertDialog.Builder(this);
        dialogXoa.setMessage("Bạn có muốn xóa học sinh này " + tenHS + " không?");
        dialogXoa.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, final int i) {
                DB.QueryData("DELETE FROM HocSinh WHERE Id = '"+ id +"' ");
                Toast.makeText(ListStudentActivity.this, "Đã xóa" + tenHS, Toast.LENGTH_SHORT).show();
                GetDataHocSinh();
            }
        });
        dialogXoa.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogXoa.show();
    }

    public void SuaHS(int id, String ten, String lop, byte[] hinh){

    }

    private void GetDataHocSinh(){
        // select dữ liệu
        Cursor dataHocSinh = DB.GetData("SELECT * FROM HocSinh");
        arrayHocSinh.clear();
        while (dataHocSinh.moveToNext()){

            arrayHocSinh.add(new HocSinh(
                    dataHocSinh.getInt(0),
                    dataHocSinh.getString(1),
                    dataHocSinh.getString(2),
                    dataHocSinh.getBlob(3)));
        }
        adapter.notifyDataSetChanged();
    }
}