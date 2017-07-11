package com.viktorkhon.habittracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Viktor Khon on 7/11/2017.
 */

public class HabitTrackerDbHelper extends SQLiteOpenHelper{
    // Initialize original version as #1
    public static final int DATABASE_VERSION = 1;

    // give a name to the new database
    public static final String DATABASE_NAME = "habits.db";

    public HabitTrackerDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Define on how to create a new table
        String SQL_CREATE_ENTRIES =
                "CREATE TABLE " + HabitTrackerContract.HabitTrackerEntry.TABLE_NAME + " (" +
                        HabitTrackerContract.HabitTrackerEntry._ID +
                        " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_EXERCISE
                        + " TEXT NOT NULL," +
                        HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_REPS
                        + " INTEGER NOT NULL," +
                        HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_NOTES
                        + " TEXT)";
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    // During upgrade, delete current table and then create a new one with updated information
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String SQL_DELETE_ENTRIES =
                "DROP TABLE IF EXISTS " + HabitTrackerContract.HabitTrackerEntry.TABLE_NAME;
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}