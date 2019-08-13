package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<EarthquakeItem>> {

    public EarthquakeLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<EarthquakeItem> loadInBackground() {
        ArrayList<EarthquakeItem> earthquakes = QueryUtils.fetchEarthquakes();
        return earthquakes;
    }
}
