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
import com.rathana.roompersistence_demo.data.entity.Category;

public class FormDialog extends DialogFragment {

    Category category;
    String buttonLabel;
    public void setData(Category category) {
        this.category = category;
    }

    public void setButtonLabel(String buttonLabel) {
        this.buttonLabel = buttonLabel;
    }

    EditText categoryName;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("create category");
        View view= LayoutInflater.from(getActivity())
                .inflate(R.layout.add_category_dialog_layout,null);
        builder.setView(view);
        builder.setCancelable(false);
        categoryName=view.findViewById(R.id.name);

        if(category!=null)
            categoryName.setText(category.name);

        builder.setPositiveButton(buttonLabel==null? "save": buttonLabel, new DialogInterface.OnClickListener() {
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
