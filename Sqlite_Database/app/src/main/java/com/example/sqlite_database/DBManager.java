package com.example.sqlite_database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private  DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    //constructor

    public DBManager(Context c){
        context = c;


    }

    public DBManager open() throws SQLException{
        dbHelper =new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close(){

        dbHelper.close();
    }

    public void insert(String name, String desc){
        ContentValues contentValues  =new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);
        database.insert(DatabaseHelper.TABLE_NAME,  null, contentValues);

    }


    public Cursor fetch(){
        String [] columns = new String[] {DatabaseHelper.ID,
        DatabaseHelper.SUBJECT,DatabaseHelper.DESC};

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME,
                columns,
                null,
                null,
                null,
                null,
                null);

        if(cursor != null){
            cursor.moveToFirst();


        }

        return cursor;
    }

    public int update(long ID, String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.SUBJECT, name);
        contentValues.put(DatabaseHelper.DESC, desc);

      int i = database.update(DatabaseHelper.TABLE_NAME,
              contentValues, DatabaseHelper.ID +
              " = " + ID, null);

        return i;
    }

    public void delete(long id){
        database.delete(DatabaseHelper.TABLE_NAME,
                DatabaseHelper.ID + " = " + id, null);
    }


}
