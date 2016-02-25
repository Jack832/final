package com.bridgelabz.com.appscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/**
 * Created by bridgelabz3 on 30/1/16.
 */
public class Welcome extends AppCompatActivity
{
    //ImageView welcome,mickey,shoppingList;
    Button continue1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

//        welcome=(ImageView)findViewById(R.id.welcome);
//        mickey=(ImageView)findViewById(R.id.mickey);
        //shoppingList=(ImageView)findViewById(R.id.shoppingList);

        continue1=(Button)findViewById(R.id.continue1);

        continue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));
            }
        });

    }
}
