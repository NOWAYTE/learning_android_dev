package com.example.booklibrary;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class AddActivity extends AppCompatActivity {


      TextInputEditText name_input, date_input, parking_input, length_input,
            location_input, difficulty_input,
            desciption_input;
      EditText observation_input, comments_input, time_input;

    Button add_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        final TextInputLayout name_input = (TextInputLayout) findViewById(R.id.name_input);
        final TextInputLayout date_input = (TextInputLayout)findViewById(R.id.date_input);
        final TextInputLayout parking_input = (TextInputLayout)findViewById(R.id.parking_input);
        final TextInputLayout length_input = (TextInputLayout) findViewById(R.id.length_input);
        final TextInputLayout location_input = (TextInputLayout) findViewById(R.id.location_input);
        final TextInputLayout difficulty_input = (TextInputLayout) findViewById(R.id.difficulty_input);
        final TextInputLayout desciption_input = (TextInputLayout) findViewById(R.id.desciption_input);
        observation_input = findViewById(R.id.observation_input);
        time_input = findViewById(R.id.time_input);
        comments_input = findViewById(R.id.comments_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(AddActivity.this);
                myDB.addBook(name_input.getEditText().getText().toString().trim(),
                        date_input.getEditText().getText().toString().trim(),
                        parking_input.getEditText().getText().toString().trim(),
                        Integer.valueOf(length_input.getEditText().getText().toString().trim()),
                        location_input.getEditText().getText().toString().trim(),
                        difficulty_input.getEditText().getText().toString().trim(),
                        desciption_input.getEditText().getText().toString().trim(),
                        observation_input.getText().toString().trim(),
                        time_input.getText().toString().trim(),
                        comments_input.getText().toString().trim());
            }
        });


    }
}