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
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Hashtable;

public class SuggestRouteActivity extends AppCompatActivity  implements View.OnClickListener {
    private Toolbar toolbar;
    private EditText busText, routeText, emailText;
    private Button sendMail;
    private String toMail;
    AdView adView;
    private AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggest_route);
        setTitle("Suggest New Route");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //Showing Banner Ads
        MobileAds.initialize(this, "ca-app-pub-4805956057529447~5307565482");
        adView = findViewById(R.id.adView4);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                TextView adText = findViewById(R.id.adText4);
                adText.setVisibility(View.GONE);
            }
        });

        busText = findViewById(R.id.busTextId);
        routeText = findViewById(R.id.routeTextId);
        emailText = findViewById(R.id.userEmailTextId);
        sendMail = findViewById(R.id.sendMailBtnId);
        toMail = "hasansouravcsecu@gmail.com";

        sendMail.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        String email = emailText.getText().toString();
        String busName = busText.getText().toString();
        String route =  routeText.getText().toString();

        if(email.isEmpty() ) {
            emailText.setError("Please Provide an Email");
            emailText.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            emailText.setError("Please Enter a Valid Email Address");
            emailText.requestFocus();
            return;
        }
        if(busName.isEmpty()){
            busText.setError("Please Enter Vehicle Name");
            busText.requestFocus();
            return;
        }
        if(route.isEmpty()){
            routeText.setError("Please Enter Route ");
            routeText.requestFocus();
            return;
        }
        if (isNetworkAvailable()) {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    sendMailUsingSendGrid(emailText.getText().toString(), toMail, busText.getText().toString(), routeText.getText().toString());
                }
            });
        }else{
            Toast.makeText(getApplicationContext(), "Your Device has no access to the Internet. Please Try Again!", Toast.LENGTH_LONG).show();
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
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
                intent = new Intent(SuggestRouteActivity.this,MainActivity.class);
                startActivity(intent);
                return  true;
            case 2:
                ExitApp.showExitDialog(SuggestRouteActivity.this, R.style.MyAlertDialogStyle);
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

    private void sendMailUsingSendGrid(String from, String to, String subject, String mailBody){
        Hashtable<String, String> params = new Hashtable<>();
        params.put("to", to);
        params.put("from", from);
        params.put("subject", subject);
        params.put("text", mailBody);

        SendGridAsyncTask email = new SendGridAsyncTask();
            try {
                email.execute(params);
                Toast.makeText(getApplicationContext(), "Sending mail...", Toast.LENGTH_LONG).show();
                emailText.setText("");
                busText.setText("");
                routeText.setText("");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

}
