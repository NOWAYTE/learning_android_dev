package com.example.booklibrary;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    Activity activity;
    private ArrayList hike_id, hike_name, hike_date, hike_parking, hike_length
            ,hike_location, hike_difficulty, hike_desciption, hike_observation
            , hike_time, hike_comments ;

    Animation translate_anim;
    CustomAdapter(Activity activitty, Context context,
                  ArrayList hike_id,
                  ArrayList hike_name,
                  ArrayList hike_observation,
                  ArrayList hike_location){

        this.activity = activitty;
        this.context = context;
        this.hike_id = hike_id;
        this.hike_name = hike_name;
        this.hike_date = hike_date;
        this.hike_length = hike_length;
        this.hike_location = hike_location;
        this.hike_difficulty = hike_difficulty;
        this.hike_desciption = hike_desciption;
        this.hike_observation = hike_observation;
        this.hike_time = hike_time;
        this.hike_comments = hike_comments;


    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder,int position){

        holder.hike_id_txt.setText(String.valueOf(hike_id.get(position)));
        holder.hike_name_txt.setText(String.valueOf(hike_name.get(position)));
        holder.hike_observation_txt.setText(String.valueOf(hike_location.get(position)));
        holder.hike_location_txt.setText(String.valueOf(hike_observation.get(position)));

        holder.mainLayout.setOnClickListener(view -> {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(hike_id.get(position)));
            intent.putExtra("name", String.valueOf(hike_name.get(position)));
            intent.putExtra("date", String.valueOf(hike_date.get(position)));
            intent.putExtra("parking", String.valueOf(hike_parking.get(position)));
            intent.putExtra("length", String.valueOf(hike_length.get(position)));
            intent.putExtra("location", String.valueOf(hike_location.get(position)));
            intent.putExtra("difficulty", String.valueOf(hike_difficulty.get(position)));
            intent.putExtra("desciption", String.valueOf(hike_desciption.get(position)));
            intent.putExtra("observation", String.valueOf(hike_observation.get(position)));
            intent.putExtra("time", String.valueOf(hike_time.get(position)));
            intent.putExtra("comments", String.valueOf(hike_comments.get(position)));
            activity.startActivityForResult(intent, 1);

        });


    }

    @Override
    public int getItemCount() {
        return hike_id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView hike_id_txt, hike_name_txt, hike_location_txt, hike_observation_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            hike_id_txt =itemView.findViewById(R.id.hike_id_txt);
            hike_name_txt =itemView.findViewById(R.id.hike_name_txt);
            hike_observation_txt =itemView.findViewById(R.id.hike_observation_text);
            hike_location_txt =itemView.findViewById(R.id.hike_location_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
         }
    }

//    public void filterList(List<MyList> filteredList){
//
//        list = filteredList;
//        notifyDataSetChanged();
//
//    }
}