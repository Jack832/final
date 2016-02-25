package com.bridgelabz.com.appscreen;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bridgelabz3 on 2/2/16.
 */

public class IncomingSms extends BroadcastReceiver {

    // Get the object of SmsManager
    String str="";
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();           //---get the SMS message passed in---
            SmsMessage[] msgs = null;
            if (bundle != null) {
                try {
                    Log.e("Incoming SMS","in try");
                    String msg = intent.getStringExtra("pdus");
                    //Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

                    Pattern p = Pattern.compile("[0-9]{6,6}+");
                    Matcher m = p.matcher(msg);

                    if (m.find()) {
                        Registration registration=new Registration();
                        registration.getInstance().updateTheTextView(m.group());
                    }
                    return;
                }
                catch (Exception e)
                {
                }
                //---retrieve the SMS message received---
                // Retrieve the SMS.

//                Object[] pdus = (Object[]) bundle.get("pdus");
//                msgs = new SmsMessage[pdus.length];
//                for (int i = 0; i < msgs.length; i++) {
//                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
//                    // In case of a particular App / Service.
//                    str += " :";
//                    str += msgs[i].getMessageBody();
//                    str += "n";
//                }
//                // Display the SMS as Toast.
//                Pattern p = Pattern.compile("[0-9]{6,6}+");
//                Matcher m = p.matcher(str);
//
//                if (m.find()) {
//                    Registration_Controller registration=new Registration_Controller();
//                    registration.getInstance().updateTheTextView(m.group());
//                    Toast.makeText(context, m.group(), Toast.LENGTH_SHORT).show();
//                }
            }
        }
    }
}