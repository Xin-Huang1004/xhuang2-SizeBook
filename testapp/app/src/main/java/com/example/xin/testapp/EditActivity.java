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
import java.util.Calendar;

public class EditActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        final Calendar c = Calendar.getInstance();

        nameText = (EditText) findViewById(R.id.reEditName);
        dateText = (EditText) findViewById(R.id.reEditDate);
        neckText = (EditText) findViewById(R.id.reEditNeck);
        bustText = (EditText) findViewById(R.id.reEditBust);
        chestText = (EditText) findViewById(R.id.reEditChest);
        waistText = (EditText) findViewById(R.id.reEditWaist);
        hipText = (EditText) findViewById(R.id.reEdithip);
        inseamText = (EditText) findViewById(R.id.reEditInseam);
        commentText = (EditText) findViewById(R.id.reEditComment);

        dateText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                DatePickerDialog dialog = new DatePickerDialog(EditActivity.this, new DatePickerDialog.OnDateSetListener(){
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

        Button saveEditButton = (Button) findViewById(R.id.saveEdit);
        saveEditButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                trySave();
            }

        });


    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        Toast.makeText(EditActivity.this, "Edit!", Toast.LENGTH_LONG).show();
        Intent intent = getIntent();
        String editInformation = intent.getExtras().getString("editInfo");
        String[] peopleinfo = editInformation.split("\n|:");

        String name = peopleinfo[1];
        String bust = peopleinfo[3];
        String chest = peopleinfo[5];
        String waist = peopleinfo[7];
        String inseam = peopleinfo[9];
        String neck = peopleinfo[11];
        String hip = peopleinfo[13];
        String date = peopleinfo[15];
        String comment = peopleinfo[17];

        EditText EditName = (EditText) findViewById(R.id.reEditName);
        EditName.setText(name);
        EditText EditDate = (EditText) findViewById(R.id.reEditDate);
        EditDate.setText(date);
        EditText EditNeck = (EditText) findViewById(R.id.reEditNeck);
        EditNeck.setText(neck);
        EditText EditBust = (EditText) findViewById(R.id.reEditBust);
        EditBust.setText(bust);
        EditText EditChest = (EditText) findViewById(R.id.reEditChest);
        EditChest.setText(chest);
        EditText EditWaist = (EditText) findViewById(R.id.reEditWaist);
        EditWaist.setText(waist);
        EditText EditHip = (EditText) findViewById(R.id.reEdithip);
        EditHip.setText(hip);
        EditText EditInseam = (EditText) findViewById(R.id.reEditInseam);
        EditInseam.setText(inseam);
        EditText EditComment = (EditText) findViewById(R.id.reEditComment);
        EditComment.setText(comment);

    }


    private void trySave() {
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
            if (name_text.isEmpty()) {
                Toast.makeText(EditActivity.this, "Name Field is empty", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(EditActivity.this, "Edit success！", Toast.LENGTH_SHORT).show();
                Intent intentPos = getIntent();
                int position = intentPos.getExtras().getInt("editPosition");
                    Intent intent = new Intent(EditActivity.this, DetailActivity.class);
                    intent.putExtra("sendName", name_text);
                    intent.putExtra("sendDate", date_text);
                    intent.putExtra("sendNeck", neck_text);
                    intent.putExtra("sendBust", bust_text);
                    intent.putExtra("sendChest", chest_text);
                    intent.putExtra("sendWaist", waist_text);
                    intent.putExtra("sendHip", hip_text);
                    intent.putExtra("sendInseam", inseam_text);
                    intent.putExtra("sendComment", comment_text);
                    intent.putExtra("sendPT", position);
                    startActivity(intent);
                    finish();
                }
        }




    protected String ifEmpty(EditText editText){
        if(editText.getText().toString().isEmpty()){
            String text = "";
            return text;
        }
        else {
            DecimalFormat df = new DecimalFormat("0.0");
            df.setRoundingMode(RoundingMode.HALF_UP);
            float floatText = Float.valueOf(editText.getText().toString());
            System.out.println(floatText);
            String text = df.format(floatText);
            return text;
        }
    }
}
