package com.xfinitive.chattogramwheels;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.app.Activity;
import android.app.ProgressDialog;
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
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;


public class AboutActivity extends AppCompatActivity {
    private WebView webView;
    private Toolbar toolbar;
    private ProgressDialog progressDialog;
    private TextView txtError;
    private  boolean isError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setTitle("About");
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText(toolbar.getTitle());
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final Activity activity =AboutActivity.this;
        txtError = findViewById(R.id.errorText);
        webView = findViewById(R.id.webViewId);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            public void onLoadResource(WebView view, String url) {
                // Check to see if there is a progress dialog
                if (progressDialog == null) {
                    // If no progress dialog, make one and set message
                    progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage("Loading Please Wait...");
                    progressDialog.show();
                    // Hide the webview while loading
                    webView.setEnabled(false);
                }
            }

            public void onPageFinished(WebView view, String url) {
                // Page is done loading;
                // hide the progress dialog and show the webview
                if (progressDialog.isShowing()) {
                    webView.setEnabled(true);
                    progressDialog.dismiss();

                }
                if (isError){
                    webView.setVisibility(View.GONE);
                    txtError.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error){
                //Your code to do
                isError = true;
                Toast.makeText(getApplicationContext(), "Your Internet Connection May not be active."  , Toast.LENGTH_LONG).show();
            }
        });

        webView.loadUrl("https://xfinitive.herokuapp.com/");

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
                intent = new Intent(AboutActivity.this,MainActivity.class);
                startActivity(intent);
                return  true;
            case 2:
                ExitApp.showExitDialog(AboutActivity.this, R.style.MyAlertDialogStyle);
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

    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else{
            super.onBackPressed();;
        }
    }
}
