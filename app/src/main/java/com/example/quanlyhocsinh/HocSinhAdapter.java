package com.example.quanlyhocsinh;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class HocSinhAdapter extends BaseAdapter {

    private ListStudentActivity context;
    private int layout;
    private List<HocSinh> hocSinhList;

    public HocSinhAdapter(ListStudentActivity context, int layout, List<HocSinh> hocSinhList) {
        this.context = context;
        this.layout = layout;
        this.hocSinhList = hocSinhList;
    }

    @Override
    public int getCount() {
        return hocSinhList.size();
    }

    private class ViewHolder{
        ImageView imgHinhAnh, imgDelete, imgEdit;
        TextView txtTen, txtLop;
    }
    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            // Ánh xạ
            holder.imgHinhAnh = (ImageView) view.findViewById(R.id.imageHinhAnh);
            holder.imgDelete = (ImageView) view.findViewById(R.id.imageDelete);
            holder.imgEdit = (ImageView) view.findViewById(R.id.imageEdit);
            holder.txtTen = (TextView) view.findViewById(R.id.textViewTenHS);
            holder.txtLop = (TextView) view.findViewById(R.id.textViewLop);

            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        HocSinh hocSinh = hocSinhList.get(i);
        holder.txtTen.setText(hocSinh.getTen());
        holder.txtLop.setText(hocSinh.getLop());

        // chuyển byte[] -> bitmap
        byte[] hinhAnh = hocSinh.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh, 0 , hinhAnh.length);
        holder.imgHinhAnh.setImageBitmap(bitmap);

        // bắt sự kiện Delete và Edit
        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Sửa học sinh " + hocSinh.getTen(), Toast.LENGTH_SHORT).show();
                context.tt(hocSinh);
            }
        });


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.DialogXoaHS(hocSinh.getTen(),hocSinh.getId());
            }
        });
        return view;
    }
}
