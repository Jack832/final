package com.bridgelabz.com.appscreen.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by bridgelabz3 on 9/2/16.
 */


public class Registration_Model extends Model
{
    public static final String TABLE_NAME="Registration_Table";
    public static final String COL_2="Name";
    public static final String COL_3="MobileNo";

    public Registration_Model(Context context) {
       super(context);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT, MobileNo TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Name,String MobNo)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,Name);
        contentValues.put(COL_3, MobNo);
        long res= db.insert(TABLE_NAME,null,contentValues);
        if(res == -1)
            return false;
        else
            return true;
    }
    public boolean SearchData(String MobNo)
    {
        SQLiteDatabase db=getWritableDatabase();
        boolean flag=false;
        Cursor res=db.rawQuery(" SELECT * FROM "+ TABLE_NAME + " WHERE "
                + COL_3 + " = " + "'"+MobNo+"'",null);

        if(res.moveToFirst())
        {
            do {
                flag=true;
                break;
            }while (res.moveToNext());
        }
        res.close();
        return flag;
    }

    // getting all the mobile number
    public Cursor getMobileNumber()
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(" SELECT "+COL_3+" FROM "+TABLE_NAME+" WHERE ID = 1",null);
        return cursor;
    }
}