package com.bridgelabz.com.appscreen;

import android.content.Intent;

import android.os.Bundle;

import android.database.Cursor;
import android.os.Bundle;

import android.provider.ContactsContract;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bridgelabz.com.appscreen.Model.Registration_Model;

import java.util.Set;

/**
 * Created by bridgelabz3 on 17/2/16.
 */
public class SetName extends AppCompatActivity
{
    EditText name;
    TextView message3;
    String msg3,nm;
    Button next;
    public Toolbar toolbar;
    Registration_Model model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_insert);

        msg3="Please provide your name and an optional profile photo";

        name= (EditText) findViewById(R.id.name);
        message3= (TextView) findViewById(R.id.Message3);
        message3.setText(""+msg3);

        next=(Button)findViewById(R.id.next);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nm=name.getText().toString();
                String data=getIntent().getStringExtra("phno");

                Registration_Model rm = new Registration_Model(SetName.this);
                boolean res=rm.SearchData(data);

                if (res)
                {
                    Toast.makeText(SetName.this,"Mobile number already registered",Toast.LENGTH_LONG).show();
                }
                else
                {
                    boolean isInserted=rm.insertData(nm, data);

                    if(isInserted)
                        Toast.makeText(SetName.this,"Data inserted successfuly",Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(SetName.this,"Data not inserted", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), Login.class));
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        next = (Button) findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}

