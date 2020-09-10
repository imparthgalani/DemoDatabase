package com.rku.demodatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE = "college";
    public static final String TABLE = "student";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "NAME";
    public static final String COL_3 = "SURNAME";
    public static final String COL_4 = "MARKS";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE, null, 1);
        SQLiteDatabase db =getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)";
        Log.i("sql",sql);
        sqLiteDatabase.execSQL(sql);

       /* sqLiteDatabase.execSQL("create table " + TABLE +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,SURNAME TEXT,MARKS INTEGER)");*/

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(sqLiteDatabase);
    }

    public  boolean insertData(String name, String surname, String marks){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_2,name);
        values.put(COL_3,surname);
        values.put(COL_4,marks);

        // insert into table_name (name,city,branch) values ('abc','rjt','ce')
        long result = db.insert(TABLE,null,values);
        return  (result == -1) ? false : true;
    }

    public Cursor selectData(){
        SQLiteDatabase db =getWritableDatabase();

        //Select * form Table
        Cursor cursor = db.query(TABLE,null,null,null,null,null,null);
        return cursor;
    }
}
