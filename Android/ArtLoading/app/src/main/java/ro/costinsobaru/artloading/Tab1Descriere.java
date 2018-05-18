package ro.costinsobaru.artloading;

/**
 * Created by Geanina on 10.04.2017.
 */

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.net.MalformedURLException;
import java.net.URL;

import ro.costinsobaru.artloading.PaginaSpectacol;

public class Tab1Descriere extends Fragment {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    private TextView tvNumeSpectacol;
    private TextView tvNumeTeatru;
    private TextView tvData;
    PaginaSpectacol ob = new PaginaSpectacol();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1descriere, container, false);

        tvNumeSpectacol = (TextView) rootView.findViewById(R.id.nume);
        tvNumeTeatru = (TextView) rootView.findViewById(R.id.teatru);
        tvData = (TextView) rootView.findViewById(R.id.data);


      //  Bundle x = savedInstanceState.getBundle("mesaj");
//        String TEST = getArguments().getString("mesaj");

       // tvNumeTeatru.setText(TEST);

        new AsyncTab1().execute("","","");

        //return rootView;
        return inflater.inflate(R.layout.tab1descriere, container, false);
    }

    //------------------
    private class AsyncTab1 extends AsyncTask<String, String, String> {
       // ProgressDialog pdLoading = new ProgressDialog(Tab1Descriere.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //ruleaza pe thread-ul UI
           // pdLoading.setMessage("\tLoading...");
            //pdLoading.setCancelable(false);
            //pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL("http://ip.costinsobaru.ro/_android/_scripts/homepage-viewSpectacole.php");
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);

                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);




                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("postTeatru", params[0])
                        .appendQueryParameter("postSpectacol", params[1])
                        .appendQueryParameter("postData", params[2]);
                String query = builder.build().getEncodedQuery();

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                conn.connect();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                return "exception";
            }
            catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }

            try {
                int response_code = conn.getResponseCode();
                // Check if successful connection made
                if (response_code == HttpURLConnection.HTTP_OK) {
                    // Read data sent from server
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;

                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    // Pass data to onPostExecute method
                    return (result.toString());
                }
                else {
                    return "unsuccessful";
                }

            }
            catch (IOException e) {
                e.printStackTrace();
                return "exception";
            }
            finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String result) {
            //this method will be running on UI thread
            //pdLoading.dismiss();

            if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                //Toast.makeText(Tab1Descriere.this, "Eroare de conectare!", Toast.LENGTH_LONG).show();
            }

            try {
                JSONArray jsonArray = new JSONArray(result);
                Integer JSONArraySize = jsonArray.length();

                String descriereSpectacol = new String();
                String pozaSpectacol = new String();
                String trailerSpectacol = new String();

                //for (int i = 0; i < JSONArraySize; i++) {
                    JSONObject jsonobject = jsonArray.getJSONObject(1);

                    descriereSpectacol = jsonobject.getString("NumeSpectacol");
                    pozaSpectacol = jsonobject.getString("NumeTeatru");
                    trailerSpectacol = jsonobject.getString("DataSpectacol");

                    // Log.d("for", "-------");
                    //Log.d("for", idSpectacol.toString());
                    //Log.d("for", numeSpectacol);
                    //Log.d("for", numeTeatru);
                    //Log.d("for", data);
                    //Log.d("for", path);
                    //Log.d("for", nota);

//                    tvNumeSpectacol.setText(numeSpectacol);
                    //                  tvNumeTeatru.setText(numeTeatru);
                    //                tvData.setText(data);
                    //              tvNota.setText(nota);
                    //            tvPath.setText(path);



                    //getView(i);

              //  }

              //  HomepageCustomList adapter = new HomepageCustomList(Homepage.this, numeSpectacol, numeTeatru, data, nota, path);
             //   list =(ListView) findViewById(R.id.listaSpectacole);
             //   list.setAdapter(adapter);

                Log.d("hi", "sunt aici");

                tvData.setText("MERG");

                //tvData.setText(ob.getM3());
                //tvNumeSpectacol.setText(ob.getM2());
//                tvNumeTeatru.setText(PaginaSpectacol.message2);

               // Toast.makeText(Homepage.this, "Fluxul de date a fost încărcat.", Toast.LENGTH_LONG).show();

            }
            catch (JSONException e) {
                e.printStackTrace();
                Log.d("catch", "m-am busit");
            }


        }
    }



    //------------------
}
