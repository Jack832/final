package com.bridgelabz.com.appscreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bridgelabz.com.appscreen.Model.LoginModel;


public class MainActivity extends AppCompatActivity {

    Button login,Registration;
    EditText name,password;
    String nm,passwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        login = (Button) findViewById(R.id.Login);
        Registration=(Button)findViewById(R.id.Registration);

        name= (EditText) findViewById(R.id.UserName);
        password= (EditText) findViewById(R.id.Password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nm=name.getText().toString();
                passwd=password.getText().toString();

                if(name.getText().toString().equals(""))
                {
                    name.setError(Html.fromHtml("<body bgcolor='#2196F3'><font color='black'>Please enter name</font></body>"));
                }
                else if(password.getText().toString().equals(""))
                {
                    password.setError("Please enter password");
                }
                else
                {
                    LoginModel model=new LoginModel(MainActivity.this);
                    boolean res=model.SearchData(nm);

                    if (res)
                    {
                        Toast.makeText(MainActivity.this,"User name already present",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        boolean isInserted=model.insertData(nm,passwd);
                        if(isInserted)
                            Toast.makeText(MainActivity.this,"Data inserted successfuly",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted", Toast.LENGTH_LONG).show();

                        startActivity(new Intent(getApplicationContext(), Login.class));
                        name.setText("");
                        password.setText("");
                    }
                }

            }
        });
        Registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), com.bridgelabz.com.appscreen.Registration.class));
            }
        });
    }
}