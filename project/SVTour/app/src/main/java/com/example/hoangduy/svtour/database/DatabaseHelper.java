package com.example.hoangduy.svtour.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.hoangduy.svtour.model.Tour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoangDuy on 5/8/2018.
 */

public class DatabaseHelper{

    // DATABASE ATTACK ./assets/SVTour.sqlite

    public static final String DATABASE_NAME = "SVTour.sqlite";
    public static final String DATABASE_LOCATION = "/databases/";
    private Context context;
    private SQLiteDatabase database;


    public DatabaseHelper(Context context) {
        this.context = context;
        openDatabase();
    }

    // OPPEN DATABASE
    public SQLiteDatabase openDatabase() {
        File databasebFile = context.getDatabasePath(DATABASE_NAME);
        if (!databasebFile.exists()) {
            try {
                copyDatabase();
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(databasebFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    // COPPY DATABASE
    private void copyDatabase() throws IOException {
        try{
            InputStream inputStream = context.getAssets().open(DatabaseHelper.DATABASE_NAME);
            String outputStrean = context.getApplicationInfo().dataDir+DATABASE_LOCATION+DATABASE_NAME;

            File file = new File(context.getApplicationInfo().dataDir+DATABASE_LOCATION);
            if(!file.exists()){
                file.mkdir();
            }

            OutputStream outputStream = new FileOutputStream(outputStrean);

            byte [] buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff))>0)
            {
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }


    // ADD ANOTHER METHOD BELLOW

    // GET LIST TOUR
    public List<Tour> getListTour(){
        List<Tour> tourList = new ArrayList<>();
        database = context.openOrCreateDatabase(DATABASE_NAME,context.MODE_PRIVATE,null);
        Cursor cursor = database.rawQuery("select * from tours",null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            Tour tour = new Tour(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            tourList.add(tour);
            cursor.moveToNext();
        }
        cursor.close();
        database.close();
        return tourList;
    }

}
