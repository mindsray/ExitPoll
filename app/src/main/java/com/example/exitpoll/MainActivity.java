package com.example.exitpoll;


import android.Manifest;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
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

public class MainActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private SQLiteDatabase mDb;
    private List<Item_poll> mItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(MainActivity.this);
        mDb = mHelper.getWritableDatabase();

        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);
        Button button4 = findViewById(R.id.button4);
        Button out_button = findViewById(R.id.out_button);

        loadPhoneData();

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Item_poll item = mItemList.get(0);
                String textscore = item.point.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "no");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"vote_no.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(1)}
                );
                loadPhoneData();


            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Item_poll item = mItemList.get(1);
                String textscore = item.point.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "1");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"one.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(2)}
                );
                loadPhoneData();

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Item_poll item = mItemList.get(2);
                String textscore = item.point.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "2");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"two.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(3)}
                );
                loadPhoneData();

            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPhoneData();
                Item_poll item = mItemList.get(3);
                String textscore = item.point.toString();
                int score =Integer.valueOf(textscore)+1;
                String newscore = Integer.toString(score);
                ContentValues cv = new ContentValues();
                cv.put(COL_NAME, "3");
                cv.put(COL_POINT,newscore);
                cv.put(COL_IMAGE,"three.png");


                mDb.update(
                        TABLE_NAME,
                        cv,
                        COL_ID + " = ?",
                        new String[]{String.valueOf(4)}
                );
                loadPhoneData();

            }
        });



        out_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });



        //todo


    }
    private void loadPhoneData() {
        Cursor c = mDb.query(TABLE_NAME, null, null, null, null, null, null);

        mItemList = new ArrayList<>();
        while (c.moveToNext()) {
            long id = c.getLong(c.getColumnIndex(COL_ID));
            String name = c.getString(c.getColumnIndex(COL_NAME));
            String score = c.getString(c.getColumnIndex(COL_POINT));
            String image = c.getString(c.getColumnIndex(COL_IMAGE));

            Item_poll item = new Item_poll(id, name, score,image);
            mItemList.add(item);
        }
        c.close();
    }

}








