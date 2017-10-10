package com.example.pc.mytodolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.IntentService;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.EditText;

import com.example.pc.mytodolist.service.TodoListService;

/**
 * Created by pc on 22.9.2017..
 */

public class AddTaskDialogFragment extends DialogFragment {

    public static AddTaskDialogFragment getInstance(String idTask){
        Bundle bundle = new Bundle();
        bundle.putString("ID_TASK",idTask);
        AddTaskDialogFragment fragment = new AddTaskDialogFragment();
        fragment.setArguments(bundle);

        return  fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setView(R.layout.task_create_dialog);
        builder.setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.btn_add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String task = ((EditText)getDialog().findViewById(R.id.et_add_task)).getText().toString();
                Intent intent = new Intent(getContext(), TodoListService.class);
                intent.putExtra(TodoListService.EXTRA_TASK_DESCRIPTION, task);
                getActivity().startService(intent);
            }
        });

        builder.setNegativeButton(R.string.btn_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }
}
