package com.example.hp_pc.quake;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.io.IOException;
import java.net.URL;

public class earthquakeloader extends AsyncTaskLoader<String> {
    private String url;
    public earthquakeloader(Context context, String url) {
        super(context);
        this.url=url;
        // TODO: Finish implementing this constructor
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
   public String loadInBackground() {
        try {
           String s=jsonstring.httprequest(new URL(this.url));
           return s;
        } catch (IOException e) {
            e.printStackTrace();
            }
           return "";
    }

}
