package com.bridgelabz.com.appscreen.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by bridgelabz4 on 23/2/16.
 */
public class ContentModel extends Model {
    public static final String TABLE_NAME="Content_Table";
    public static final String COL_2="url";
//    public static final String COL_3="part";

    public ContentModel(Context context) {
        super(context);
        Log.d("created", "table1");

    }
        @Override
        public void onCreate (SQLiteDatabase db){
            super.onCreate(db);
            db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,url TEXT)");
            Log.d("created", "table2");
        }

        @Override
        public void onUpgrade (SQLiteDatabase db,int oldVersion, int newVersion){
            super.onUpgrade(db, oldVersion, newVersion);
            onCreate(db);
        }

    public boolean insertimage(String urlimage){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2, urlimage);
//        contentValues.put(COL_3,text1);
        long result=db.insert(TABLE_NAME,null,contentValues);
//         return  result != -1;
        if(result == -1){
            return  false;
            }
            else{
            return true;
        }

    }
    public  String getimageurl(){
        String str="";
     SQLiteDatabase db =this.getReadableDatabase();
        Cursor r=db.rawQuery(" SELECT " + COL_2 + " FROM " + TABLE_NAME + " WHERE ID = 1 ", null);
           if (r.moveToFirst()) {
                str = r.getString(r.getColumnIndex("url"));
           }
               return str;

    }

}
