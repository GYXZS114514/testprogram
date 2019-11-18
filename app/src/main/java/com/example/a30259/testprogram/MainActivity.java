package com.example.a30259.testprogram;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.a30259.testprogram.R;

public class MainActivity extends AppCompatActivity {
private String newId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button select = (Button)findViewById(R.id.select);
        Button update = (Button)findViewById(R.id.update);
        Button delete = (Button)findViewById(R.id.delete);
        Button add = (Button)findViewById(R.id.add);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.a30259.word_note.provider/word");
                Cursor cursor = getContentResolver().query(uri,null,null,null,null);
                if(cursor!=null){
                    while(cursor.moveToNext()){
                        String yw = cursor.getString(cursor.getColumnIndex("yw"));
                        String zw = cursor.getString(cursor.getColumnIndex("zw"));
                        String lj = cursor.getString(cursor.getColumnIndex("lj"));
                        Log.d("MainActivity","euclish is: " + yw);
                        Log.d("MainActivity","chinesse is: " + zw);
                        Log.d("MainActivity","sentence is: " + lj);
                    }
                    cursor.close();
                }
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("content://com.example.a30259.word_note.provider/word");
                ContentValues values = new ContentValues();
                values.put("yw","ContentProvider");
                values.put("zw","内容提供器");
                values.put("lj","2019/11/18");
                Uri newUri = getContentResolver().insert(uri,values);
                newId = newUri.getPathSegments().get(1);
            }
        });
    }
}
