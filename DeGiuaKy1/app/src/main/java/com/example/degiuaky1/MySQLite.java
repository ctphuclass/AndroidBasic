package com.example.degiuaky1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.degiuaky1.MyModel.RegisterSubjectModel;
import com.example.degiuaky1.MyModel.StudentModel;
import com.example.degiuaky1.MyModel.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class MySQLite extends SQLiteOpenHelper {
    public static final String DBName = "Demo_Sqlite";
    public static final int VERSION = 1;
    //--Table Danh sach Hoc//
    public static final String TABLE_MH = "MON_HOC";
    public static final String MH_ID = "ID";
    public static final String MH_TenMH = "TenMH";
    public static final String MH_MaMH = "MaMH";
    public static final String MH_TC = "TinChi";
    public static final String MH_Ten = "TenSV";
    public static final String MH_MSSV = "Mssv";
    public static final String MH_Status = "Status";
    //Table Subjects//
    public static final String TABLE_SUBJECTS = "SUBJECT";
    public static final String SUBJECTS_ID = "ID";
    public static final String SUBJECTS_NAME = "NAME";
    //Table students//
    public static final String TABLE_STUDENTS = "STUDENT";
    public static final String STUDENTS_ID = "ID";
    public static final String STUDENTS_NAME = "NAME";

    //Table register subjects//
    public static final String TABLE_REGISTER_SUBJECT = "REGISTER_SUBJECT";
    public static final String REGISTER_ID = "ID";
    public static final String REGISTER_SUBJECT_ID = "SUBJECT_ID";
    public static final String REGISTER_STUDENT_ID = "STUDENT_ID";

    public SQLiteDatabase mDB;
    public MySQLite(Context context) {
        super(context, DBName, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String mQuery = String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, %s TEXT, %s TEXT, %s INT, %s TEXT, %s TEXT ,%s INTERGER )",
                                            TABLE_MH,MH_ID,MH_MaMH,MH_TenMH,MH_TC,MH_Ten,MH_MSSV,MH_Status);
        String mSubjectsQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s TEXT)",TABLE_SUBJECTS
                ,SUBJECTS_ID,SUBJECTS_NAME);
        String mStudentQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s TEXT)",TABLE_STUDENTS,STUDENTS_ID,STUDENTS_NAME);
        String mRegisterSubjectQuery = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,%s INNTEGER,%s INTEGER)",TABLE_REGISTER_SUBJECT,REGISTER_ID,REGISTER_SUBJECT_ID,REGISTER_STUDENT_ID);

        db.execSQL(mRegisterSubjectQuery);
        db.execSQL(mSubjectsQuery);
        db.execSQL(mStudentQuery);
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
                item.setId(cursor.getInt(cursor.getColumnIndex(MH_ID)));
                item.setMaMonHoc(cursor.getString(cursor.getColumnIndex(MH_MaMH)));
                item.setTenMonHoc(cursor.getString(cursor.getColumnIndex(MH_TenMH)));
                item.setTinChi(cursor.getString(cursor.getColumnIndex(MH_TC)));
                item.setTen(cursor.getString(cursor.getColumnIndex(MH_Ten)));
                item.setMssv(cursor.getString(cursor.getColumnIndex(MH_Ten)));
                item.setStatus(cursor.getInt(cursor.getColumnIndex(MH_Status)));
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

    public int updateItem(int id,int status){
//        String mQuery = String.format("UPDATE %s SET %s = %d WHERE %s = %d",TABLE_MH,MH_Status,status,MH_ID,id);
//        mDB.execSQL(mQuery);
        String mClause = String.format("%s = %d",MH_ID,id);
        ContentValues values = new ContentValues();
        values.put(MH_Status,status);
        int isSuccess = mDB.update(TABLE_MH,values,mClause,null);
        return isSuccess;
    }

    public List<SubjectModel> getAllSubject(){
        List<SubjectModel> models = new ArrayList<>();
        String mQuery = "SELECT * FROM " + TABLE_SUBJECTS;
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                SubjectModel model = new SubjectModel();
                model.setId(cursor.getInt(0));
                model.setName(cursor.getString(1));
                models.addAll(models);
            }
        }
        cursor.close();
        return models;
    }
    public List<String> getSubjectNames(){
        List<String> models = new ArrayList();
        String mQuery = "SELECT " +SUBJECTS_NAME+" FROM " + TABLE_SUBJECTS;
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                String m = cursor.getString(0);
                models.add(m);
            }
        }
        cursor.close();
        return models;
    }

    public int getSubjectIdByName(String mName){
        String mQuery = "SELECT " +SUBJECTS_ID+" FROM " + TABLE_SUBJECTS + " WHERE " + SUBJECTS_NAME + " like " + "'" + mName + "'";
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            return cursor.getInt(0);
        }
        cursor.close();
        return 0;
    }

    public long addSubjet(String mName){
        ContentValues values = new ContentValues();
        values.put(SUBJECTS_NAME,mName);
        long isSuccess = mDB.insert(TABLE_SUBJECTS,null,values);
        return isSuccess;
    }

    public List<StudentModel> getAllStudent(){
        List<StudentModel> models = new ArrayList<>();
        String mQuery = "SELECT * FROM " + TABLE_STUDENTS;
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                StudentModel model = new StudentModel();
                model.setId(cursor.getInt(0));
                model.setName(cursor.getString(1));
                models.add(model);
            }
        }
        cursor.close();
        return models;
    }
    public long addStudent(String mName){
        ContentValues values = new ContentValues();
        values.put(STUDENTS_NAME,mName);
        long isSuccess = mDB.insert(TABLE_STUDENTS,null,values);
        return isSuccess;
    }

    public StudentModel getStudentById(int Id){
        String mQuery = String.format("SELECT * FROM %s WHERE ID = %d",TABLE_STUDENTS,Id);
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToNext()){
            StudentModel model = new StudentModel();
            model.setId(cursor.getInt(0));
            model.setName(cursor.getString(1));
            return model;
        }
        return null;
    }

    public List<RegisterSubjectModel> getAllRegisterSubject(){
        List<RegisterSubjectModel> models = new ArrayList<>();
        String mQuery = "SELECT * FROM " + TABLE_REGISTER_SUBJECT;
        Cursor cursor = mDB.rawQuery(mQuery,null);
        if (cursor.moveToFirst()){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                RegisterSubjectModel model = new RegisterSubjectModel();
                model.setId(cursor.getInt(0));
                model.setSubjectId(cursor.getInt(1));
                model.setStudentId(cursor.getInt(2));
                models.add(model);
            }
        }
        cursor.close();
        return models;
    }
    public long addRegisterSubject(int SubjectId,int StudentId){
        ContentValues values = new ContentValues();
        values.put(REGISTER_SUBJECT_ID,SubjectId);
        values.put(REGISTER_STUDENT_ID,STUDENTS_ID);
        long isSuccess = mDB.insert(TABLE_REGISTER_SUBJECT,null,values);
        return isSuccess;
    }
}
