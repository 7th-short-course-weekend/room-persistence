package com.rathana.roompersistence_demo.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.rathana.roompersistence_demo.R;

public class FromDialog extends DialogFragment {

    EditText categoryName;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("create category");
        View view= LayoutInflater.from(getActivity())
                .inflate(R.layout.add_category_dialog_layout,null);
        builder.setView(view);
        categoryName=view.findViewById(R.id.name);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(callback!=null)
                    callback.onClicked(categoryName.getText().toString());
            }
        });

        return builder.create();
    }

    private DialogCallback callback;

    public void setCallback(DialogCallback callback) {
        this.callback = callback;
    }

    public interface DialogCallback<T>{
        void onClicked(T data);
    }
}
