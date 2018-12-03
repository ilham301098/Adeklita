package com.trydev.games.guesswhat;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseAccess {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;

    //Private Constructor to avoid object creation from outside classes

    private DatabaseAccess(Context context){
        this.openHelper = new DatabaseOpenHelper(context);
    }

    //return a singleton instance of Database Access
    public static DatabaseAccess getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    //Open Database Connection
    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if(database != null){
            this.database.close();
        }
    }

    public String[] getTitleBalita(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ArticleAdeklita WHERE Jenis='1' ORDER BY `ID` ASC", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        String[] mStringArray = new String[list.size()];
        mStringArray =list.toArray(mStringArray);

        for(int i = 0; i < mStringArray.length ; i++){
            Log.d("",(String)mStringArray[i]);
        }
        return mStringArray;
    }

    public String getDesc(String Title){
        String desc="";
        Cursor cursor = database.rawQuery("SELECT `Isi` FROM ArticleAdeklita WHERE Judul='"+Title+"'", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            desc=cursor.getString(0);
            cursor.moveToNext();
        }
        cursor.close();
        return desc;
    }

    public String[] getTitleOrtu(){
        List<String> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM ArticleAdeklita WHERE Jenis='2' ORDER BY `ID` ASC", null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            list.add(cursor.getString(1));
            cursor.moveToNext();
        }
        cursor.close();

        String[] mStringArray = new String[list.size()];
        mStringArray =list.toArray(mStringArray);

        for(int i = 0; i < mStringArray.length ; i++){
            Log.d("",(String)mStringArray[i]);
        }
        return mStringArray;
    }

}
