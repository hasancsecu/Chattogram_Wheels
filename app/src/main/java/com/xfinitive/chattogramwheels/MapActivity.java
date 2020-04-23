package com.xfinitive.chattogramwheels;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.xfinitive.chattogramwheels.directionhelpers.FetchURL;
import com.xfinitive.chattogramwheels.directionhelpers.TaskLoadedCallback;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.List;


public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, TaskLoadedCallback {
    private GoogleMap mMap;
    private MarkerOptions place1, place2;
    private Polyline currentPolyline;
    private Toolbar toolbar;
    private AlertDialog.Builder alertDialog;
    List<MarkerOptions> markerOptions = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_activity);
        setTitle("See Map");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent secondIntent = getIntent();
        String[] start = secondIntent.getStringArrayExtra("start");
        String[] end = secondIntent.getStringArrayExtra("end");
        String loc1 = secondIntent.getStringExtra("loc1");
        String loc2 = secondIntent.getStringExtra("loc2");

        double lat1 = Double.parseDouble(start[0]);
        double lng1 = Double.parseDouble(start[1]);
        double lat2 = Double.parseDouble(end[0]);
        double lng2 = Double.parseDouble(end[1]);

        place1 = new MarkerOptions().position(new LatLng(lat1, lng1)).title(loc1);
        place2 = new MarkerOptions().position(new LatLng(lat2, lng2)).title(loc2);

        markerOptions.add(place1);
        markerOptions.add(place2);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        new FetchURL(MapActivity.this).execute(getUrl(place1.getPosition(), place2.getPosition(), "driving"), "driving");

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.addMarker(place1);
        mMap.addMarker(place2);

        showAllMarkers();
    }

    private void showAllMarkers(){
        LatLngBounds.Builder builder = new LatLngBounds.Builder();

        for(MarkerOptions m : markerOptions){
            builder.include(m.getPosition());
        }
        LatLngBounds bounds = builder.build();
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int)  (width * 0.30);

        CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds,width,height,padding);
        mMap.animateCamera(cu);

    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        // Mode
        String mode = "mode=" + directionMode;
        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        // Output format
        String output = "json";
        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        if (currentPolyline != null)
            currentPolyline.remove();
        currentPolyline = mMap.addPolyline((PolylineOptions) values[0]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        menu.add(0, 1, 1, menuIconWithText(getResources().getDrawable(R.drawable.ic_home_black_24dp), getResources().getString(R.string.home)));
        menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.ic_sign_out_24dp), getResources().getString(R.string.logout)));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;
        switch (id) {
            case 1:
                intent = new Intent(MapActivity.this,MainActivity.class);
                startActivity(intent);
                return  true;
            case 2:
                ExitApp.showExitDialog(MapActivity.this, R.style.MyAlertDialogStyle);
                return true;
            case android.R.id.home:
                this.onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private CharSequence menuIconWithText(Drawable r, String title) {

        r.setBounds(0, 0, r.getIntrinsicWidth(), r.getIntrinsicHeight());
        SpannableString sb = new SpannableString("    " + title);
        ImageSpan imageSpan = new ImageSpan(r, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }


}
