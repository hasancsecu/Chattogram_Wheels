package com.xfinitive.chattogramwheels;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class ListBuses extends AppCompatActivity {
    private ListView listView;
    private String[] locNames;
    private Toolbar toolbar;
    private TextView busName, busRoute;
    private Button seeMap,seeVehicle;
    AdView adView;
    private AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_buses);
        setTitle("Bus Route");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Showing Banner Ads
        MobileAds.initialize(this, "ca-app-pub-4805956057529447~5307565482");
        adView = findViewById(R.id.adView1);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                TextView adText = findViewById(R.id.adText1);
                adText.setVisibility(View.GONE);
            }
        });

        busName = findViewById(R.id.busNameHeaderId);
        busRoute = findViewById(R.id.busRouteViewId);
        listView = findViewById(R.id.listView2Id);
        seeMap = findViewById(R.id.seeMapId);
        seeVehicle = findViewById(R.id.seeVehicleImage);

        Intent secondIntent = getIntent();
        String bus = secondIntent.getStringExtra("bus_name");
        showDetails(bus);

        seeMap.setOnClickListener(new View.OnClickListener() {
            String bus = busName.getText().toString();
            String[] newMarket = getResources().getStringArray(R.array.NewMarket);
            String[] hathazari = getResources().getStringArray(R.array.Hathazari);
            String[] alonkar = getResources().getStringArray(R.array.Alonkar);
            String[] kaptai = getResources().getStringArray(R.array.KaptaiRasta);
            String[] seaBeach = getResources().getStringArray(R.array.SeaBeach);
            String[] cu = getResources().getStringArray(R.array.CU);
            String[] bohoddaehat = getResources().getStringArray(R.array.Bohoddarhat);
            String[] foteyabad = getResources().getStringArray(R.array.Foteyabad);
            String[] vatriary = getResources().getStringArray(R.array.Vatiary);
            String[] laldighi = getResources().getStringArray(R.array.Laldighi);
            String[] oxygen = getResources().getStringArray(R.array.Oxygen);
            String[] kalurghat = getResources().getStringArray(R.array.Kalurghat);
            String[] kathgor = getResources().getStringArray(R.array.Kathgor);
            String[] twoGate = getResources().getStringArray(R.array.TwoGate);
            String[] kotowali = getResources().getStringArray(R.array.Kotowali);

            @Override
            public void onClick(View view) {

                if (isNetworkAvailable()) {
                    Intent intent = new Intent(ListBuses.this, MapActivity.class);
                        if(bus == "1 NO BUS"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", bohoddaehat);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Bohoddarhat");
                        }else if(bus == "2 NO BUS"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", kaptai);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Kaptai");
                        } else if(bus == "3 NO BUS(lg)"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", hathazari);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Hathazari");
                        }else if(bus == "3 NO BUS(md)"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", cu);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "CU");
                        } else if(bus == "3 NO BUS(sm)"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", foteyabad);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Foteyabad");
                        }else if(bus == "4 NO BUS"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", vatriary);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Vatiaru");
                        }else if(bus == "6 NO BUS"){
                            intent.putExtra("start",laldighi);
                            intent.putExtra("end", seaBeach);
                            intent.putExtra("loc1", "Laldighi");
                            intent.putExtra("loc2", "Sea Beach");
                        }else if(bus == "7 NO BUS"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", vatriary);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Vatiary");
                        }else if(bus == "8 NO BUS"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", oxygen);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Oxygen");
                        }else if(bus == "10 NO BUS"){
                            intent.putExtra("start",kalurghat);
                            intent.putExtra("end", kathgor);
                            intent.putExtra("loc1", "New Kalurghat");
                            intent.putExtra("loc2", "Kathgor");
                        }else if(bus == "11 NO BUS"){
                            intent.putExtra("start",vatriary);
                            intent.putExtra("end", seaBeach);
                            intent.putExtra("loc1", "Vatiary");
                            intent.putExtra("loc2", "Sea Beach");
                        }else if(bus == "8 NO LEGUNA"){
                            intent.putExtra("start",twoGate);
                            intent.putExtra("end", oxygen);
                            intent.putExtra("loc1", "2 No Gate");
                            intent.putExtra("loc2", "Oxygen");
                        }else if(bus == "15 NO LEGUNA"){
                            intent.putExtra("start",newMarket);
                            intent.putExtra("end", alonkar);
                            intent.putExtra("loc1", "New Market");
                            intent.putExtra("loc2", "Alonkar");
                        }else if(bus == "16 NO LEGUNA"){
                            intent.putExtra("start",kotowali);
                            intent.putExtra("end", alonkar);
                            intent.putExtra("loc1", "Kotowali");
                            intent.putExtra("loc2", "Alonkar");
                        }
                    startActivity(intent);
                }else {
                    Toast.makeText(getApplicationContext(), "Your Device has no access to the Internet. Please Try Again!", Toast.LENGTH_LONG).show();
                }
            }
        });

        seeVehicle.setOnClickListener(new View.OnClickListener() {
            String bus = busName.getText().toString();
            @Override
            public void onClick(View view) {
                Toast.makeText(ListBuses.this, "This function is not yet implemented. You wil get vehicle images in the next update Insha allah", Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(ListBuses.this, BusImageActivity.class);
//                intent.putExtra("bus_name", bus);
//                startActivity(intent);
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);

        menu.add(0, 1, 1, menuIconWithText(getResources().getDrawable(R.drawable.ic_directions_bus_blue_24dp), getResources().getString(R.string.profile)));
        menu.add(0, 2, 2, menuIconWithText(getResources().getDrawable(R.drawable.ic_location_searching_black_24dp), getResources().getString(R.string.suggest)));
        menu.add(0, 3, 3, menuIconWithText(getResources().getDrawable(R.drawable.ic_info_outline_black_24dp), getResources().getString(R.string.about)));
        menu.add(0, 4, 4, menuIconWithText(getResources().getDrawable(R.drawable.ic_star_black_24dp), getResources().getString(R.string.rate)));
        menu.add(0, 5, 5, menuIconWithText(getResources().getDrawable(R.drawable.ic_sign_out_24dp), getResources().getString(R.string.logout)));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        Intent intent;

        switch (id) {
            case 1:
                intent = new Intent(getApplicationContext(), AllVehicleActivity.class);
                startActivity(intent);
                return true;
            case 2:
                intent = new Intent(getApplicationContext(), SuggestRouteActivity.class);
                startActivity(intent);
                return true;
            case 3:
                intent = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(intent);
                return true;
            case 4:
                RateThisApp.showRateDialog(ListBuses.this, R.style.MyAlertDialogStyle);
                return true;
            case 5:
                ExitApp.showExitDialog(ListBuses.this, R.style.MyAlertDialogStyle);
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

    public void showDetails(String bus){
        if(bus.equals("1 NO BUS")){
            busName.setText("1 NO BUS");
            busRoute.setText("New Market - Bohoddarhat");
            locNames = getResources().getStringArray(R.array.bus1);
        } else if(bus.equals("2 NO BUS")){
            busName.setText("2 NO BUS");
            busRoute.setText("New Market - Kaptai Rastar Matha");
            locNames = getResources().getStringArray(R.array.bus2);
        } else if(bus.equals("3 NO BUS(lg)")){
            busName.setText("3 NO BUS(lg)");
            busRoute.setText("New Market - Hathazari");
            locNames = getResources().getStringArray(R.array.bus3);
        } else if(bus.equals("3 NO BUS(md)")){
            busName.setText("3 NO BUS(md)");
            busRoute.setText("New Market - CU 1 No Gate");
            locNames = getResources().getStringArray(R.array.bus3_md);
        } else if(bus.equals("3 NO BUS(sm)")){
            busName.setText("3 NO BUS(sm)");
            busRoute.setText("New Market - Foteyabad");
            locNames = getResources().getStringArray(R.array.bus3_sm);
        } else if(bus.equals("4 NO BUS")){
            busName.setText("4 NO BUS");
            busRoute.setText("New Market - Vatiary");
            locNames = getResources().getStringArray(R.array.bus4);
        } else if(bus.equals("6 NO BUS")){
            busName.setText("6 NO BUS");
            busRoute.setText("Laldighi - Sea Beach");
            locNames = getResources().getStringArray(R.array.bus6);
        } else if(bus.equals("7 NO BUS")){
            busName.setText("7 NO BUS");
            busRoute.setText("New Market - Vatiary");
            locNames = getResources().getStringArray(R.array.bus7);
        } else if(bus.equals("8 NO BUS")){
            busName.setText("8 NO BUS");
            busRoute.setText("New Market - Oxygen");
            locNames = getResources().getStringArray(R.array.bus8);
        } else if(bus.equals("10 NO BUS")){
            busName.setText("10 NO BUS");
            busRoute.setText("Kalurghat - Kathgor");
            locNames = getResources().getStringArray(R.array.bus10);
        } else if(bus.equals("11 NO BUS")){
            busName.setText("11 NO BUS");
            busRoute.setText("Vatiary - Sea Beach");
            locNames = getResources().getStringArray(R.array.bus11);
        } else if(bus.equals("8 NO LEGUNA")){
            busName.setText("8 NO LEGUNA");
            busRoute.setText("2 No Gate - Oxygen");
            locNames = getResources().getStringArray(R.array.leguna8);
        }else if(bus.equals("15 NO LEGUNA")){
            busName.setText("15 NO LEGUNA");
            busRoute.setText("New Market - Alonkar");
            locNames = getResources().getStringArray(R.array.leguna15);
        }else if(bus.equals("16 NO LEGUNA")){
            busName.setText("16 NO LEGUNA");
            busRoute.setText("Kotowali - Alonkar");
            locNames = getResources().getStringArray(R.array.leguna16);
        }
        CustomAdapterLocation adapter = new CustomAdapterLocation(ListBuses.this, locNames);
        listView.setAdapter(adapter);
    }

}
