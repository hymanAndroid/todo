package com.zhy.todo.fragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

/**
 * Created by zhy on 16/8/17.
 */
public class InputDialog extends DialogFragment
{
    public interface OnButtonClickListener
    {
        void onSure(String content);

        void onCancel();
    }

    private OnButtonClickListener onButtonClickListener;

    public void setOnButtonClickListener(OnButtonClickListener listener)
    {
        onButtonClickListener = listener;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        final EditText editText = new EditText(getActivity());

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        AlertDialog dialog = builder.setTitle("Add Todo")
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        if (onButtonClickListener != null)
                        {
                            onButtonClickListener.onSure(editText.getText().toString());
                        }
                    }
                })
                .setNegativeButton("取消", null)
                .create();

        return dialog;
    }
}
