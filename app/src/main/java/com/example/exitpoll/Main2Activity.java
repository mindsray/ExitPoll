package com.example.exitpoll;

import android.app.Activity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.exitpoll.adapter.PollAdapterlist;

import com.example.exitpoll.db.DatabaseHelper;
import com.example.exitpoll.model.Item_poll;


import java.util.ArrayList;
import java.util.List;

import static com.example.exitpoll.db.DatabaseHelper.COL_ID;
import static com.example.exitpoll.db.DatabaseHelper.COL_IMAGE;
import static com.example.exitpoll.db.DatabaseHelper.COL_NAME;
import static com.example.exitpoll.db.DatabaseHelper.COL_POINT;
import static com.example.exitpoll.db.DatabaseHelper.TABLE_NAME;

public class Main2Activity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Item_poll> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mHelper = new DatabaseHelper(Main2Activity.this);
        mDb = mHelper.getWritableDatabase();

        loadPhoneData();
        setupListView();

        Button deleteScore = findViewById(R.id.delete_button);
        deleteScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv0 = new ContentValues();
                cv0.put(COL_NAME, "no");
                cv0.put(COL_POINT,"0");
                cv0.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv0,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );

                ContentValues cv1 = new ContentValues();
                cv1.put(COL_NAME, "1");
                cv1.put(COL_POINT,"0");
                cv1.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv1,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );

                ContentValues cv2 = new ContentValues();
                cv2.put(COL_NAME, "2");
                cv2.put(COL_POINT,"0");
                cv2.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv2,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );

                ContentValues cv3 = new ContentValues();
                cv3.put(COL_NAME, "3");
                cv3.put(COL_POINT,"0");
                cv3.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv3,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );

                loadPhoneData();
                setupListView();
            }
        });

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            } else {
                finish();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPhoneData();
        setupListView();
    }

    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String point = c.getString(c.getColumnIndex(COL_POINT));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            Item_poll item = new Item_poll(id , name , point ,image);
            mItemList.add(item);
        }
        c.close();
    }
    private void setupListView() {
        PollAdapterlist adapter = new PollAdapterlist(
                Main2Activity.this,
                R.layout.activity_item_poll,
                mItemList
        );
        ListView lv = findViewById(R.id.result_list_view);
        lv.setAdapter(adapter);
    }
}
