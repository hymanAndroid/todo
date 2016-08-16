package com.zhy.todo;

import org.litepal.LitePalApplication;

/**
 * Created by zhy on 16/8/16.
 */
public class TodoApplication extends LitePalApplication
{
    @Override
    public void onCreate()
    {
        super.onCreate();
        LitePalApplication.initialize(this);
    }
}
