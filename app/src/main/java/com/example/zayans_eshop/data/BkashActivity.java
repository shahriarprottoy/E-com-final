package com.example.zayans_eshop.data;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.zayans_eshop.R;
import com.google.gson.Gson;

public class BkashActivity extends AppCompatActivity {
    WebView wvBkashPayment;
    ProgressBar progressBar;
    String amount = "";
    String request = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bkashactivityscreen);

        wvBkashPayment = findViewById(R.id.wvBkashPayment);
        progressBar = findViewById(R.id.progressBar);

        //check there is any intent data or not
        if (getIntent().getExtras() == null) {
            //no data
            Toast.makeText(this, "Amount is empty. You can't pay through bkash. Try again", Toast.LENGTH_SHORT).show();
            return;
        } else {
            amount = getIntent().getExtras().getString("AMOUNT");  //make sure your keyname is same as MainActivity.

            //Create a PaymentRequests model
            PaymentRequest paymentRequest = new PaymentRequest();
            paymentRequest.setAmount(amount);
            paymentRequest.setIntent("sale");

            Gson gson = new Gson();
            request = gson.toJson(paymentRequest);
            //TODO to understand follow
            WebSettings webSettings = wvBkashPayment.getSettings();
            webSettings.setJavaScriptEnabled(true);

            /*
             * Below part is for enabling webview settings for using javascript and accessing html files and other assets
             */
            wvBkashPayment.setClickable(true);
            wvBkashPayment.getSettings().setDomStorageEnabled(true);
            wvBkashPayment.getSettings().setAppCacheEnabled(false);
            wvBkashPayment.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            wvBkashPayment.clearCache(true);
            wvBkashPayment.getSettings().setAllowFileAccessFromFileURLs(true);
            wvBkashPayment.getSettings().setAllowUniversalAccessFromFileURLs(true);

            /*
             * To control any kind of interaction from html file
             */
            wvBkashPayment.addJavascriptInterface(new BkashJavaScriptInterface(BkashActivity.this), "KinYardsPaymentData");

            wvBkashPayment.loadUrl("https://zayansshop.000webhostapp.com/payment.php");   // api host link .

            wvBkashPayment.setWebViewClient(new CheckoutWebViewClient());
        }
    }


    private class CheckoutWebViewClient extends WebViewClient {

        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("External URL: ", url);
            if (url.equals("https://www.bkash.com/terms-and-conditions")) {
                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(myIntent);
                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(view.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            String paymentRequest = "{paymentRequest:" + request + "}";
            wvBkashPayment.loadUrl("javascript:callReconfigure(" + paymentRequest + " )");
            wvBkashPayment.loadUrl("javascript:clickPayButton()");
            progressBar.setVisibility(view.GONE);
        }

    }

    @Override
    public void onBackPressed() {
        popupPaymentCancelAlert();  //people can press backBtn and payment may cancel. so alert he really want to cancel payment or not
    }

    private void popupPaymentCancelAlert() {
        final AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("Want to cancel payment process?");
        alert.setCancelable(false);
        alert.setIcon(R.drawable.ic_launcher_background);
        alert.setTitle("Alert!");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(BkashActivity.this, "Payment canceled", Toast.LENGTH_SHORT).show();
                BkashActivity.super.onBackPressed();
            }
        });

        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        final AlertDialog alertDialog = alert.create();
        alertDialog.show();
    }


}

