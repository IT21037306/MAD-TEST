package com.example.newapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class View_Plan extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;

    FDBHelper dbHelper;
    ArrayList<String> id,title , ex1 , ex2 ,ex3 , ex4;

    CustomAdapter customAdapter;
    TextView textView;

    Button bkHome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.actionColor)));

        recyclerView = findViewById(R.id.recycleView);
        floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(View_Plan.this , Create_Activity.class);
                startActivity(intent);
            }
        });

        textView = findViewById(R.id.textView);

        dbHelper = new FDBHelper(View_Plan.this);
        id = new ArrayList<>();
        title = new ArrayList<>();
        ex1 = new ArrayList<>();
        ex2 = new ArrayList<>();
        ex3 = new ArrayList<>();
        ex4 = new ArrayList<>();

        storeDataInArray();

        customAdapter = new CustomAdapter(View_Plan.this , id , title ,ex1 ,ex2 , ex3,ex4);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(View_Plan.this));

        bkHome = findViewById(R.id.hmBack);
        bkHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoMain();
            }
        });

    }

    void storeDataInArray(){
        Cursor cursor = dbHelper.readData();
        if(cursor.getCount() == 0){
            textView.setText("No Plans Were Found");
            Toast.makeText(this,"No Data",Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                id.add(cursor.getString(0));
                title.add(cursor.getString(1));
                ex1.add(cursor.getString(2));
                ex2.add(cursor.getString(3));
                ex3.add(cursor.getString(4));
                ex4.add(cursor.getString(5));
            }
        }
    }

    @Override
    public void onBackPressed() {
        gotoMain();
        super.onBackPressed();
    }

    public void gotoMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}