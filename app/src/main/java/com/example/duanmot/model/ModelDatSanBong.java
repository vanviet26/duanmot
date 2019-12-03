package com.example.duanmot.model;

public class ModelDatSanBong {
    private String mMaSan;
    private String mSDT;
    private String mTen;
    private String mDate;
    private String mGioSan;
    private int mGia;
    private String mLoaiSan;

    public ModelDatSanBong(String mMaSan, String mSDT, String mTen, String mDate,String mLoaiSan, String mGioSan, int mGia ) {
        this.mMaSan = mMaSan;
        this.mSDT = mSDT;
        this.mTen = mTen;
        this.mDate = mDate;
        this.mGioSan = mGioSan;
        this.mGia = mGia;
        this.mLoaiSan = mLoaiSan;
    }

    public ModelDatSanBong() {
    }

    public String getmMaSan() {
        return mMaSan;
    }

    public void setmMaSan(String mMaSan) {
        this.mMaSan = mMaSan;
    }

    public String getmSDT() {
        return mSDT;
    }

    public void setmSDT(String mSDT) {
        this.mSDT = mSDT;
    }

    public String getmTen() {
        return mTen;
    }

    public void setmTen(String mTen) {
        this.mTen = mTen;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }

    public String getmGioSan() {
        return mGioSan;
    }

    public void setmGioSan(String mGioSan) {
        this.mGioSan = mGioSan;
    }

    public int getmGia() {
        return mGia;
    }

    public void setmGia(int mGia) {
        this.mGia = mGia;
    }

    public String getmLoaiSan() {
        return mLoaiSan;
    }

    public void setmLoaiSan(String mLoaiSan) {
        this.mLoaiSan = mLoaiSan;
    }
}