package com.example.hp_pc.quake;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;

public class earthquake_data {
    private String location;
    private double magnitude;
    private String dates;
    private String url;

    public earthquake_data(String loc,double mag,String date,String Url)
    {
        this.location=loc;
        this.magnitude=mag;
        this.dates=date;
        this.url=Url;
    }

    public String getUrl()
    {
        return url;
    }
    public int getMagnitudeColor(double mag, Context context)
    {
        int color;
        int magnitude= (int) Math.floor(mag);
        switch(magnitude)
        {
            case 0:
            case 1:
            color= R.color.magnitude1;
            break;
            case 2:
                color= R.color.magnitude2;
                break;
            case 3:
                color= R.color.magnitude3;
                break;
            case 4:
                color= R.color.magnitude4;
                break;
            case 5:
                color= R.color.magnitude5;
                break;
            case 6:
                color=R.color.magnitude6;
                break;
            case 7:
                color= R.color.magnitude7;
                break;
            case 8:
                color= R.color.magnitude8;
                break;
            case 9:
                color=R.color.magnitude9;
                break;
            default:
                color=R.color.magnitude10plus;
        }
        return ContextCompat.getColor(context,color);
    }
    public String getLocation()
    {
        return this.location;
    }

    public String getDates()
    {
        return this.dates;
    }
    public double getMagnitude()
    {
        return this.magnitude;
    }
}
