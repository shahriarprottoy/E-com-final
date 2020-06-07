package com.example.zayans_eshop.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.zayans_eshop.MainActivity;
import com.example.zayans_eshop.RegisterActivity;

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

    public BackgroundRegistrationEngine(Activity context){
        this.context = context;
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
           return "server crash";
        }

        return retrievedData;
    }

    @Override
    protected void onPostExecute(String s) {
        if (s.equals("Successful")) {
            Toast.makeText(context, "registration successful",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(context, MainActivity.class);
            context.startActivity(intent);
            Log.i("TESTING", s);
        }
        else if(s.equalsIgnoreCase("server crash")){
            Log.i("TESTING", s);
            Toast.makeText(context,
                    "sorry for the server crash",
                    Toast.LENGTH_LONG).show();
        }
         super.onPostExecute(s);
    }
}
