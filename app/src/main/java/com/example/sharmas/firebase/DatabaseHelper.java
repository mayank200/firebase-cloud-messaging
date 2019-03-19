package com.example.sharmas.firebase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public DatabaseHelper(Context context) {
        super(context, "Student.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Student (user_id text,day text,hal_id text,M_view text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Student");
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
        onUpgrade(db, oldVersion, newVersion);
    }

    long saveDetails(String email,String name,String password,String mview) throws Exception{
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("user_id", email);
        contentValues.put("day", name);
        contentValues.put("hal_id", password);
        contentValues.put("M_view", mview);
        return sqLiteDatabase.insert("Student", null, contentValues);
    }

    ArrayList show() throws Exception{
        ArrayList arrayList=new ArrayList();
        SQLiteDatabase sqLiteDatabase=this.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select * from Student", null);
        while(cursor.moveToNext()){
            arrayList.add(cursor.getString(cursor.getColumnIndex("user_id")));
        }
        return arrayList;
    }


}

