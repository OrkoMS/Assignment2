package com.roar71.assignment2;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.google.gson.Gson;
import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rView;
    private String data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        TextView toolbarTextView =findViewById(R.id.toolbar_text);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/srisakdi_bold.ttf");

        toolbarTextView.setTypeface(custom_font);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
            toolbar.setElevation(10f);
        }
        toolbar.inflateMenu(R.menu.menu);

        new AsyncThread().execute();
        rView = findViewById(R.id.recycler_view);
        rView.setLayoutManager(new LinearLayoutManager(this));


    }

    public String fetchDataFromApi(String url)throws IOException
    {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() != null) {
                return response.body().string();
            }
            else {
                return null;
            }
        }

    }
    @SuppressLint("StaticFieldLeak")
    private class AsyncThread extends AsyncTask{

        @Override
        protected Object doInBackground(Object[] objects) {
            try {
                data=fetchDataFromApi("http://api.openweathermap.org/data/2.5/forecast/daily?APPID=c13159d2d9b7d01343afbc8acde7572b&q=Dhaka&mode=json&units=metric&cnt=16");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            WeatherRep weatherRep =new Gson().fromJson(data,WeatherRep.class);

            rView.setAdapter(new MyAdapter(MainActivity.this,weatherRep.getWeatherList()));
        }
    }
}