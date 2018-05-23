package com.example.ethan.dream_fit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper_Main extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "calories_Tracker_DB_2";
    public static final String TABLE_NAME = "food_Items_main";

    public static final String COL1 = "ID" ;
    public static final String COL2 = "DESCRIPTION" ;
    public static final String COL3 = "CALORIE";

    //default Constructor
    // it is taking in the database_Name, version and factor value as null
    public DatabaseHelper_Main(Context context) {

        super(context, DATABASE_NAME, null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    //onCreate method will create a table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "DESCRIPTION TEXT, CALORIE TEXT )" ;

        //create table
        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        // drop Old table if it exits
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // recreate table
        onCreate(db);
    }

    public boolean addData(String description, String calorie){
        SQLiteDatabase db = this.getWritableDatabase() ;
        ContentValues contentValues = new ContentValues();

        //adding values from method paramter to content value
        contentValues.put(COL2, description);
        contentValues.put(COL3, calorie );

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false ;
        else
            return true ;
    }

    public void clearDatabase(){
        SQLiteDatabase db = this.getWritableDatabase();
        // this will clear all the rows from the existing table in this database

        String clearDBQuery = "DELETE FROM " + TABLE_NAME ;
        db.execSQL(clearDBQuery);
    }

    public void removeItem(String description){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME + " WHERE " + COL2+ "= '" + description+ "'");
        db.close();
    }

    public Cursor getListContents(){
        //Uses a (select  Query) i.e select all from table

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }


}
