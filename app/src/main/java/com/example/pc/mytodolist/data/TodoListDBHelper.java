package com.example.pc.mytodolist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.pc.mytodolist.data.TodoListContract.TodoEntry;
/**
 * Created by Nedim on 05.10.2017..
 */

public class TodoListDBHelper extends SQLiteOpenHelper {
    //Ako promjenimo šemu baze moramo povećati i verziju baze
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "todolist.db";

    public TodoListDBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + TodoEntry.TABLE_NAME + " (" +
                TodoEntry._ID + " INTEGER PRIMARY KEY, " +
                TodoEntry.COLUMN_DATE + " TEXT NOT NULL, " +
                TodoEntry.COLUMN_DESCRIPTION + " TEXT NOT NULL, " +
                TodoEntry.COLUMN_DONE+ " INTEGER, " +
                "UNIQUE (" + TodoEntry.COLUMN_DESCRIPTION + " , " + TodoEntry.COLUMN_DATE + ") ON " +
                "CONFLICT IGNORE );";

        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME);
        onCreate(db);
    }
}
