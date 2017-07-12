package com.viktorkhon.habittracker;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.viktorkhon.habittracker.HabitTrackerContract;
import com.viktorkhon.habittracker.HabitTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

    HabitTrackerDbHelper mDBHelper = new HabitTrackerDbHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void insertHabit() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDBHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys, followed by a value
        ContentValues values = new ContentValues();
        values.put(HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_EXERCISE, "Push-ups");
        values.put(HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_REPS, 100);
        values.put(HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_NOTES,
                "Reached my goal of 100 reps today");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitTrackerContract.HabitTrackerEntry.TABLE_NAME, null, values);
    }

    private Cursor mCursor () {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        // Perform this SQL query "SELECT * FROM habits"
        // to get a Cursor that contains all rows from the habits table.
        Cursor cursor = db.query(HabitTrackerContract.HabitTrackerEntry.TABLE_NAME,
                null, null, null, null, null, null);

        cursor.close();

        return cursor;
    }
}