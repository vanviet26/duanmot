package com.example.duanmot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.duanmot.database.DataBaseHelper;
import com.example.duanmot.model.ModelDatSanBong;

import java.util.ArrayList;
import java.util.List;

public class DaoDatSanBong {
    SQLiteDatabase db;
    DataBaseHelper dataBaseHelper;
    public static final String TABLE_NAME = "datsanbong";
    public static final String SQL_DATSANBONG = "CREATE TABLE datsanbong ( masan text primary key,sdt integer , " +
            "ten text, ngay date, loaiSan text, " +
            "giovao time, giora time, giatien integer );";

    public DaoDatSanBong(Context context) {
        dataBaseHelper = new DataBaseHelper(context);
        db = dataBaseHelper.getWritableDatabase();
    }

    public int addDatSanBong(ModelDatSanBong datSanBong) {
        ContentValues values = new ContentValues();
        values.put("masan", datSanBong.getmMaSan());
        values.put("sdt", datSanBong.getmSDT());
        values.put("ten", datSanBong.getmTen());
        values.put("ngay", datSanBong.getmDate());
        values.put("loaiSan", String.valueOf(datSanBong.getmLoaiSan()));
        values.put("giovao", datSanBong.getmGioVao());
        values.put("giora", datSanBong.getmGiora());
        values.put("giatien", datSanBong.getmGia());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {

        }
        return 1;
    }
    public List<ModelDatSanBong> viewLichdat(){
            List<ModelDatSanBong> modelDatSanBongs = new ArrayList<>();

            return modelDatSanBongs;
    }

}
