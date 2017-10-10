package com.example.pc.mytodolist;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.pc.mytodolist.data.TodoListContract.TodoEntry;
import com.example.pc.mytodolist.TodoCursorAdapter;
import com.example.pc.mytodolist.TodoCursorAdapter.ToggleTodoCheckListener;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>,
        ToggleTodoCheckListener
{
    private TodoCursorAdapter cursorAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView mRecycleView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecycleView.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        mRecycleView.addItemDecoration(itemDecoration);
        mRecycleView.setHasFixedSize(true);

        cursorAdapter = new TodoCursorAdapter(null, this);
        mRecycleView.setAdapter(cursorAdapter);

        FloatingActionButton fbAddButton = (FloatingActionButton) findViewById(R.id.fb_add_item);
        fbAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddTaskDialogFragment dialogFragment = new AddTaskDialogFragment();
                dialogFragment.show(getSupportFragmentManager(), "addTask");
            }
        });

        //Loader inicialization
        getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new CursorLoader(this, TodoEntry.CONTENT_URI, null, null, null,null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        loader.cancelLoad();
    }

    @Override
    public void onTodoItemChange(int todoID, boolean done) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TodoEntry.COLUMN_DONE, done ? 1 : 0);
        String [] mSelectionArgs = {Integer.toString(todoID)};

        getContentResolver().update(TodoEntry.CONTENT_URI, contentValues, TodoEntry.WHERE_TODO_ID, mSelectionArgs);
    }
}
