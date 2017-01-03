package com.example.arvidquarshie.weatherapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by arvid.quarshie on 12/26/2016.
 */

public class jsonDownloadTask extends AsyncTask {




    @Override
    protected Object doInBackground(Object[] urls) {
        String result = null;
        URL url;
        HttpURLConnection urlConnection = null;


        try {
            Log.d("@Async","async task");
            url = new URL((String) urls[0]);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream in = urlConnection.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data = reader.read();
            Log.d("@Data",String.valueOf(data));

            while (data != -1) {
                char current = (char) data;
                result += current;
                data = reader.read();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        try {
            JSONObject jsonObject = new JSONObject((String)result);
            JSONObject weatherData = new JSONObject(jsonObject.getString("main"));
            Double temperature = Double.parseDouble(weatherData.getString("temp"));
            String placename = jsonObject.getString("name");
            Log.d("@WeatherData:",jsonObject.getString("main"));
            MainActivity.place.setText(String.valueOf(placename));
            MainActivity.temp.setText(String.valueOf(temperature));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
