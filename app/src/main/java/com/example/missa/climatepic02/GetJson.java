package com.example.missa.climatepic02;

/**
 * Created by missa on 22/03/2018.
 */

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;



public class GetJson extends AsyncTask<Void,Void,Void> {
    String data ="";
    String dataParsed = "";
    String singleParsed ="";
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            URL url = new URL("http://api.apixu.com/v1/current.json?key=5b1644f78f4249dd8ae81305182203&q=Tepic");
            HttpURLConnection httpURLConnection = (HttpURLConnection)
                    url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new
                    InputStreamReader(inputStream));
            String line = "";
            while(line != null){
                line = bufferedReader.readLine();
                data = data + line;
            }
            JSONObject JO = new JSONObject(data);
            JSONObject JOLocation =  JO.getJSONObject("location");
            JSONObject JOCurrent = JO.getJSONObject("current");

            singleParsed = "Ciudad: " + JOLocation.get("name") +"\n"+
                    "Region: " + JOLocation.get("region") +"\n"+
                    "Pais: " + JOLocation.get("country") + "\n"+
                    "Temperatura: " + String.valueOf(JOCurrent.getDouble("temp_c"))+ "\n";
            dataParsed = dataParsed + singleParsed +"\n" ;




        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        MainActivity.data.setText(this.dataParsed);
    }
}
