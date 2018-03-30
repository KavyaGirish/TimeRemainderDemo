package com.example.root.remainderapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class UpdateActivity extends AppCompatActivity
{
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase mDatabase;
    ContentValues contentValues;
    Cursor cursor;
    EditText date1,title1,time1;
    Spinner sp2;
    String[] rem={"Event Time","5min ago","15min ago","30min ago","1hr ago"};
    Button back,update;
    private static final String table_name="remtable";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        contentValues=new ContentValues();
        myDatabaseHelper=new MyDatabaseHelper(this);
        mDatabase=myDatabaseHelper.getReadableDatabase();

        date1=findViewById(R.id.date1);
        title1=findViewById(R.id.title1);
        sp2=findViewById(R.id.spinner2);
        time1=findViewById(R.id.time1);
        back=findViewById(R.id.back2);
        update=findViewById(R.id.update);

        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,rem);
        sp2.setAdapter(ad);

        //to get the date in selected item
        Intent v=getIntent();
        String s=v.getStringExtra("data8");
        startActivity(v);

        //to view remainder data from DB
        cursor=mDatabase.rawQuery("select * from "+table_name+" where column_date="+s+";",null);
        cursor.moveToFirst();
        date1.setText(cursor.getString(1));
        title1.setText(cursor.getString(2));
        time1.setText(cursor.getString(3));
        //sp2.setSelection(cursor.getString(4));


        //update spinner
        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp2.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //get updated data and stored in DB
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contentValues.put("column_date",date1.getText().toString());
                contentValues.put("column_title",title1.getText().toString());
                contentValues.put("column_date",time1.getText().toString());
                contentValues.put("column_date",sp2.getSelectedItem().toString());
                mDatabase.update("remtable",contentValues,null,null);
                //cursor=mDatabase.rawQuery();
                //cursor.moveToFirst();
            }
        });

        //for BACK option
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent b=new Intent(UpdateActivity.this,AppointmentsActivity.class);
                startActivity(b);
            }
        });
    }
}
