package com.example.booklibrary;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.w3c.dom.Text;

public class UpdateActivity extends AppCompatActivity {

    TextInputEditText name_input, date_input, parking_input, length_input,
            location_input, difficulty_input,
            desciption_input;
    EditText observation_input,
            time_input, comments_input ;

    Button update_button, delete_button;

    String id, name, date, location, difficulty, parking, length, desciption
            , observation, time, comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        final TextInputLayout name_input = (TextInputLayout) findViewById(R.id.name_input2);
        final TextInputLayout date_input = (TextInputLayout) findViewById(R.id.date_input2);
        final TextInputLayout parking_input = (TextInputLayout)findViewById(R.id.hike_parking2);
        final TextInputLayout length_input = (TextInputLayout)findViewById(R.id.hike_length2);
        final TextInputLayout location_input = (TextInputLayout)findViewById(R.id.location_input2);
        final TextInputLayout difficulty_input = (TextInputLayout)findViewById(R.id.difficulty_input2);
        final TextInputLayout desciption_input = (TextInputLayout)findViewById(R.id.desciption_input2);
        observation_input = findViewById(R.id.observation_input2);
        time_input = findViewById(R.id.time_input2);
        comments_input = findViewById(R.id.comments_input2);
        update_button = findViewById(R.id.update_button);
        getIntentData();
        update_button.setOnClickListener(view -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);
            myDB.updateData(id, name, date, parking, length, location,difficulty, desciption,
                    observation, time, comments);

            ActionBar ab = getSupportActionBar();
            if(ab != null){
                ab.setTitle(name);
            }

        });

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();

            }
        });



    }

    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name")
                && getIntent().hasExtra("date")
                && getIntent().hasExtra("parking")
                && getIntent().hasExtra("length")
                && getIntent().hasExtra("location")
                && getIntent().hasExtra("difficulty")
                && getIntent().hasExtra("desciption")
                && getIntent().hasExtra("observation")
                && getIntent().hasExtra("time")
                && getIntent().hasExtra("comments")){

            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            date = getIntent().getStringExtra("date");
            parking = getIntent().getStringExtra("parking");
            length = getIntent().getStringExtra("length");
            location = getIntent().getStringExtra("location");
            difficulty = getIntent().getStringExtra("difficulty");
            desciption = getIntent().getStringExtra("desciption");
            observation = getIntent().getStringExtra("observation");
            time = getIntent().getStringExtra("time");
            comments = getIntent().getStringExtra("comments");

            name_input.setText(name);
            date_input.setText(date);
            parking_input.setText(parking);
            length_input.setText(length);
            location_input.setText(location);
            difficulty_input.setText(difficulty);
            desciption_input.setText(desciption);
            observation_input.setText(observation);
            time_input.setText(time);
            comments_input.setText(comments);
            Log.d("success", name);
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete" + name + "?");
        builder.setMessage("are you sure " + name + " ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateActivity.this);

                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

}