package com.example.duanmot.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.duanmot.dao.DaoDatSanBong;
import com.example.duanmot.dao.DaoThemSanBong;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "quanlusanbongda";
    public static int version = 1;
    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DaoThemSanBong.SQL_THEMSANBONG);
        db.execSQL(DaoDatSanBong.SQL_DATSANBONG);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+DaoThemSanBong.TABLE_NAME);
        db.execSQL("Drop table if exists "+DaoDatSanBong.TABLE_NAME);
        onCreate(db);
    }
}
