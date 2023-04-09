package com.example.booklibrary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "M-Hike.db";
    private static final int DATABASE_VERSION = 1;
    private static  final String TABLE_NAME = "my_hike";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAME = "name_hike";
    private static final String COLUMN_DATE = "date_hike";
    private static final String COLUMN_PARKING = "parking";
    private static final String COLUMN_LENGTH = "length_hike";
    private static final String COLUMN_LOCATION = "name_location";
    private static final String COLUMN_DIFFICULTY = "level_difficulty";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_NUMBER = "number_people";
    private static final String COLUMN_OBSERVATION = "observation";
    private static final String COLUMN_TIME = "time_observation";
    private static final String COLUMN_COMMENTS = "observation_comments";




    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query =
                "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_NAME + " TEXT, " +
                        COLUMN_DATE + " TEXT, " +
                        COLUMN_PARKING + " TEXT, " +
                        COLUMN_LENGTH + " TEXT, " +
                        COLUMN_LOCATION + " TEXT, " +
                        COLUMN_DIFFICULTY + " TEXT, " +
                        COLUMN_DESCRIPTION + " TEXT, " +
                        COLUMN_NUMBER + " INTEGER, " +
                        COLUMN_OBSERVATION + " TEXT, " +
                        COLUMN_TIME + " TEXT, " +
                        COLUMN_COMMENTS + " TEXT);";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }

    void addBook(String name, String date, String parking,
                 Integer length, String location, String difficulty,
                 String desciption, String observation,String time_observation,
                 String comments){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PARKING, parking);
        cv.put(COLUMN_LENGTH, length);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_DIFFICULTY, difficulty);
        cv.put(COLUMN_DESCRIPTION, desciption);
        cv.put(COLUMN_OBSERVATION, observation);
        cv.put(COLUMN_TIME, time_observation);
        cv.put(COLUMN_COMMENTS, comments);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == 1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "saved successfully", Toast.LENGTH_SHORT).show();
        }
        
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();


        Cursor cursor = null;
        if(db != null){
           cursor =  db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String name, String date, String parking, String length, String location, String difficulty, String desciption, String observation, String time_observation, String comments){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_PARKING, parking);
        cv.put(COLUMN_LENGTH, length);
        cv.put(COLUMN_LOCATION, location);
        cv.put(COLUMN_DIFFICULTY, difficulty);
        cv.put(COLUMN_DESCRIPTION, desciption);
        cv.put(COLUMN_OBSERVATION, observation);
        cv.put(COLUMN_TIME, time_observation);
        cv.put(COLUMN_COMMENTS, comments);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1){
            Toast.makeText(context, "failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "success", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Failed to delete", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully deleted", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
