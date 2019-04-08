package com.example.fourthapplication.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.fourthapplication.model.UserModel;

import java.util.ArrayList;
import java.util.List;

public class MySQLite extends SQLiteOpenHelper {

    private static final String DB_NAME = "MY_DB";
    private static final int VERSION = 1;
    private static final String ID="ID";
    private SQLiteDatabase mDB;

    private static final String TABLE_USER = "USER";
    private static final String USER_NAME = "USERNAME";
    private static final String USER_AGE = "AGE";
    private static final String USER_ADDRESS = "ADDRESS";

    public MySQLite(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    public void OpenDB(){
        mDB = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String mQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s TEXT,%s INTEGER,%s TEXT)",
                                                    TABLE_USER,ID,USER_NAME,USER_AGE,USER_ADDRESS);
        sqLiteDatabase.execSQL(mQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public List<UserModel> getAllUser(){
        List<UserModel> modelList = new ArrayList<>();
        String mQuery = String.format("SELECT * FROM %s",TABLE_USER);
        Cursor cursor =mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                UserModel model = new UserModel();
                model.setID(cursor.getInt(0));
                model.setUserName(cursor.getString(cursor.getColumnIndex(USER_NAME)));
                model.setAge(cursor.getInt(2));
                model.setAddress(cursor.getString(3));
                modelList.add(model);
            }
            return modelList;
        }
        return new ArrayList<>();
    }

    public long addUser(UserModel model){
        ContentValues values = new ContentValues();
        values.put(USER_NAME,model.getUserName());
        values.put(USER_AGE,model.getAge());
        values.put(USER_ADDRESS,model.getAddress());
        long i = mDB.insert(TABLE_USER,null,values);
        return i;
    }

    public long updateUser(UserModel model){
        String mQuery = ID + " = " + model.getID();
        ContentValues values = new ContentValues();
        values.put(USER_NAME,model.getUserName());
        values.put(USER_AGE,model.getAge());
        values.put(USER_ADDRESS,model.getAddress());
        long i = mDB.update(TABLE_USER,values,mQuery,null);
        return i;
    }

    public void deleteUser(int mID){
        String mQuery = String.format("DELETE FROM %s WHERE %s = %d",TABLE_USER,ID,mID);
        mDB.execSQL(mQuery);
    }

    public int countUser(){
        String mQuery = "SELECT COUNT(ID) FROM " + TABLE_USER;
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            return  cursor.getInt(0);
        }
        return 0;
    }
}
