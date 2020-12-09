package com.example.hp_pc.quake;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.Loader;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static com.example.hp_pc.quake.R.id.list1;

public class EarthquakeActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String>{
    public String url="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=4&limit=100";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        ConnectivityManager cm=(ConnectivityManager)getApplicationContext().getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
            NetworkInfo nf = cm.getActiveNetworkInfo();
            if(nf!=null) {
                LoaderManager m = getSupportLoaderManager();
                m.initLoader(1, null, EarthquakeActivity.this);
            }
            else
            {
            ProgressBar spin=(ProgressBar)findViewById(R.id.loadingspinner);
            spin.setVisibility(View.GONE);
            TextView txt = (TextView) findViewById(R.id.evt);
            txt.setText("No Internet Connection");
        }

    }

 @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        earthquakeloader newloader=new earthquakeloader(EarthquakeActivity.this,url);
        return newloader;
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        ProgressBar spin=(ProgressBar)findViewById(R.id.loadingspinner);
        spin.setVisibility(View.GONE);
        jsonstring.SAMPLE_JSON_RESPONSE=data;
        final ArrayList<earthquake_data> earthquakes = jsonstring.extractearthquakes();
        ListView earthquakeListView = (ListView) findViewById(list1);
        TextView evt=(TextView)findViewById(R.id.evt);
        evt.setText("No Data Found");
        earthquakeListView.setEmptyView(evt);
        CustomAdapter adapter = new CustomAdapter(EarthquakeActivity.this, earthquakes);
        earthquakeListView.setAdapter(adapter);
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent();
                i.setAction(Intent.ACTION_VIEW);
                i.setData(Uri.parse(earthquakes.get(position).getUrl()));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setPackage("com.android.chrome");
                if(i.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }
}