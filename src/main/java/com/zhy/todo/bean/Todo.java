package com.zhy.todo.bean;

import org.litepal.crud.DataSupport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhy on 16/8/16.
 */
public class Todo extends DataSupport
{
    public static final int TYPE_UNDO = 1;
    public static final int TYPE_DONE = 0;
    private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int id;
    private String dateTime = df.format(new Date());

    public Todo()
    {

    }

    public Todo(String content)
    {
        this.content = content;
    }

    private String content;
    private int type = TYPE_UNDO;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(String dateTime)
    {
        this.dateTime = dateTime;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public int getType()
    {
        return type;
    }

    public void setType(int type)
    {
        this.type = type;
    }
}
