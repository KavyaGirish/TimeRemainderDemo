package com.example.root.remainderapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ViewActivity extends AppCompatActivity
{
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase mDatabase;
    Cursor cursor;
    TextView date,title,remainder,time;
    Button button;
    private static final String table_name="remtable";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        date=findViewById(R.id.date);
        title=findViewById(R.id.title);
        remainder=findViewById(R.id.rmdr);
        time=findViewById(R.id.time2);
        button=findViewById(R.id.bk);

        //for BACK option
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b=new Intent(ViewActivity.this,AppointmentsActivity.class);
                startActivity(b);
            }
        });

        myDatabaseHelper=new MyDatabaseHelper(this);
        mDatabase=myDatabaseHelper.getReadableDatabase();

        //to get the date in selected item
        Intent v=getIntent();
        String s=v.getStringExtra("data7");
        startActivity(v);

        //to view remainder data from DB
        cursor=mDatabase.rawQuery("select * from "+table_name+" where column_date="+s+";",null);
        cursor.moveToFirst();
        date.setText(cursor.getString(1));
        title.setText(cursor.getString(2));
        time.setText(cursor.getString(3));
        remainder.setText(cursor.getString(4));

    }
}
