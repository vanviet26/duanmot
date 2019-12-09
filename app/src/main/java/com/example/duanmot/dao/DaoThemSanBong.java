package com.example.duanmot.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmot.database.DataBaseHelper;
import com.example.duanmot.model.ModelThemSanBong;

import java.util.ArrayList;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class DaoThemSanBong {
    private SQLiteDatabase db;
    private DataBaseHelper dbHelper;
    public static final String TABLE_NAME = "themsanbong";
    public static final String SQL_THEMSANBONG = "CREATE TABLE themsanbong ( mathemsan primary key, loaisan text );";

    public DaoThemSanBong(Context context) {
        dbHelper = new DataBaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public int addSanBong(ModelThemSanBong modelThemSanBong) {
        ContentValues values = new ContentValues();
        values.put("mathemsan", modelThemSanBong.getmMaSanBong());
        values.put("loaisan", modelThemSanBong.getmLoaiSan());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
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
        return modelThemSanBongs;
    }

    public int updateSanBong(String maSanBong, String loaiSan) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("loaisan", loaiSan);
        int result = db.update(TABLE_NAME, contentValues, "mathemsan=?",
                new String[]{maSanBong});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int deleteSanBong(String maSanBong) {
        int result = db.delete(TABLE_NAME, "mathemsan=?", new String[]{maSanBong});
        if (result == 0) {
            return -1;
        }
        return 1;
    }


    public List<ModelThemSanBong> getSanByTime( String date,String input, String output) {
        List<ModelThemSanBong> modelThemSanBongs = new ArrayList<>();
        String sql = "SELECT themsanbong.mathemsan,themsanbong.loaisan FROM themsanbong \n" +
                "INNER JOIN datsanbong on datsanbong.loaiSan=themsanbong.loaisan or datsanbong.loaiSan<>themsanbong.loaisan " +
                "WHERE (ngay <> '"+date+"' or ngay == '"+date+"')  AND giovao <> '" + input + "'  AND giora <> '" + output + "' ";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                ModelThemSanBong modelThemSanBong = new ModelThemSanBong();
                modelThemSanBong.setmMaSanBong(cursor.getString(0));
                modelThemSanBong.setmLoaiSan(cursor.getString(1));
                modelThemSanBongs.add(modelThemSanBong);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return modelThemSanBongs;
    }
}
