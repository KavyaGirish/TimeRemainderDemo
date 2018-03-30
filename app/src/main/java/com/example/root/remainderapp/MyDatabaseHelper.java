package com.example.root.remainderapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 30/3/18.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper
{
    private static final String database_name="remdata.db";
    private static final String table_name="remtable";
    private static final int database_version=1;
    private String column_date="rdate",column_title="rtitle", column_time="rtime", column_remainder="remainder";
    private String create_table_statement="create table "+table_name+"("+column_date+" text primary key, "+column_title+" text not null, "+column_time+" text not null, "+column_remainder+" text not null);";

    public MyDatabaseHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_table_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //change in DB
        sqLiteDatabase.execSQL("drop table if exists "+table_name+";");
        onCreate(sqLiteDatabase);
    }
}
