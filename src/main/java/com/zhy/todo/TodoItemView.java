package com.zhy.todo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by zhy on 16/8/16.
 */
public class TodoItemView extends RelativeLayout
{

    public TodoItemView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
        View line = findViewById(R.id.id_view_line);
        line.layout(line.getLeft(), line.getTop(), line.getRight(), getMeasuredHeight());

    }


}
