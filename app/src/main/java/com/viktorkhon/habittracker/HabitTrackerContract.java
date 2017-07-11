package com.viktorkhon.habittracker;

import android.provider.BaseColumns;

/**
 * Created by Viktor Khon on 7/11/2017.
 */

public class HabitTrackerContract {

    public static class HabitTrackerEntry implements BaseColumns {
        // Create new table with a name Habit Tracker
        public static final String TABLE_NAME = "Habit_Tracker";
        public static final String _ID = BaseColumns._ID;

        // name of exercise performed
        public static final String COLUMN_HT_EXERCISE = "exercise";

        // number of reps performed during exercise
        public static final String COLUMN_HT_REPS = "repetitions";

        // any additional notes
        public static final String COLUMN_HT_NOTES = "notes";
    }
}
