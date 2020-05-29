package com.example.zayans_eshop;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.zayans_eshop.data.Product;
import com.example.zayans_eshop.data.ProductList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import java.util.List;

public class electronics extends AppCompatActivity {
    ArrayList<Product> products = new ArrayList<>();
    private ProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.electronics_layout);

        // Find a reference to the {@link ListView} in the layout
        ListView productListView = (ListView) findViewById(R.id.electronics_list);

        // Create a new adapter that takes an empty list of Product as input
        mAdapter = new ProductAdapter(this , (ArrayList<Product>) products);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        productListView.setAdapter(mAdapter);
        DbRetriever retriever = new DbRetriever();
        retriever.execute();
    }


    public class DbRetriever extends AsyncTask<String, Void, String> {

        @SuppressLint("StaticFieldLeak")
        private ProgressDialog progressDialog;

      //  DbRetriever() {
       //     progressDialog = new ProgressDialog(context);
      //      progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      //  }

        @Override
        protected void onPreExecute() {
            progressDialog.setTitle("Querying");
            progressDialog.setMessage("Getting products from server");
            progressDialog.show();
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
                progressDialog.setMessage("Connecting to server.");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

                String data = URLEncoder.encode("category", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8");
                progressDialog.setMessage("Querying category.");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                progressDialog.setMessage("Getting result.");
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
                e.printStackTrace();
            }

            return retrievedData;
        }

         @Override
        protected void onPostExecute(String s) {

            try {
                home__fragment.jsonArray = new JSONArray(s);
                MainActivity.productList = new ProductList(4);
                for (int i = 0; i < home__fragment.jsonArray.length(); i++) {
                    JSONObject obj = home__fragment.jsonArray.getJSONObject(i);
                            String name= obj.getString("0");
                            int price= obj.getInt("1");
                            int stock= obj.getInt("2");
                           String image1=obj.getString("4");
                           String image2=obj.getString("5");
                            String image3=obj.getString("6");
                       Product product = new Product(name,price,stock,image1,image2,image3);
                       products.add(product);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

           if (progressDialog.isShowing())
               progressDialog.dismiss();
           super.onPostExecute(s);

        }
    }
}