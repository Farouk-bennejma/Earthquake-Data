/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderCallbacks<List<EarthquakeItem>>  {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        LoaderManager loaderManager = getLoaderManager();

        loaderManager.initLoader(1, null, this);

    }

    @Override
    public Loader<List<EarthquakeItem>> onCreateLoader(int id, Bundle args) {
        return new EarthquakeLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<List<EarthquakeItem>> loader, List<EarthquakeItem> earthquakes) {
        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter earthquakeAdapter = new EarthquakeAdapter(EarthquakeActivity.this , (ArrayList<EarthquakeItem>) earthquakes);

        progressBar = (ProgressBar) findViewById(R.id.loading_spinner);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(earthquakeAdapter);
        progressBar.setVisibility(View.GONE);
    }


    @Override
    public void onLoaderReset(Loader<List<EarthquakeItem>> loader) {

    }
}
