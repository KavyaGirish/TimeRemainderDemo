package com.example.root.remainderapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class AppointmentsActivity extends AppCompatActivity
{
    ListView list;
    ArrayList<String> al;
    ArrayAdapter<String> ad;
    String s;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoinments);



        /**display saved events**/
        list=findViewById(R.id.list);
        al=new ArrayList<String>();
        ad=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,al);
        list.setAdapter(ad);

        Intent intent2=getIntent();
        s=intent2.getStringExtra("data3");
        al.add(s);
        ad.notifyDataSetChanged();

        /**select each event to view & update details**/
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentsActivity.this);
                builder.setTitle("Alert!");
                builder.setMessage("");
                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                            Intent v=new Intent(AppointmentsActivity.this,ViewActivity.class);
                            v.putExtra("data4",s);
                            startActivity(v);
                        }
                        });
                builder.setNegativeButton("Update", new DialogInterface.OnClickListener() {
                        @Override public void onClick(DialogInterface dialogInterface, int i) {
                            Intent u=new Intent(AppointmentsActivity.this,UpdateActivity.class);
                            startActivity(u);
                        }
                        });
                builder.create().show();
            }
        });

        /**for BACK option**/
        Button b=findViewById(R.id.b16);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                backtoMain();
            }
        });
    }

    /**onClick for BACK option**/
    public  void backtoMain()
    {
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);
    }
}
