package com.bridgelabz.com.appscreen;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.bridgelabz.com.appscreen.Model.ContentModel;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bridgelabz4 on 25/1/16.
 */
public class Contactinfo extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout=null;
    private CoordinatorLayout coordinatorLayout=null;
    ImageView imageView;
    Toolbar toolbar4;
    RecyclerView horizontal, horizontal2, horizontal3;
    ViewAdapter adapterRec1;
    Adapter2 adapter2;
    Adapter3 adapter3;
    //collapse using url
    Bitmap bitmap;
    ProgressDialog progress;
    //database
    ContentModel cm;
    String st;


    public static List<MyData> getdata() {
        List<MyData> data = new ArrayList<>();
        int icon[] = {R.drawable.doc2, R.drawable.home, R.drawable.doc1, R.drawable.doc2, R.drawable.home
                , R.drawable.doc1, R.drawable.doc2, R.drawable.home, R.drawable.doc1};

        for (int i = 0; i < icon.length ; i++) {
            MyData current = new MyData();
            current.mainIcon = icon[i];
            data.add(current);
        }
        return data;
    }

    public static List<MyData> getdataall() {
        List<MyData> data = new ArrayList<>();
        int icon[] = {R.drawable.doc1, R.drawable.doc2, R.drawable.doc1, R.drawable.doc2, R.drawable.doc1,
                R.drawable.doc2, R.drawable.doc1, R.drawable.doc2, R.drawable.doc1,};
        String title[] = {"document1", "document2", "document3", "document4", "document5", "document6",
                "document7", "document8", "document9"};
        for (int i = 0; i < icon.length && i < title.length; i++) {
            MyData current = new MyData();
            current.mainIcon = icon[i];
            current.mainTitle = title[i];
            data.add(current);
        }
        return data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lasttry);
        toolbar4 = (Toolbar) findViewById(R.id.toolbar4);
        setSupportActionBar(toolbar4);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        toolbar4.setBackgroundColor(Color.TRANSPARENT);




        // set collase image.

        imageView = (ImageView)findViewById(R.id.profile_id1);
        cm= new ContentModel(Contactinfo.this);
        String urlofimg="http://techclones.com/wp-content/uploads/2013/05/Blue1.png";
        boolean insert=cm.insertimage(urlofimg);
        if(insert){
            Toast.makeText(Contactinfo.this, "inserted", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(Contactinfo.this, "not inserted ", Toast.LENGTH_SHORT).show();
        }

        st=cm.getimageurl();
        Toast.makeText(Contactinfo.this, ""+st, Toast.LENGTH_SHORT).show();
        new Loadimage().execute(st);
        //imageView.setImageResource(R.drawable.earth);
        Toast.makeText(Contactinfo.this, "contactds" +
                "", Toast.LENGTH_SHORT).show();

       // toolbar4.setBackgroundResource(R.drawable.earth);

        collapsingToolbarLayout=(CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle("LINK IN");
        dynamic();
        toolbarText();

//        //call participant
//        String imagep="http://images.mystockphoto.com/files/premium/thumbs/161/national-flag-papua-new-guinea_16194985.jpg";
//        participantModel= new participantModel(Contactinfo.this);
//        boolean inserted=participantModel.insertparticipants(imagep, "jayesh", null, null);
//
//        if(inserted){
//            Toast.makeText(Contactinfo.this, "inserted parti", Toast.LENGTH_SHORT).show();
//        }
//        else {
//            Toast.makeText(Contactinfo.this, "not inserted parti", Toast.LENGTH_SHORT).show();
//        }






        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false);
        horizontal = (RecyclerView) findViewById(R.id.RV1);
        horizontal.setLayoutManager(linearLayoutManager);
        adapter2 = new Adapter2(getApplication(), getdata());
        horizontal.setAdapter(adapter2);

        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager
                (this, LinearLayoutManager.HORIZONTAL, false);
        horizontal2 = (RecyclerView) findViewById(R.id.RV2);
        horizontal2.setLayoutManager(linearLayoutManager1);
        adapter3 = new Adapter3(getApplication(), getdata());

        horizontal2.setAdapter(adapter3);

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager
                (this, LinearLayoutManager.VERTICAL, false);
        horizontal3 = (RecyclerView) findViewById(R.id.RV3);
        horizontal3.setLayoutManager(linearLayoutManager2);
        adapterRec1 = new ViewAdapter(getApplication(), getdataall());
        horizontal3.setAdapter(adapterRec1);
    }

    private void toolbarText() {
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.collapse);
        collapsingToolbarLayout.setExpandedTitleTextAppearance(R.style.expand);
    }


    private void dynamic() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.earth);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                collapsingToolbarLayout.setContentScrimColor(palette.getMutedColor(R.attr.colorPrimary));
                collapsingToolbarLayout.setStatusBarScrimColor(palette.getMutedColor(R.attr.colorPrimaryDark));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.card, menu);
        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView= (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.Searchicon));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.edit1){
            Toast.makeText(getApplication(),"hello edit click",Toast.LENGTH_SHORT).show();


        }
        if(id==R.id.Searchicon) {

            Toast.makeText(getApplication(), "hello edit click", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    private  class  Loadimage extends AsyncTask<String,String,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progress = new ProgressDialog(Contactinfo.this);
            progress.setMessage("downloasding");
            progress.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try{

//                URL url = new URL(st);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.setDoInput(true);
//                connection.connect();
//                InputStream input = connection.getInputStream();
//                bitmap = BitmapFactory.decodeStream(input);
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
            if(bitmap != null){
                imageView.setImageBitmap(bitmap);
                progress.dismiss();
            }else {
                progress.dismiss();
                Toast.makeText(Contactinfo.this, "image not found", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
