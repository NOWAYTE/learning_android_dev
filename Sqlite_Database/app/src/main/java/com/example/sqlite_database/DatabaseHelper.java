package com.example.sqlite_database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

public class DatabaseHelper extends SQLiteOpenHelper {

    //first part we are just declared some constants

    //table Name:

    public static final String TABLE_NAME = "COUNTRIES";

    //table Columns
    public static final String ID = "_id";
    public  static final String SUBJECT = "subject";
    public static final String DESC = "description";


    //database information
    static  final String DB_NAME = "MASTER_ANDROID_SQLITE_DB";


    //database version

    static final int DB_VERSION = 1;

    //creating Table Query: CREATE TABLE an sql command to create a new table in db

//    private static final String CREATE_TABLE = "create table " +
//            TABLE_NAME + "(" + ID
//            + "INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
//            " TEXT NOT NULL," + DESC + "TEXT);";

    private static final String CREATE_TABLE = "create table " +
            TABLE_NAME + "(" + ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT +
            " TEXT NOT NULL," + DESC + " TEXT);";




    // constructor

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //executing the query
        db.execSQL(CREATE_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


}
