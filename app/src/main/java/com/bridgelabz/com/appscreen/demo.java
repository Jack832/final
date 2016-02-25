package com.bridgelabz.com.appscreen;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bridgelabz.com.appscreen.Model.ContentModel;
import com.bridgelabz.com.appscreen.Model.Registration_Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by bridgelabz5 on 27/1/16.
 */
public class demo extends Activity {

    Toolbar toolbar;
    Toolbar t2;
    String ph_no;
    ContentModel cm;
    Bitmap bitmap;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.demo1);


        toolbar = (Toolbar) findViewById(R.id.app_bar);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .5));

        Bundle extras = getIntent().getExtras();
        if (extras == null) {
            return;
        }

       // int res = extras.getInt("Bitmap");
        String tit = extras.getString("Title");
//        ImageView imageView = (ImageView) findViewById(R.id.cropImg1);
        t2 = (Toolbar) findViewById(R.id.toolbar2);
       // imageView.setImageResource(res);
        t2.setTitle(tit);

        toolbar.inflateMenu(R.menu.cropimgmenu);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                int id = item.getItemId();
                if (id == R.id.calls) {

                    String posted_by =  ph_no;

                    String uri = "tel:" + posted_by.trim();
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse(uri));
                    startActivity(intent);
                }
                if (id == R.id.chats) {

                    PackageManager pm = getPackageManager();

                    try {

                        Intent waIntent = new Intent(Intent.ACTION_SEND);
                        waIntent.setType("text/plain");
                        String text = "YOUR TEXT HERE";

                        PackageInfo info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                        //Check if package exists or not. If not then code
                        //in catch block will be called
                        waIntent.setPackage("com.whatsapp");

                        waIntent.putExtra(Intent.EXTRA_TEXT, text);
                        startActivity(Intent.createChooser(waIntent, "Share with"));

                    } catch (PackageManager.NameNotFoundException e) {
                        Toast.makeText(demo.this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                                .show();
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(demo.this);
                        alertDialog.setTitle("ALERT");
                        alertDialog.setMessage("Do You Want To Continue With SMS?");

                        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {

                                Temp();
                                // Write your code here to invoke YES event
                                Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                            }
                        });

                        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Write your code here to invoke NO event
                                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        });

                        // Showing Alert Message
                        alertDialog.show();
                    }

                }

                if(id == R.id.infodetails)
                {
                    Intent intent = new Intent(demo.this,Contactinfo.class);
                    startActivity(intent);
                }
                return true;
            }

            public void Temp() {

                Log.d("In SMS", "In SMS");
                String phoneNumber = ph_no;
                String smsBody = "This is an SMS!";

                Intent smsIntent = new Intent(Intent.ACTION_VIEW);
                // Invokes only SMS/MMS clients
                smsIntent.setType("vnd.android-dir/mms-sms");
                // Specify the Phone Number
                smsIntent.putExtra("address", phoneNumber);
                // Specify the Message
                smsIntent.putExtra("sms_body", smsBody);

                // Shoot!
                startActivity(smsIntent);
            }
        });


        //DATABASE OPERATIONS

        Cursor cursor = new Registration_Model(this).getMobileNumber();
        if(cursor.moveToFirst()) {
            ph_no = cursor.getString(0);
            Toast.makeText(demo.this, ph_no, Toast.LENGTH_SHORT).show();
        }


        cm= new ContentModel(demo.this);
        String urlofimg="http://techclones.com/wp-content/uploads/2013/05/Blue1.png";

        boolean insert=cm.insertimage(urlofimg);
        if(insert){
            Toast.makeText(demo.this, "inserted", Toast.LENGTH_SHORT).show();
            String st = cm.getimageurl();
            new LoadImg().execute(st);

        }
        else {
            Toast.makeText(demo.this, "not inserted ", Toast.LENGTH_SHORT).show();
        }


        //
    }

    public class LoadImg extends AsyncTask<String,String,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(demo.this);
            progressDialog.setMessage("DOWNLOADING");
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try{
                 bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (bitmap != null){
                ImageView imageView = (ImageView) findViewById(R.id.cropImg1);
                imageView.setImageBitmap(bitmap);
                progressDialog.dismiss();
            }else {
                progressDialog.dismiss();
                Toast.makeText(demo.this, "UNABLE TO DWNLD IMG", Toast.LENGTH_SHORT).show();
            }
        }
    }
}