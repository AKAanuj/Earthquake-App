package com.example.hp_pc.quake;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter<earthquake_data> {

    private String delimiter="of";
    private Context mcontext;
    public CustomAdapter(Context context,ArrayList<earthquake_data> list)
    {
        super(context,0,list);
        mcontext=context;
    }


    public View getView(int position, View convertView, ViewGroup parent)
    {
    earthquake_data data=getItem(position);
    convertView= LayoutInflater.from(getContext()).inflate(R.layout.custom_layout,null);

    TextView magview=(TextView) convertView.findViewById(R.id.magnitude);
    GradientDrawable circle=(GradientDrawable)magview.getBackground();
    double mag=data.getMagnitude();
    circle.setColor(data.getMagnitudeColor(mag,mcontext));

    DecimalFormat decimalFormat=new DecimalFormat("#.0");
    magview.setText(decimalFormat.format(mag));
        TextView locationview1=(TextView) convertView.findViewById(R.id.location1);
        TextView locationview2=(TextView) convertView.findViewById(R.id.location2);
        String locationtext=data.getLocation();
        if(locationtext.contains(delimiter))
        {
            String [] tokens=locationtext.split(delimiter);
            locationview1.setText(tokens[0].concat(delimiter));
            locationview2.setText(tokens[1]);
        }
        else
        {
            locationview1.setText("Near The");
            locationview2.setText(locationtext);
        }
        TextView dateview=(TextView) convertView.findViewById(R.id.date);
        dateview.setText(data.getDates());
        return convertView;
    }
}
