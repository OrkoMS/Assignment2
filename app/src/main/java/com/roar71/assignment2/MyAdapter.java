package com.roar71.assignment2;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
    private Context mainContext;
    private List<WeatherList> wlists;

    public MyAdapter(Context mainContext, List<WeatherList> weatherLists) {
        this.mainContext = mainContext;
        this.wlists = weatherLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View listHolderView = LayoutInflater.from(mainContext).inflate(R.layout.weather_list, viewGroup, false);
        return new MyViewHolder(listHolderView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.MyViewHolder viewHolder, int i) {

        viewHolder.rvDayView.setText(wlists.get(i).getDt().toString());
        viewHolder.rvDesView.setText(wlists.get(i).getWeather().get(0).getDescription());
        viewHolder.rvMaxTemp.setText(wlists.get(i).getTemp().getMax().toString()+"°");
        viewHolder.rvMinTemp.setText(wlists.get(i).getTemp().getMin().toString()+"°");
    }

    @Override
    public int getItemCount() {
        return wlists.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView rvDayView;
        TextView rvDesView;
        TextView rvMaxTemp;
        TextView rvMinTemp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            rvDayView = itemView.findViewById(R.id.rv_day_text);
            rvDesView = itemView.findViewById(R.id.rv_weather_des_text);
            rvMaxTemp =itemView.findViewById(R.id.rv_max_temp);
            rvMinTemp =itemView.findViewById(R.id.rv_min_temp);
        }
    }

}