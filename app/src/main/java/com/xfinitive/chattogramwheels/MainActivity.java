package com.xfinitive.chattogramwheels;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity{
    private AutoCompleteTextView sourceTextview, destinationTextview;
    private TextView searchTextView;
    String[] sourceLocation, destinationLocation;
    private Button searchBtn;
    private ListView listView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Chattogram Wheels");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        searchBtn = findViewById(R.id.searchBtnId);
        sourceTextview = findViewById(R.id.sourceId);
        destinationTextview = findViewById(R.id.destinationId);
        searchTextView = findViewById(R.id.searchViewId);
        sourceLocation = getResources().getStringArray(R.array.location);
        destinationLocation = getResources().getStringArray(R.array.location);

        ArrayAdapter<String> sourceAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, sourceLocation);
        sourceTextview.setThreshold(1);
        sourceTextview.setAdapter(sourceAdapter);

        ArrayAdapter<String> destinationAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1, destinationLocation);
        destinationTextview.setThreshold(1);
        destinationTextview.setAdapter(destinationAdapter);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] bus1 = getResources().getStringArray(R.array.bus1);
                String[] bus2 = getResources().getStringArray(R.array.bus2);
                String[] bus3 = getResources().getStringArray(R.array.bus3);
                String[] bus3_md = getResources().getStringArray(R.array.bus3_md);
                String[] bus3_sm = getResources().getStringArray(R.array.bus3_sm);
                String[] bus4 = getResources().getStringArray(R.array.bus4);
                String[] bus6 = getResources().getStringArray(R.array.bus6);
                String[] bus7 = getResources().getStringArray(R.array.bus7);
                String[] bus8 = getResources().getStringArray(R.array.bus8);
                String[] bus10= getResources().getStringArray(R.array.bus10);
                String[] bus11 = getResources().getStringArray(R.array.bus11);
                String[] leguna8 = getResources().getStringArray(R.array.leguna8);
                String[] leguna15 = getResources().getStringArray(R.array.leguna15);
                String[] leguna16 = getResources().getStringArray(R.array.leguna16);
                String[] allBus = getResources().getStringArray(R.array.bus_names);
                String[] allBusType = getResources().getStringArray(R.array.bus_type);
                StringBuilder stringBuilder1 = new StringBuilder();
                StringBuilder stringBuilder2 = new StringBuilder();
                final String startLoc = sourceTextview.getText().toString();
                final String endLoc = destinationTextview.getText().toString();

                if(view.getId() == R.id.searchBtnId){
                    if(startLoc.isEmpty()){
                        sourceTextview.setError("Please Enter Source Location");
                        sourceTextview.requestFocus();
                        return;
                    }if(endLoc.isEmpty()){
                        destinationTextview.setError("Please Enter Destination Location");
                        destinationTextview.requestFocus();
                        return;
                    }
                    searchTextView.setVisibility(View.VISIBLE);
                    listView = findViewById(R.id.listView);
                    for(int i=0;i<bus1.length;i++){
                        for(int j=0;j<bus1.length;j++) {
                            if (startLoc.equals(bus1[i]) && endLoc.equals(bus1[j])) {
                                stringBuilder1.append(allBus[0]  + ",");
                                stringBuilder2.append(allBusType[0] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus2.length;i++){
                        for(int j=0;j<bus2.length;j++) {
                            if (startLoc.equals(bus2[i]) && endLoc.equals(bus2[j])) {
                                stringBuilder1.append(allBus[1]  + ",");
                                stringBuilder2.append(allBusType[1] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus3.length;i++){
                        for(int j=0;j<bus3.length;j++) {
                            if (startLoc.equals(bus3[i]) && endLoc.equals(bus3[j])) {
                                stringBuilder1.append(allBus[2]  + ",");
                                stringBuilder2.append(allBusType[2] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus3_md.length;i++){
                        for(int j=0;j<bus3_md.length;j++) {
                            if (startLoc.equals(bus3_md[i]) && endLoc.equals(bus3_md[j])) {
                                stringBuilder1.append(allBus[3]  + ",");
                                stringBuilder2.append(allBusType[3] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus3_sm.length;i++){
                        for(int j=0;j<bus3_sm.length;j++) {
                            if (startLoc.equals(bus3_sm[i]) && endLoc.equals(bus3_sm[j])) {
                                stringBuilder1.append(allBus[4]  + ",");
                                stringBuilder2.append(allBusType[4] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus4.length;i++){
                        for(int j=0;j<bus4.length;j++) {
                            if (startLoc.equals(bus4[i]) && endLoc.equals(bus4[j])) {
                                stringBuilder1.append(allBus[5]  + ",");
                                stringBuilder2.append(allBusType[5] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus6.length;i++){
                        for(int j=0;j<bus6.length;j++) {
                            if (startLoc.equals(bus6[i]) && endLoc.equals(bus6[j])) {
                                stringBuilder1.append(allBus[6]  + ",");
                                stringBuilder2.append(allBusType[6] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus7.length;i++){
                        for(int j=0;j<bus7.length;j++) {
                            if(startLoc.equals(bus7[i]) && endLoc.equals(bus7[j])) {
                                stringBuilder1.append(allBus[7]  + ",");
                                stringBuilder2.append(allBusType[7] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus8.length;i++){
                        for(int j=0;j<bus8.length;j++) {
                            if (startLoc.equals(bus8[i]) && endLoc.equals(bus8[j])) {
                                stringBuilder1.append(allBus[8]  + ",");
                                stringBuilder2.append(allBusType[8] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus10.length;i++){
                        for(int j=0;j<bus10.length;j++) {
                            if (startLoc.equals(bus10[i]) && endLoc.equals(bus10[j])) {
                                stringBuilder1.append(allBus[9]  + ",");
                                stringBuilder2.append(allBusType[9] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<bus11.length;i++){
                        for(int j=0;j<bus11.length;j++) {
                            if (startLoc.equals(bus11[i]) && endLoc.equals(bus11[j])) {
                                stringBuilder1.append(allBus[10]  + ",");
                                stringBuilder2.append(allBusType[10] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<leguna8.length;i++){
                        for(int j=0;j<leguna8.length;j++) {
                            if (startLoc.equals(leguna8[i]) && endLoc.equals(leguna8[j])) {
                                stringBuilder1.append(allBus[11]  + ",");
                                stringBuilder2.append(allBusType[11] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<leguna15.length;i++){
                        for(int j=0;j<leguna15.length;j++) {
                            if (startLoc.equals(leguna15[i]) && endLoc.equals(leguna15[j])) {
                                stringBuilder1.append(allBus[12]  + ",");
                                stringBuilder2.append(allBusType[12] + ",");
                                break;
                            }
                        }
                    }
                    for(int i=0;i<leguna16.length;i++){
                        for(int j=0;j<leguna16.length;j++) {
                            if (startLoc.equals(leguna16[i]) && endLoc.equals(leguna16[j])) {
                                stringBuilder1.append(allBus[13]  + ",");
                                stringBuilder2.append(allBusType[13] + ",");
                                break;
                            }
                        }
                    }

                    String value1 = stringBuilder1.toString();
                    String value2 = stringBuilder2.toString();
                    String[] busNames = value1.split(",");
                    String[] busType = value2.split(",");

                    if(!value1.isEmpty()) {
                        CustomAdapterMenu adapter = new CustomAdapterMenu(MainActivity.this, busNames, busType);
                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                TextView selectedText = view.findViewById(R.id.textViewId);
                                String[] value = selectedText.getText().toString().split(" ");
                                final String busName = value[0] + " " + value[1] + " " + value[2] ;

                                Intent intent = new Intent(MainActivity.this, ListBuses.class);
                                intent.putExtra("bus_name", busName);
                                startActivity(intent);
                            }
                        });
                    }else {
                        listView.setVisibility(View.GONE);
                        TextView noVehicle = findViewById(R.id.noVehicleId);
                        noVehicle.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
                RateThisApp.showRateDialog(MainActivity.this, R.style.MyAlertDialogStyle);
                return true;
            case 5:
                    ExitApp.showExitDialog(MainActivity.this, R.style.MyAlertDialogStyle);
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