package com.bridgelabz.com.appscreen.Controller;

import android.support.v7.app.AppCompatActivity;


/**
 * Created by bridgelabz3 on 1/2/16.
 */
public class Registration_Controller extends AppCompatActivity {

    static String num;
    static boolean flag=false;

    public static boolean verifyNumber(String phoneNo)
    {
        num=phoneNo;
        if(phoneNo.matches("^[7-9][0-9]{9}$"))
        {
            flag=true;
        }
        if(flag ==true)
            return true;
        else {
            return false;
        }
    }
    public void Temp()
    {

//        Log.e("Reg model","Reg model");

        //Registration_Model rm=new Registration_Model(num);
    }
}