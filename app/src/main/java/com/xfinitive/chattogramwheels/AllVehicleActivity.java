package com.xfinitive.chattogramwheels;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AllVehicleActivity extends AppCompatActivity {
    private ListView allVehicles;
    private Toolbar toolbar;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_vehicles);
        setTitle("All Vehicles");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Showing Banner Ads
        MobileAds.initialize(this, "ca-app-pub-4805956057529447~5307565482");
        adView = findViewById(R.id.adView2);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                TextView adText = findViewById(R.id.adText2);
                adText.setVisibility(View.GONE);
            }
        });

        allVehicles = findViewById(R.id.listViewAllVehicles);
        String[] busNames = getResources().getStringArray(R.array.bus_names);
        String[] busType = getResources().getStringArray(R.array.bus_type);

        CustomAdapterMenu adapter = new CustomAdapterMenu(AllVehicleActivity.this, busNames, busType);
        allVehicles.setAdapter(adapter);

        allVehicles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TextView selectedText = view.findViewById(R.id.textViewId);
                String[] value = selectedText.getText().toString().split(" ");
                String busName = value[0] + " " + value[1] + " " + value[2] ;

                Intent intent = new Intent(AllVehicleActivity.this, ListBuses.class);
                intent.putExtra("bus_name", busName);
                startActivity(intent);
            }
        });

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
                intent = new Intent(AllVehicleActivity.this,MainActivity.class);
                startActivity(intent);
                return  true;
            case 2:
                ExitApp.showExitDialog(AllVehicleActivity.this, R.style.MyAlertDialogStyle);
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
