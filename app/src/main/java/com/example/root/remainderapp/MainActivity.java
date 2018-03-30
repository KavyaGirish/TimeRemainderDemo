package com.example.root.remainderapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    Button b1,b2;
    int yr, mon, dy;
    static final int DIALOG_ID=0;
    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase mDatabase;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDatabaseHelper=new MyDatabaseHelper(this);
        mDatabase=myDatabaseHelper.getReadableDatabase();
        contentValues=new ContentValues();


        /**Calender to create an event**/
        final Calendar cal=Calendar.getInstance();
        yr=cal.get(Calendar.YEAR);
        mon=cal.get(Calendar.MONTH);
        dy=cal.get(Calendar.DAY_OF_MONTH);
        showDialogOnClick();

       /**Goto All Applications**/
        b2=findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showApp();
           }
       });
    }

    /**Shows Calender to pick a date**/
    public void showDialogOnClick()
    {
        b1=findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DIALOG_ID);
            }
        });
    }
    protected Dialog onCreateDialog(int id)
    {
        if(id==DIALOG_ID)
            return new DatePickerDialog(MainActivity.this,kDPL,yr,mon,dy);
        return null;
    }
    private DatePickerDialog.OnDateSetListener kDPL=new DatePickerDialog.OnDateSetListener()
    {
        public  void  onDateSet(DatePicker datePicker,int i,int i1,int i2)
        {
            yr=i;
            mon=i1+1;
            dy=i2;


            /**Toast.makeText(MainActivity.this,yr+"/"+mon+"/"+dy,Toast.LENGTH_SHORT).show();**/
            String s=dy+"/"+mon+"/"+yr;

            Intent intent2=new Intent(MainActivity.this,EventCreateActivity.class);
            intent2.putExtra("data",s);
            startActivity(intent2);
        }
    };

    /**onClick for All Applications**/
    public  void  showApp()
    {
        Intent intent1= new Intent(this,AppointmentsActivity.class);
        startActivity(intent1);
    }
}
