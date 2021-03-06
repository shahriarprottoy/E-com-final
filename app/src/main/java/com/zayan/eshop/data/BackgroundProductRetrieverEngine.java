package com.zayan.eshop.data;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.view.View;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatImageView;

import com.zayan.eshop.FilterMenu;
import com.zayan.eshop.MainActivity;
import com.zayan.eshop.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class BackgroundProductRetrieverEngine extends AsyncTask<String, Void, String> {

    @SuppressLint("StaticFieldLeak")
    private Activity context;
    private AdapterProduct mAdapter;
    @SuppressLint("StaticFieldLeak")
    private GridView productListView;
    @SuppressLint("StaticFieldLeak")
    private ProgressBar progressBar;
    @SuppressLint("StaticFieldLeak")
    private AppCompatImageView imageView;

    public BackgroundProductRetrieverEngine(Activity context, AdapterProduct mAdapter) {
        this.mAdapter = mAdapter;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        // Find a reference to the {@link ListView} in the layout
        productListView = context.findViewById(R.id.product_list);
        progressBar = context.findViewById(R.id.progressBar);
        imageView = context.findViewById(R.id.nothing);
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected String doInBackground(String... strings) {
        String retrievedData = "";
        String checkUrl = "https://zayansshop.000webhostapp.com/retrieveapp.php";
        URL url;
        try {
            url = new URL(checkUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            String data = URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.ISO_8859_1));
            String productDataReader;

            while ((productDataReader = bufferedReader.readLine()) != null) {
                //noinspection StringConcatenationInLoop
                retrievedData += productDataReader;
            }

            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
        } catch (IOException e) {
            // e.printStackTrace();
            progressBar.setVisibility(View.INVISIBLE);
            return "Connection error";
        }

        return retrievedData;
    }

    @Override
    protected void onPostExecute(String s) {
        // Hide progress bar
        progressBar.setVisibility(View.INVISIBLE);

        if (s.equalsIgnoreCase("Connection error")) {
            Toast.makeText(context,
                    "Could not connect. Please check network connection",
                    Toast.LENGTH_SHORT).show();
        } else {
            super.onPostExecute(s);
            MainActivity.products = new ArrayList<>();
            JSONArray jsonArray;
            try {
                jsonArray = new JSONArray(s);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONArray obj = jsonArray.getJSONArray(i);

                    MainActivity.products.add(new Product(
                            obj.getString(0),
                            obj.getInt(1),
                            obj.getInt(2),
                            obj.getString(3),
                            obj.getString(4),
                            obj.getString(5),
                            obj.getString(6),
                            obj.getInt(7),
                            obj.getInt(8),
                            obj.getInt(9),
                            obj.getInt(10)
                    ));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            for (int l = 0; l < MainActivity.products.size() ; l++){
                if (MainActivity.products.get(l).getStock() < 1){
                    MainActivity.products.remove(l);
                    l--;
                } else if (FilterMenu.filterFlag){
                    if(MainActivity.products.get(l).getDiscountedPrice() < FilterMenu.minimum
                            || MainActivity.products.get(l).getDiscountedPrice() > FilterMenu.maximum){
                        MainActivity.products.remove(l);
                        l--;
                    }
                }
            }

            // Create a new adapter that takes an empty list of Product as input
            mAdapter = new AdapterProduct(context, MainActivity.products);

            if (MainActivity.products.size() <= 0) {
                imageView.setVisibility(View.VISIBLE);
            } else {
                productListView.setVisibility(View.VISIBLE);
                productListView.setAdapter(mAdapter);
            }

        }
    }
}
