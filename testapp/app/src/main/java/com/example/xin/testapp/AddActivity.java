package com.example.xin.testapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * This class is the AddActivity class of the project. In this class,
 * user can add a new record
 * and intent will send the ArrayList back to MainActivity for save
 *
 * @author Xin Huang
 * @version  1.0
 * @since 1.0
 */

/**
 * The type AddActivity
 */
public class AddActivity extends AppCompatActivity {

    private EditText nameText;
    private EditText dateText;
    private EditText neckText;
    private EditText bustText;
    private EditText chestText;
    private EditText waistText;
    private EditText hipText;
    private EditText inseamText;
    private EditText commentText;
    private int checkDate = 0;

    /**
     * Called when the activity is first created
     * Set a calendar for user to pick when is EditText for date is called
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        final Calendar c = Calendar.getInstance();

        nameText = (EditText) findViewById(R.id.editname);
        dateText = (EditText) findViewById(R.id.editdate);
        neckText = (EditText) findViewById(R.id.editneck);
        bustText = (EditText) findViewById(R.id.editbust);
        chestText = (EditText) findViewById(R.id.editchest);
        waistText = (EditText) findViewById(R.id.editwaist);
        hipText = (EditText) findViewById(R.id.edithip);
        inseamText = (EditText) findViewById(R.id.editinseam);
        commentText = (EditText) findViewById(R.id.editcomment);

        //set the calendar
        dateText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener(){
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
                    {
                        Calendar c = Calendar.getInstance();
                        c.set(year, monthOfYear, dayOfMonth);
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        dateText.setText(dateFormat.format(c.getTime()));
                    }
                }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });

        /**
         * when the saveButton is clicked
         * get the user input, check if the name is empty and also the format for date
         * change the float number's format
         * If all OK, save it to ArrayList and send it back to MainActivity
         */
        Button saveButton = (Button) findViewById(R.id.save);
        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

                String name_text = nameText.getText().toString();
                String date_text = dateText.getText().toString();
                String neck_text = ifEmpty(neckText);
                String bust_text = ifEmpty(bustText);
                String chest_text = ifEmpty(chestText);
                String waist_text = ifEmpty(waistText);
                String hip_text = ifEmpty(hipText);
                String inseam_text = ifEmpty(inseamText);
                String comment_text = commentText.getText().toString();

                Person info = new Person(name_text, date_text, neck_text, bust_text, chest_text, waist_text, hip_text, inseam_text, comment_text);

                if (name_text.isEmpty()) {
                    Toast.makeText(AddActivity.this, "Name Field is empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    if (! date_text.isEmpty()) {
                        String[] date = date_text.split("-|\\.|/");
                        if (date.length < 3) {
                            Toast.makeText(AddActivity.this, "Date format incorrect! (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                            checkDate = 1;
                        } else {
                            String year = date[0];
                            String month = (date[1]);
                            String day = (date[2]);

                            if (year.length() != 4) {
                                Toast.makeText(AddActivity.this, "Date format incorrect! (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                                checkDate = 1;
                            } else if (Integer.parseInt(month) > 12 || Integer.parseInt(month) < 1) {
                                Toast.makeText(AddActivity.this, "Date format incorrect! (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                                checkDate = 1;
                            } else if (Integer.parseInt(day) > 31 || Integer.parseInt(month) < 1) {
                                Toast.makeText(AddActivity.this, "Date format incorrect! (yyyy-mm-dd)", Toast.LENGTH_SHORT).show();
                                checkDate = 1;
                            }
                        }
                    }

                    if (date_text.isEmpty() || checkDate == 0) {
                        Intent passIntent = new Intent();
                        passIntent.setClass(AddActivity.this, MainActivity.class);
                        ArrayList<Person> personList = new ArrayList<Person>();
                        personList.add(info);
                        Bundle bundleObject = new Bundle();
                        bundleObject.putSerializable("key", personList);
                        passIntent.putExtras(bundleObject);
                        startActivity(passIntent);
                        finish();
                    }
                }
            }
        });

    }

    /**
     * Called when the activity is onStart
     */
    @Override
    protected void onStart() {
        super.onStart();
        }

    /**
     * Check the user input for numeric part
     * if empty set it to 0
     * if not empty only leave one number after decimal point
     */
    protected String ifEmpty(EditText editText){
        String text;
        if(editText.getText().toString().isEmpty()){
            text = "0";
        }
        else {
            DecimalFormat df = new DecimalFormat("0.0");
            df.setRoundingMode(RoundingMode.HALF_UP);
            float floatText = Float.valueOf(editText.getText().toString());
            text = df.format(floatText);
        }
        return text;
    }

}
