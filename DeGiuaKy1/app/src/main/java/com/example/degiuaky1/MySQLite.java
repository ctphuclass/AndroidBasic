package com.example.degiuaky1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class MySQLite extends SQLiteOpenHelper {
    public static final String DBName = "Demo_Sqlite";
    public static final int VERSION = 1;
    public static final String TABLE_MH = "MON_HOC";
    public static final String MH_ID = "ID";
    public static final String MH_TenMH = "TenMH";
    public static final String MH_MaMH = "MaMH";
    public static final String MH_TC = "TinChi";
    public static final String MH_Ten = "TenSV";
    public static final String MH_MSSV = "Mssv";
    public SQLiteDatabase mDB;
    public MySQLite(Context context) {
        super(context, DBName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mQuery = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT, %s TEXT, %s INT, %s TEXT, %s TEXT )",
                                            TABLE_MH,MH_ID,MH_MaMH,MH_TenMH,MH_TC,MH_Ten,MH_MSSV);
        db.execSQL(mQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void OpenDB(){
        mDB = getWritableDatabase();
    }
    public void CloseDB(){
        if (mDB != null){
            mDB.close();
        }
    }

    public List<model> getAll(){
        List<model> modelList = new ArrayList<>();
        String mQuery = String.format("SELECT * FROM %s",TABLE_MH);
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                model item = new model();
                item.setMaMonHoc(cursor.getString(cursor.getColumnIndex(MH_MaMH)));
                item.setTenMonHoc(cursor.getString(cursor.getColumnIndex(MH_TenMH)));
                item.setTinChi(cursor.getString(cursor.getColumnIndex(MH_TC)));
                item.setTen(cursor.getString(cursor.getColumnIndex(MH_Ten)));
                item.setMssv(cursor.getString(cursor.getColumnIndex(MH_Ten)));
                modelList.add(item);
            }
            cursor.close();
        }
        return modelList;
    }

    public void addItem(model item){
        ContentValues values = new ContentValues();
        values.put(MH_MaMH,item.getMaMonHoc());
        values.put(MH_TenMH,item.getTenMonHoc());
        values.put(MH_TC,item.getTinChi());
        values.put(MH_Ten,item.getTen());
        values.put(MH_MSSV,item.getMssv());
        mDB.insert(TABLE_MH,null,values);
    }
}
