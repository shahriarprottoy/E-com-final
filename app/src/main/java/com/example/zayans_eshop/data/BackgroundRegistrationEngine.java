package com.example.zayans_eshop.data;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.Toast;

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

public class BackgroundRegistrationEngine extends AsyncTask<String, Void, String> {

    private Activity context;
    private Button submit;

    public BackgroundRegistrationEngine(Activity context, Button submit) {
        this.context = context;
        this.submit = submit;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected String doInBackground(String... strings) {
        String retrievedData = "";
        String registerUrl = "https://zayansshop.000webhostapp.com/register.php";
        URL url;
        try {
            url = new URL(registerUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));

            String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(strings[0], "UTF-8") + "&" +
                    URLEncoder.encode("userpass", "UTF-8") + "=" + URLEncoder.encode(strings[1], "UTF-8") + "&" +
                    URLEncoder.encode("phone", "UTF-8") + "=" + URLEncoder.encode(strings[2], "UTF-8") + "&" +
                    URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(strings[3], "UTF-8") + "&" +
                    URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(strings[4], "UTF-8");
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
            submit.setEnabled(true);
           return "server crash";
        }

        return retrievedData;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equalsIgnoreCase("Successful")) {
            Toast.makeText(context, "registration successful",
                    Toast.LENGTH_LONG).show();

            context.onBackPressed();
        } else if (s.equalsIgnoreCase("server crash")) {
            Toast.makeText(context,
                    "An unknown error occurred",
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context,
                    "Connection problem! Please check network connection",
                    Toast.LENGTH_LONG).show();
        }
         super.onPostExecute(s);
    }
}
