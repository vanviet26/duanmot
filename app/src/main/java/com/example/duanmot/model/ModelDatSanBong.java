package com.example.duanmot.model;

public class ModelDatSanBong {
    private String mMaSan;
    private int mSDT;
    private String mTen;
    private String mDate;
    private String mGioVao;
    private String mGiora;
    private int mGia;
    private String mLoaiSan;

    public ModelDatSanBong(String mMaSan,int mSDT, String mTen, String mDate, String mLoaiSan, String mGioVao, String mGiora, int mGia) {
        this.mMaSan= mMaSan;
        this.mSDT = mSDT;
        this.mTen = mTen;
        this.mDate = mDate;
        this.mGioVao = mGioVao;
        this.mGiora = mGiora;
        this.mGia = mGia;
        this.mLoaiSan = mLoaiSan;
    }

    public String getmMaSan() {
        return mMaSan;
    }

    public void setmMaSan(String mMaSan) {
        this.mMaSan = mMaSan;
    }

    public int getmSDT() {
        return mSDT;
    }

    public void setmSDT(int mSDT) {
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

    public String getmGioVao() {
        return mGioVao;
    }

    public void setmGioVao(String mGioVao) {
        this.mGioVao = mGioVao;
    }

    public String getmGiora() {
        return mGiora;
    }

    public void setmGiora(String mGiora) {
        this.mGiora = mGiora;
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
