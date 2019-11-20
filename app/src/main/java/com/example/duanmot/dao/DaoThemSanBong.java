package com.example.duanmot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.database.DataBaseHelper;
import com.example.duanmot.model.ModelThemSanBong;

import java.util.ArrayList;
import java.util.List;

public class DaoThemSanBong {
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    public static final String TABLE_NAME = "themsanbong";
    public static final String SQL_THEMSANBONG = "CREATE TABLE themsanbong (masan primary key, loaisan text );";

    public DaoThemSanBong(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void addSanBong(ModelThemSanBong modelThemSanBong) {
        ContentValues values = new ContentValues();
        values.put("masan", modelThemSanBong.getmMaSanBong());
        values.put("loaisan", modelThemSanBong.getmLoaiSan());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<ModelThemSanBong> getALLSanBong() {
        List<ModelThemSanBong> modelThemSanBongs = new ArrayList<>();
        String Sql_allSan = "SELECT * FROM " + TABLE_NAME;
        Cursor cursor = db.rawQuery(Sql_allSan, null);
        if (cursor.moveToFirst()) {
            do {
                ModelThemSanBong model = new ModelThemSanBong();
                model.setmMaSanBong(cursor.getString(0));
                model.setmLoaiSan(cursor.getString(1));
                modelThemSanBongs.add(model);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return modelThemSanBongs;
    }

    public int updateSanBong(String maSanBong, String loaiSan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("loaisan", loaiSan);
        int result = db.update(TABLE_NAME, contentValues, "masan=?",
                new String[]{maSanBong});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
    public int deleteSanBong(String maSanBong) {
        int result = db.delete(TABLE_NAME, "masan=?", new String[]{maSanBong});
        if (result == 0) {
            return -1;
        }
        return 1;
    }
}
