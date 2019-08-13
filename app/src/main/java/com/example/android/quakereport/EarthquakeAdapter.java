package com.example.android.quakereport;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;

import java.text.SimpleDateFormat;

import java.util.ArrayList;

public class EarthquakeAdapter extends ArrayAdapter<EarthquakeItem> {


    public EarthquakeAdapter(@NonNull Context context, @NonNull ArrayList<EarthquakeItem> earthquakeItems) {
        super(context, 0, earthquakeItems);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        EarthquakeItem earthquakeItem = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.earthquake_item, parent, false);
        }

        // Lookup view for data population
        TextView magnitude = (TextView) convertView.findViewById(R.id.mag);
        TextView primaryLocation = (TextView) convertView.findViewById(R.id.primaryLocation);
        TextView offsetLocation = (TextView) convertView.findViewById(R.id.offsetLocation);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView time = (TextView) convertView.findViewById(R.id.time);

        final String url = earthquakeItem.getUrl();


        // Format the magnitude to show 1 decimal place
        String formattedMagnitude = new DecimalFormat("0.0").format(earthquakeItem.getMagnitude());
        // Display the magnitude of the current earthquake in that TextView
        magnitude.setText(formattedMagnitude);

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(earthquakeItem.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        // @param location stores the location JSON object
        String location = earthquakeItem.getLocation();

        if(location.contains("of")){
            String[] locationSplit = location.split("of");
            offsetLocation.setText(locationSplit[0]+" of");
            primaryLocation.setText(locationSplit[1].substring(1));

        } else {
            offsetLocation.setText("Near of");
            primaryLocation.setText(location);
        }


        Date dateObject = new Date(earthquakeItem.getDate());

        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = new SimpleDateFormat("MMM DD, yyyy").format(dateObject);
        // Display the date of the current earthquake in that TextView
        date.setText(formattedDate);

        // Format the time string (i.e. "4:30PM")
        String formattedTime = new SimpleDateFormat("h:mm a").format(dateObject);
        // Display the time of the current earthquake in that TextView
        time.setText(formattedTime);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                if (browserIntent.resolveActivity(getContext().getPackageManager()) != null) {
                    getContext().startActivity(browserIntent);
                }

            }
        });

        // Return the completed view to render on screen
        return convertView;

    }

    private int getMagnitudeColor(double magnitude) {

        int magnitude1Color;

        switch ((int) magnitude){
            case 0:
            case 1:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude1);
                break;
            case 2 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude2);
                break;
            case 3 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude3);
                break;
            case 4 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude4);
                break;
            case 5 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude5);
                break;
            case 6 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude6);
                break;
            case 7 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude7);
                break;
            case 8 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude8);
                break;
            case 9 :
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude9);
                break;
            default:
                magnitude1Color = ContextCompat.getColor(getContext(), R.color.magnitude10plus);
                break;
        }

        return magnitude1Color;
    }
}
