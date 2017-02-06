package com.example.xin.testapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {
    private static final String FILENAME = "xhuang2-SizeBook.sav";
    public ListView persons;
    public ArrayList<Person> personList;
    public ArrayAdapter<Person> adapter;
    public ArrayList<Person> classObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        persons = (ListView) findViewById(R.id.list_view);

        Button addButton = (Button) findViewById(R.id.Add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Add a new Person", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });
    }

        @SuppressWarnings("unchecked")
        protected void onStart() {
            super.onStart();
            loadFromFile();

            try {
                setResult(RESULT_OK);
                // Get the Bundle Object
                Bundle bundleObject = this.getIntent().getExtras();
                // Get ArrayList Bundle
                classObject = (ArrayList<Person>) bundleObject.getSerializable("key");
                //Retrieve Objects from Bundle
                for(int index = 0; index < classObject.size(); index++) {
                    personList.add(classObject.get(index));
                }

            } catch (Exception e) {
                }


            String size = "You have " + personList.size() + " records";
            TextView sizeView = (TextView) findViewById(R.id.size);
            sizeView.setText(size);

            adapter = new ArrayAdapter<Person>(this,
                    R.layout.list_item, personList);
            persons.setAdapter(adapter);

            persons.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                        intent.putExtra("pos", position);
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("sendPersonList",personList);
                        intent.putExtras(bundleObject);
                        startActivity(intent);
                    }
                });
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



}

