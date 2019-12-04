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
    public static final String SQL_DATSANBONG = "CREATE TABLE datsanbong ( masan text primary key,sdt text , " +
            "ten text, ngay date, loaiSan text, " +
            "gio time, giatien integer );";

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
        values.put("gio",datSanBong.getmGioSan());
        values.put("giatien", datSanBong.getmGia());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception e) {

        }
        return 1;
    }
    public int updateDatSan(String maSan, String sdt,String ten,String ngay, String loaiSan,String time,int gia) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("sdt", sdt);
        contentValues.put("ten", ten);
        contentValues.put("ngay", ngay);
        contentValues.put("loaiSan", loaiSan);
        contentValues.put("gio", time);
        contentValues.put("giatien", gia);
        int result = db.update(TABLE_NAME, contentValues, "masan=?",
                new String[]{maSan});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int deleteDatSan(String maSan) {
        int result = db.delete(TABLE_NAME, "masan=?", new String[]{maSan});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public List<ModelDatSanBong> viewDatSan(String ngay ,String loaisan){
        List<ModelDatSanBong> listDatSanBong = new ArrayList<>();
        String sql = "SELECT ten, sdt, gio from datsanbong where ngay = '"+ ngay + "'  and loaiSan = '"+loaisan+"'  ";
        Cursor cursor = db.rawQuery(sql,null);
        if (cursor.moveToFirst()){
            do {
                ModelDatSanBong modelDatSanBong1 = new ModelDatSanBong();
                modelDatSanBong1.setmTen(cursor.getString(0));
                modelDatSanBong1.setmSDT(cursor.getString(1));
                modelDatSanBong1.setmGioSan(cursor.getString(2));
                listDatSanBong.add(modelDatSanBong1);
            }while (cursor.moveToNext());
        }
        cursor.close();

        return  listDatSanBong;
    }
    public List<ModelDatSanBong> viewAll() {
        List<ModelDatSanBong> listDatSanBong = new ArrayList<>();
        String sql = "SELECT * from datsanbong ";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                ModelDatSanBong modelDatSanBong1 = new ModelDatSanBong();
                modelDatSanBong1.setmMaSan(cursor.getString(0));
                modelDatSanBong1.setmSDT(cursor.getString(1));
                modelDatSanBong1.setmTen(cursor.getString(2));
                modelDatSanBong1.setmDate(cursor.getString(3));
                modelDatSanBong1.setmLoaiSan(cursor.getString(4));
                modelDatSanBong1.setmGioSan(cursor.getString(5));
                modelDatSanBong1.setmGia(Integer.parseInt(cursor.getString(6)));
                listDatSanBong.add(modelDatSanBong1);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return listDatSanBong;
    }
}
