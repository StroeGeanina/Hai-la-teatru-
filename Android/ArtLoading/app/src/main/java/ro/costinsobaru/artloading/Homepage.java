package ro.costinsobaru.artloading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static ro.costinsobaru.artloading.R.id.teatru;

public class Homepage extends AppCompatActivity {
    //pentru conextiune
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    //declarare campuri TextView
    private TextView tvNumeSpectacol;
    private TextView tvNumeTeatru;
    private TextView tvData;
    private TextView tvNota;
    private TextView tvPath;

    //declarare lista
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //referinta la variabile
       // tvNumeSpectacol = (TextView) findViewById(R.id.tvNumeSpectacol);
        //tvNumeTeatru = (TextView) findViewById(R.id.tvNumeTeatru);
        //tvData = (TextView) findViewById(R.id.tvData);
        //tvNota = (TextView) findViewById(R.id.tvNota);
        //tvPath = (TextView) findViewById(R.id.tvPath);

        //pornire thread
        new AsyncSpectacole().execute();

        //actualizez lista


        //CustomList adapter = new CustomList(Homepage.this , item , imageId);
        list =(ListView) findViewById(R.id.listaSpectacole);
        //list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer i = new Integer(position);

                String boy;
             //   Cursor c = (Cursor) parent.getItemAtPosition(position);
           //     c.moveToFirst();
                TextView t = (TextView)view.findViewById(R.id.teatru);
                TextView t2 = (TextView)view.findViewById(R.id.nume_piesa);
                TextView t3 = (TextView)view.findViewById(R.id.data_ora);

                Toast.makeText(Homepage.this, "You Clicked at "+t.getText()+" "+t2.getText()+" "+t3.getText(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Homepage.this, PaginaSpectacol.class);
                intent.putExtra("numeTeatru",t.getText().toString());
                intent.putExtra("numeSpectacol",t2.getText().toString());
                intent.putExtra("data",t3.getText().toString());
                startActivity(intent);
                //finish();
            }
        });

    }

    private class AsyncSpectacole extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(Homepage.this);
        HttpURLConnection conn;
        URL url = null;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //ruleaza pe thread-ul UI
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                url = new URL("http://ip.costinsobaru.ro/_android/_scripts/homepage-viewSpectacole.php");
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEOUT);
                conn.setDoOutput(true);
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
            pdLoading.dismiss();

            if (result.equalsIgnoreCase("exception") || result.equalsIgnoreCase("unsuccessful")) {
                Toast.makeText(Homepage.this, "Eroare de conectare!", Toast.LENGTH_LONG).show();
            }

            try {
                JSONArray jsonArray = new JSONArray(result);
                Integer JSONArraySize = jsonArray.length();

                String[] numeSpectacol = new String[JSONArraySize];
                String[] numeTeatru = new String[JSONArraySize];
                String[] data = new String[JSONArraySize];
                String[] path = new String[JSONArraySize];
                String[] nota = new String[JSONArraySize];


                for (int i = 0; i < JSONArraySize; i++) {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);

                    Integer idSpectacol = jsonobject.getInt("IdSpectacol");

                    numeSpectacol[i] = jsonobject.getString("NumeSpectacol");
                    numeTeatru[i] = jsonobject.getString("NumeTeatru");
                    data[i] = jsonobject.getString("DataSpectacol");
                    path[i] = jsonobject.getString("PathPoza");
                    nota[i] = jsonobject.getString("NotaSpectacol");

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

                }

                HomepageCustomList adapter = new HomepageCustomList(Homepage.this, numeSpectacol, numeTeatru, data, nota, path);
                list =(ListView) findViewById(R.id.listaSpectacole);
                list.setAdapter(adapter);

                Toast.makeText(Homepage.this, "Fluxul de date a fost încărcat.", Toast.LENGTH_LONG).show();

            }
            catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }

}

