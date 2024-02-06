package com.example.lista7;

import static java.lang.Math.round;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    String CityName = "";
    int selectedDay = 1;

    EditText CityInput;
    Button FindBtn;

    TabLayout TabLayout;
    TabLayout TabLayout2;

    TextView CityNameField;
    TextView CityNameField2;
    TextView CityNameField3;
    String[] miasta = {"Nysa", "Opole", "Warszawa"};

    TextView TextView2;
    int selectedCity = 0;

    ImageView icon1;

    TextView tvResult;
    public final String url = "https://api.openweathermap.org/data/2.5/forecast";
    public  final String appid = "d589ce9a6e52c645b8c0063cfd154e7d";

//    public static void hideSoftKeyboard(Activity activity, View view) {
//        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
//    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
            getWeatherDetails("Nysa", "Poland");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvResult = (TextView)findViewById(R.id.tvResult);

        TabLayout = (TabLayout)findViewById(R.id.tabLayout);
        TabLayout2 = (TabLayout)findViewById(R.id.tabLayout2);

        icon1 = (ImageView)findViewById(R.id.imageView1);

        TextView2 = (TextView)findViewById(R.id.textView2);

        CityNameField = (TextView)findViewById(R.id.cityNameText);
        CityNameField2 = (TextView)findViewById(R.id.cityNameText2);
        CityNameField3 = (TextView)findViewById(R.id.cityNameText3);


        TabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                selectedDay = 	TabLayout.getSelectedTabPosition() + 1;
                getWeatherDetails(CityName, "Poland");

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tvResult.setText("");
                 CityNameField.setText("");
                 CityNameField2.setText("");
                 CityNameField3.setText("");
                 TextView2.setText("");
                 icon1.setImageBitmap(null);
                 tvResult.setText("");
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // Domyślnie nic nie rób
            }
        });

        TabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    selectedCity = TabLayout2.getSelectedTabPosition();
                    CityName = miasta[selectedCity];
                    getWeatherDetails(CityName, "Poland");

                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {
                    tvResult.setText("");
                    CityNameField.setText("");
                    CityNameField2.setText("");
                    CityNameField3.setText("");
                    TextView2.setText("");
                    icon1.setImageBitmap(null);
                    tvResult.setText("");
                }

                @Override
                public void onTabReselected(TabLayout.Tab tab) {
                    // Domyślnie nic nie rób
                }
            });

        Activity act = this;
        CityInput = (EditText)findViewById(R.id.cityInput);
        FindBtn = (Button)findViewById(R.id.findBtn);
        FindBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

//                        hideSoftKeyboard(act, CityInput);
                        tvResult.setText("");
                        CityName = CityInput.getText().toString();
                        getWeatherDetails(CityName, "Poland");
                    }
                }
        );


    }


    class IconDownloaderTask extends AsyncTask<String, Void, Bitmap> {
        Bitmap result = null;
        ImageView ikona;

        public IconDownloaderTask(ImageView cokolwiek){
            ikona = cokolwiek;
        }
        @Override
        protected Bitmap doInBackground(String... strings) {
            InputStream in = null;

            try {
                in = new URL("http://openweathermap.org/img/wn/" + strings[0] + "@4x.png").openStream();
                result = BitmapFactory.decodeStream(in);
            } catch (IOException e) {
//                throw new RuntimeException(e);
            }
            return result;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            ikona.setImageBitmap(result);
        }

    }


    public void getWeatherDetails(String city, String country) {
        String tempUrl = "";
        if(city.equals("")){
            tvResult.setText("City field can not be empty!");
        }else{
            if(!country.equals("")){
                tempUrl = url + "?q=" + city + "," + country + "&appid=" + appid;
            }else{
                tempUrl = url + "?q=" + city + "&appid=" + appid;
            }
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String output = "";
                    try {
                        JSONObject odpowiedzServera = new JSONObject(response);
                        JSONArray listaDanych = odpowiedzServera.getJSONArray("list");


                        JSONObject jsonResponse = listaDanych.getJSONObject((selectedDay-1) * 8 );

                        JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);

                        String description = jsonObjectWeather.getString("description");
                        tvResult.setText(description);
                        JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");

                        String iconString = jsonObjectWeather.getString("icon");
                        new IconDownloaderTask(icon1).execute(iconString);

                        double temp = round(jsonObjectMain.getDouble("temp") - 273.15);

                        double feelsLike = round(jsonObjectMain.getDouble("feels_like") - 273.15);

                        float pressure = jsonObjectMain.getInt("pressure");

                        int humidity = jsonObjectMain.getInt("humidity");

                        String date =  jsonResponse.getString("dt_txt");

                        JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");

                        String wind = jsonObjectWind.getString("speed");

                        JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");

                        String clouds = jsonObjectClouds.getString("all");


                        CityNameField2.setText("Weather in ");
                        CityNameField.setText(city);
                        CityNameField3.setText("for day: " + date);
                        output += "\n Temp: " + temp + " °C"
                                + "\n Feels Like: " + feelsLike + " °C"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Wind Speed: " + wind + "m/s (meters per second)"
                                + "\n Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";
                        TextView2.setText(output);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }

            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }
}