package com.example.root.remainderapp;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class EventCreateActivity extends AppCompatActivity
{
    String s1,s2,s3;
    String[] rem={"Event Time","5min ago","15min ago","30min ago","1hr ago"};
    Spinner sp;
    TextView e1;
    EditText e2;
    Button cancel,save,start;
    static final int DIALOG_ID1=0;
    int hr1,min1;

    private MyDatabaseHelper myDatabaseHelper;
    private SQLiteDatabase mDatabase;
    ContentValues contentValues;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_create);

        myDatabaseHelper=new MyDatabaseHelper(this);
        mDatabase=myDatabaseHelper.getReadableDatabase();
        contentValues=new ContentValues();

        /**For Cancel option**/
        cancel=findViewById(R.id.b3);
        cancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                backtoMA();
            }
        });

        /**Displaying Date**/
        Intent intent=getIntent();
        s1=intent.getStringExtra("data");
        e1=findViewById(R.id.e1);
        e1.setText(s1);

        /**Event Name**/
        e2=findViewById(R.id.e2);
        s2=e2.getText().toString();

        /**Setting Time**/
        start=findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                showDialog(DIALOG_ID1);
            }
        });

        /**Setting Remainder**/
        sp=findViewById(R.id.spinner);
        ArrayAdapter<String> ad=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,rem);
        sp.setAdapter(ad);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sp.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        /**For Saving option**/
        save=findViewById(R.id.b4);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveItems();
            }
        });
    }

    /**for TimePickerDialog**/
    protected Dialog onCreateDialog(int id)
    {
        if(id == DIALOG_ID1)
            return new TimePickerDialog(EventCreateActivity.this,k1, hr1, min1,true);
        return null;
    }
    protected TimePickerDialog.OnTimeSetListener k1 = new TimePickerDialog.OnTimeSetListener()
    {
        @Override
        public void onTimeSet(TimePicker timePicker, int i, int i1)
        {
            hr1= i;
            min1= i1;
            String str1= hr1+" : "+min1;
            start.setText(str1);
        }
    };


    /**move items to ListView**/
    public void saveItems()
    {
        Intent intent2=new Intent(this,AppointmentsActivity.class);
        s3="Date: "+e1.getText().toString()+", Title: "+e2.getText().toString()+", Time: "+start.getText().toString()+", Remainder: "+sp.getSelectedItem().toString();
        intent2.putExtra("data3",s3);
        startActivity(intent2);

        //to ViewActivity
        Intent intent4=new Intent(this,ViewActivity.class);
        intent4.putExtra("data7",e1.getText().toString());
        startActivity(intent4);

        //to UpdateActivity
        Intent intent5=new Intent(this,ViewActivity.class);
        intent4.putExtra("data8",e1.getText().toString());
        startActivity(intent5);

        /**add to DB table**/
        contentValues.put("column_date",e1.getText().toString());
        contentValues.put("column_title",e2.getText().toString());
        contentValues.put("column_time",start.getText().toString());
        contentValues.put("column_remainder",sp.getSelectedItem().toString());
        mDatabase.insert("remtable",null,contentValues);
    }

    /**for BACK option**/
    public  void backtoMA()
    {
        Intent intent1=new Intent(this,MainActivity.class);
        startActivity(intent1);
    }
}
