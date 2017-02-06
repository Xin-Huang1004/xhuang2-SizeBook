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

/**
 * This class is the main view class of the project. <br> In this class,
 * user can view the list of records and also can click add button to add a
 * new record
 * All files are in the form of "json" files that are stored in Emulator's
 * accessible from Android Device Monitor:
 * <pre>
 *     pre-formatted text: <br>
 *         File Explorer -> data -> data -> com.example.xin.testapp -> files -> xhuang2-SizeBook.sav.
 * </pre>
 * The file name is indicated in the &nbsp &nbsp &nbsp FILENAME constant.
 *
 * @author Xin Huang
 * @version  1.0
 * @since 1.0
 */


/**
 * The type Main activity.
 */
public class MainActivity extends AppCompatActivity {
    /**
     * The file that all the add's records are saved there.
     * The format of the file is JSON.
     * @see #loadFromFile()
     * @see #saveInFile()
     */

    private static final String FILENAME = "xhuang2-SizeBook.sav";
    public ListView persons;
    public ArrayList<Person> personList;
    public ArrayAdapter<Person> adapter;
    public ArrayList<Person> classObject;

    /**
     * Called when the activity is first created
     * @param savedInstanceState
     */
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

    /**
     * Called when the activity is started
     * try get the ArrayList which is sended from AddActivity(contains the information
     * for a new person)
     * Output the number of records in TextView
     * Set the adapter
     * When user click on the item in ListView, send the positon of the item to DetailActivity
     * Save changes by calling saveInFile
     */
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

    /**
     * Loads tweets from file.
     * @throws RuntimeException if IOException e happens
     * @exception FileNotFoundException if the file is not created
     */
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

    /**
     * Saves tweets in file in JSON format.
     * @throws FileNotFoundException if folder not exists
     */
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

