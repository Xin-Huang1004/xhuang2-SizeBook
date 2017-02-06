package com.example.xin.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private static final String FILENAME = "xhuang2-SizeBook.sav";
    private ArrayList<Person> personList;
    private int position;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadFromFile();
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        position = intent.getExtras().getInt("pos");

        Button deleteButton = (Button) findViewById(R.id.deletebutton);

        deleteButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                setResult(RESULT_OK);
                personList.remove(position);
                saveInFile();
                finish();
            }
        });

        Button editButton =(Button) findViewById(R.id.editbutton);
        editButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(DetailActivity.this, EditActivity.class);
                String infomation = (personList.get(position)).toString();
                intent.putExtra("editInfo", infomation);
                intent.putExtra("editPosition",position);
                startActivity(intent);
            }
        });
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        String infoma = (personList.get(position)).toString();
        TextView myTextView = (TextView) findViewById(R.id.DetialView);//获取textview
        myTextView.setText(infoma);

        Intent intentinfo = getIntent();
        int getPosition = intentinfo.getExtras().getInt("sendPT");
        String name = intentinfo.getExtras().getString("sendName");
        String date = intentinfo.getExtras().getString("sendDate");
        String neck = intentinfo.getExtras().getString("sendNeck");
        String bust = intentinfo.getExtras().getString("sendBust");
        String chest = intentinfo.getExtras().getString("sendChest");
        String waist = intentinfo.getExtras().getString("sendWaist");
        String hip = intentinfo.getExtras().getString("sendHip");
        String inseam = intentinfo.getExtras().getString("sendInseam");
        String comment = intentinfo.getExtras().getString("sendComment");

        if(name != null) {
            Person info = new Person(name, date, neck, bust, chest, waist, hip, inseam, comment);
            personList.set(getPosition,info);
            Intent backToMain = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(backToMain);
        }
        saveInFile();
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Taken from https://stackoverflow.com/question/12384064/gson-convert-from-json-into java
            // 2017 01-26 17:53:59
            personList = gson.fromJson(in, new TypeToken<ArrayList<Person>>(){}.getType());

            fis.close();

        } catch (FileNotFoundException e) {
            personList = new ArrayList<Person>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(personList, out);
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
    @Override
    public void onBackPressed() {
        Intent backToMain = new Intent(DetailActivity.this, MainActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
        startActivity(backToMain);
    }
    }

