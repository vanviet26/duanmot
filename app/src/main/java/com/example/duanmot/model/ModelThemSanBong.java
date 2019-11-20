package com.example.duanmot.model;

public class ModelThemSanBong {
    private String mMaSanBong;
    private String mLoaiSan;

    public ModelThemSanBong(String mMaSanBong, String mLoaiSan) {
        this.mMaSanBong = mMaSanBong;
        this.mLoaiSan = mLoaiSan;
    }

    public ModelThemSanBong() {
    }

    public String getmMaSanBong() {
        return mMaSanBong;
    }

    public void setmMaSanBong(String mMaSanBong) {
        this.mMaSanBong = mMaSanBong;
    }

    public String getmLoaiSan() {
        return mLoaiSan;
    }

    public void setmLoaiSan(String mLoaiSan) {
        this.mLoaiSan = mLoaiSan;
    }

    @Override
    public String toString() {
        return mLoaiSan;
    }
}
