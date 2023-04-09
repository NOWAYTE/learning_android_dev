package com.example.booklibrary;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SearchEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FloatingActionButton add_button;
    ImageView empty_imageView;
    TextView no_data;
    private SearchView searchView;

    MyDatabaseHelper myDB;
    ArrayList<String> hike_id, hike_name, hike_date, hike_parking, hike_length
            ,hike_location, hike_difficulty, hike_desciption, hike_observation
            , hike_time, hike_comments ;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        searchView = findViewById(R.id.searchView);
//        searchView.clearFocus();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                filter(newText);
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return true;
//            }
//
//        });


        recyclerView = findViewById(R.id.recycleView);
        add_button = findViewById(R.id.add_button);
        empty_imageView = findViewById(R.id.empty_imageView);
        no_data = findViewById(R.id.no_data);
        add_button.setOnClickListener(view -> {

            Intent intent = new Intent(MainActivity.this, AddActivity.class);
            startActivity(intent);


        });

        myDB = new MyDatabaseHelper(MainActivity.this);
        hike_id = new ArrayList<>();
        hike_name = new ArrayList<>();
        hike_date = new ArrayList<>();
        hike_parking = new ArrayList<>();
        hike_length = new ArrayList<>();
        hike_location = new ArrayList<>();
        hike_difficulty = new ArrayList<>();
        hike_desciption = new ArrayList<>();
        hike_observation = new ArrayList<>();
        hike_time = new ArrayList<>();
        hike_comments = new ArrayList<>();


        StoreData();
        customAdapter = new CustomAdapter(MainActivity.this, this, hike_id, hike_name, hike_location, hike_time);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void StoreData(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageView.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                hike_id.add(cursor.getString(0));
                hike_name.add(cursor.getString(1));
                hike_date.add(cursor.getString(2));
                hike_parking.add(cursor.getString(3));
                hike_length.add(cursor.getString(4));
                hike_location.add(cursor.getString(5));
                hike_difficulty.add(cursor.getString(6));
                hike_desciption.add(cursor.getString(7));
                hike_observation.add(cursor.getString(8));
                hike_time.add(cursor.getString(9));
                hike_comments.add(cursor.getString(10));
            }
            empty_imageView.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All ?");
        builder.setMessage("are you sure ? ");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MyDatabaseHelper myDB = new MyDatabaseHelper(MainActivity.this);
                myDB.deleteAllData();
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
                recreate();
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

//    public void filter(String newText){
//        List<MyList> filteredList = new ArrayList<>();
//        for(MyList item : hike_name){
//            if(item.getName().toLowerCase().contains(newText.toLowerCase())){
//
//            }
//
//        }
//
//
//        customAdapter.filterList(filteredList);
//    }

}