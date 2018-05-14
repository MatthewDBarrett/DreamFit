package com.example.ethan.dream_fit;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "calories_Tracker_DB";
    public static final String TABLE_NAME = "food_Items";

    public static final String COL1 = "ID" ;
    public static final String COL2 = "DESCRIPTION" ;
    public static final String COL3 = "CALORIE";

    //default Constructor
    // it is taking in the database_Name, version and factor value as null
    public DatabaseHelper(Context context) {

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
        contentValues.put(COL3, calorie);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false ;
        else
            return true ;
    }


}
