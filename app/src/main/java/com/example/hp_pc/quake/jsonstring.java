package com.example.hp_pc.quake;

import android.app.Activity;
import android.util.Log;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


        import android.util.Log;
import android.util.MalformedJsonException;
import android.widget.Toast;

import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;
import static android.widget.Toast.makeText;

public final class jsonstring {
    public static String SAMPLE_JSON_RESPONSE;

    private static String readermethod(InputStream i) throws IOException
    {
        InputStreamReader charstream=new InputStreamReader(i);
        BufferedReader buffer=new BufferedReader(charstream);
        StringBuilder strbuffer=new StringBuilder();
        String s=buffer.readLine();
        while(s!=null)
        {
            strbuffer.append(s);
            s=buffer.readLine();
        }
        return strbuffer.toString();
    }
    public static String httprequest(URL url) throws IOException  {
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException e)
        {
            Log.v("Interruptedexception","caught in http request");
        }
        String output;
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setReadTimeout(250000);
        conn.setConnectTimeout(250000);
        conn.connect();
        if(conn.getResponseCode()!= HttpURLConnection.HTTP_OK)
        {
            conn.disconnect();
        }
        try {

            String s=readermethod(conn.getInputStream());
            conn.disconnect();
            return s;
        }
        catch(IOException e)
        {
            Log.v("ioexception","generated in readerclass caught in connection class");
        }
        return "";
    }

    private jsonstring() {
    }


    public static ArrayList<earthquake_data> extractearthquakes() {

        ArrayList<earthquake_data> earthquakes = new ArrayList<>();
        try {
    JSONObject root=new JSONObject(jsonstring.SAMPLE_JSON_RESPONSE);
    JSONArray features=root.getJSONArray("features");
    JSONObject properties;
    JSONObject earthquakeobject;
    for(int i=0;i<features.length();i++) {
        earthquakeobject=features.getJSONObject(i);
        properties=earthquakeobject.getJSONObject("properties");
        double magnitude = properties.getDouble("mag");
        String location = properties.getString("place");
        long time = properties.getLong("time");
        String Url=properties.getString("url");
       Calendar datetoformat=Calendar.getInstance();
       datetoformat.setTimeInMillis(time);
        SimpleDateFormat dateformatter=new SimpleDateFormat("d MMM yyyy h:mm a", Locale.getDefault());
        String date=dateformatter.format(datetoformat.getTime());
        earthquakes.add(new earthquake_data(location, magnitude,date,Url));
        Log.v("url",Url);
    }
        } catch (JSONException e) {
        }
        return earthquakes;
    }

}