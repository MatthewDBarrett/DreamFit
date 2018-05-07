package com.example.ethan.dream_fit;
package com.tabian.saveanddisplaysql;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class databaseHelper extends SQLiteOpenHelper{

 private static final String TABLE_NAME = "food_Items";
 private static final String COL1= "food_Item_Activiy";
 private static final String COL2= "calories";
    public databaseHelper(Context context) {
    super(contet, TABLE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String createTable = "CREATE TABLE" + TABLE_NAME + "()";
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
