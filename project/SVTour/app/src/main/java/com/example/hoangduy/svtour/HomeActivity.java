package com.example.hoangduy.svtour;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hoangduy.svtour.adapter.ListTourAdapter;
import com.example.hoangduy.svtour.database.DatabaseHelper;
import com.example.hoangduy.svtour.model.Tour;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;

public class HomeActivity extends AppCompatActivity {

    private ListView listviewTour;
    private ListTourAdapter adapter;
    private List<Tour> TourList;
    private DatabaseHelper DatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseHelper = new DatabaseHelper(this);
        listviewTour = (ListView) findViewById(R.id.lvDulich);
        TourList = DatabaseHelper.getListTour();
        adapter = new ListTourAdapter(this, TourList);
        listviewTour.setAdapter(adapter);

    }
}
