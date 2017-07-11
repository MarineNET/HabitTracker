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

        displayDatabaseInfo();

        // Assign onclicklistener to a Button
        Button viewTable = (Button) findViewById(R.id.button);
        viewTable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertHabit();
                displayDatabaseInfo();
            }
        });
    }

    private void displayDatabaseInfo() {

        // Create and/or open a database to read from it
        SQLiteDatabase db = mDBHelper.getReadableDatabase();

        // Perform this SQL query "SELECT * FROM habits"
        // to get a Cursor that contains all rows from the habits table.
        Cursor cursor = db.query(HabitTrackerContract.HabitTrackerEntry.TABLE_NAME,
                null, null, null, null, null, null);

        // Find TextView by ID
        TextView displayView = (TextView) findViewById(R.id.tv_habit);

        try {
            // First display this text
            displayView.setText("This is an exercise tracker and it contains "
                    + cursor.getCount() + " completed exercise(s).\n\n");
            // In addition this code will create a header with column names
            displayView.append(HabitTrackerContract.HabitTrackerEntry._ID + " - " +
                    HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_EXERCISE + " - " +
                    HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_REPS + " - " +
                    HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_NOTES);

            // Find out the index of each column
            int idColumnIndex = cursor.getColumnIndex(HabitTrackerContract.HabitTrackerEntry._ID);
            int exerciseColumnIndex = cursor.getColumnIndex
                    (HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_EXERCISE);
            int repsColumnIndex = cursor.getColumnIndex
                    (HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_REPS);
            int notesColumnIndex = cursor.getColumnIndex
                    (HabitTrackerContract.HabitTrackerEntry.COLUMN_HT_NOTES);

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentExercise = cursor.getString(exerciseColumnIndex);
                int currentReps = cursor.getInt(repsColumnIndex);
                String currentNotes = cursor.getString(notesColumnIndex);

                displayView.append("\n" + currentId + " - " + currentExercise +
                        " - " + currentReps + " - " + currentNotes);
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
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
}