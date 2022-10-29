package com.example.newapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class FDBHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "blueblood.db";
    public static final String TABLE_NAME = "exercise_table";
    public static final int DB_VERSION = 1;
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Title";
    public static final String COL_3 = "Ex1";
    public static final String COL_4 = "Ex2";
    public static final String COL_5 = "Ex3";
    public static final String COL_6 = "Ex4";

    public FDBHelper(@Nullable Context context) {
        super(context , DATABASE_NAME , null , DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT , Title TEXT , Ex1 TEXT , Ex2 TEXT , Ex3 TEXT , Ex4 TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    void addPlan(String title , String ex1 , String ex2 , String ex3 , String ex4){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_2,title);
        contentValues.put(COL_3, ex1);
        contentValues.put(COL_4, ex2);
        contentValues.put(COL_5, ex3);
        contentValues.put(COL_6, ex4);

        long res =   sqLiteDatabase.insert(TABLE_NAME,null,contentValues);

        if (res == -1){
            Toast.makeText(context,"Plan creation failed",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Plan was Created Successfully",Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    void updateData(String id , String title , String ex1 , String ex2 , String ex3 , String ex4){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2 , title);
        contentValues.put(COL_3 , ex1);
        contentValues.put(COL_4 , ex2);
        contentValues.put(COL_5 , ex3);
        contentValues.put(COL_6 , ex4);

        long result =sqLiteDatabase.update(TABLE_NAME , contentValues , "id=?" , new String[] {id});

        if (result == -1) {
            Toast.makeText(context,"Plan was Failed to update",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Plan was Successfully Updated",Toast.LENGTH_SHORT).show();
        }
    }

    void deleteData(String id){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result = sqLiteDatabase.delete(TABLE_NAME, "id=?" , new String[] {id});

        if(result == -1){
            Toast.makeText(context, "Plan was Failed to Delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context,"Plan Deleted Successfully" , Toast.LENGTH_SHORT ).show();
        }
    }
}
