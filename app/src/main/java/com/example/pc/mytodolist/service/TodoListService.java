package com.example.pc.mytodolist.service;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.example.pc.mytodolist.data.TodoListContract;
import com.example.pc.mytodolist.data.TodoListContract.TodoEntry;

/**
 * Created by Nedim on 07.10.2017..
 */

public class TodoListService extends IntentService {
    public static final String EXTRA_TASK_DESCRIPTION = "EXTRA_TASK_DESCRIPTION";
    public TodoListService() {
        super("TodoListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String taskDescription = intent.getStringExtra(EXTRA_TASK_DESCRIPTION);

        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoEntry.COLUMN_DESCRIPTION, taskDescription);

        getContentResolver().insert(TodoEntry.CONTENT_URI, contentValues);
    }
}
