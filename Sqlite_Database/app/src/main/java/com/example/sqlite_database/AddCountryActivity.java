package com.example.sqlite_database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddCountryActivity extends AppCompatActivity implements View.OnClickListener {


    // widgets:
    private Button addTodoBtn;
    private EditText subjectEditText;
    private EditText descEditText;
    private DBManager dbManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setTitle("Add Record");
        setContentView(R.layout.activity_add_country);

        subjectEditText = findViewById(R.id.subject_edit);
        descEditText = findViewById(R.id.description_edittext);
        addTodoBtn = findViewById(R.id.add_record);

        dbManager = new DBManager(this);
        dbManager.open();
        addTodoBtn.setOnClickListener(this);
    }


    public  void onClick(View v){
        switch (v.getId()){
            case R.id.add_record:
                final String name = subjectEditText.getText().toString();
                final String desc = descEditText.getText().toString();

                Log.d("AddCountryActivity", "Adding record: name=" + name + ", desc=" + desc);
                dbManager.insert(name, desc);
//                dbManager.insert(name, desc);

                Intent main = new Intent(AddCountryActivity.this,
                        MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(main);
                break;
        }
    }
}