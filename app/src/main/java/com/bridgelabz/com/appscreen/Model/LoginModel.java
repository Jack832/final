package com.bridgelabz.com.appscreen.Model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


/**
 * Created by bridgelabz3 on 16/2/16.
 */
public class LoginModel extends Model
{
    public static final String TABLE_NAME="Login_Table";
    public static final String COL_2="UserName";
    public static final String COL_3="Password";

    public LoginModel(Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT,UserName TEXT, Password TEXT)");
        Log.e("Login table","Login table");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String Username,String Password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,Username);
        contentValues.put(COL_3, Password);
        long res= db.insert(TABLE_NAME,null,contentValues);
        return res != -1;
    }
    public boolean SearchData(String UserNm)
    {
        SQLiteDatabase db=getWritableDatabase();
        boolean flag=false;
        Cursor res=db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE "
                + COL_2 + " = " + "'"+UserNm+"'",null);

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
}