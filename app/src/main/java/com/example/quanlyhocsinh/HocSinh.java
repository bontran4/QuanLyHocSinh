package com.example.quanlyhocsinh;

import java.io.Serializable;

public class HocSinh implements Serializable {
    private int Id;
    private String Ten;
    private String Lop;
    private byte[] hinh;

    public HocSinh(int id, String ten, String lop, byte[] hinh) {
        Id = id;
        Ten = ten;
        Lop = lop;
        this.hinh = hinh;
    }

    public int getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public byte[] getHinh() {
        return hinh;
    }

    public void setHinh(byte[] hinh) {
        this.hinh = hinh;
    }
}
