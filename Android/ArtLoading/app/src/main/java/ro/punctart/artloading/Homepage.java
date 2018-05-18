package ro.punctart.artloading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import ro.punctart.artloading.Cont.ContMain;
import ro.punctart.artloading.DB.PunctArtDBHelper;

public class Homepage extends AppCompatActivity {
    //declarare lista
    ListView list;

    //vectori pentru id-urile folosite la query
    int[] idSpectacol;
    int[] idTeatru;

    //override-uri pentru homepage
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //apelare pentru info spectacole
        new getSpectacole().execute();

        //accesare lista
        list =(ListView) findViewById(R.id.listaSpectacole);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer i = new Integer(position);

                TextView t = (TextView)view.findViewById(R.id.teatru);
                TextView t2 = (TextView)view.findViewById(R.id.nume_piesa);
                TextView t3 = (TextView)view.findViewById(R.id.data_ora);

                //Toast.makeText(Homepage.this, "You Clicked at "+t.getText()+" "+t2.getText()+" "+t3.getText() + " " + idSpectacol[i] + " " + idTeatru[i], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(Homepage.this, PaginaSpectacol.class);
                intent.putExtra("numeTeatru", t.getText().toString());
                intent.putExtra("numeSpectacol",t2.getText().toString());

                intent.putExtra("qData",t3.getText().toString());
                intent.putExtra("qIdSpectacol",idSpectacol[i]);
                intent.putExtra("qIdTeatru",idTeatru[i]);

                Log.d("apas", String.valueOf(idSpectacol[i]));
                Log.d("apas", String.valueOf(idTeatru[i]));

                startActivity(intent);

                //finish();
            }
        });

        ///Button btnCont = (Button) findViewById(R.id.buttonAccount);
        ImageButton btnCont = (ImageButton) findViewById(R.id.buttonAccount);
        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Homepage.this, ContMain.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    //-----------

    //DATABASE QUERY
    public class getSpectacole extends AsyncTask<String, String, String>{
        ProgressDialog pdLoading = new ProgressDialog(Homepage.this);
        OkHttpClient client = new OkHttpClient();
        String url = "http://ip.costinsobaru.ro/_android/_scripts/homepage-viewSpectacole.php";

        //se executa pe GUI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        //se executa in background
        @Override
        protected String doInBackground(String... strings) {
            Request request;
            Response response;
            try {
                request = new Request.Builder().url(url).build();
                response = client.newCall(request).execute();
                return response.body().string();
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                return "urlerror";
            }
            catch (IOException e) {
                e.printStackTrace();
                return "ioerror";
            }
        }

        //se executa pe GUI
        @Override
        protected void onPostExecute(String result) {
            //super.onPostExecute(s);
            pdLoading.dismiss();

            //pentru erori de conectare
            if (result.equalsIgnoreCase("urlerror") || result.equalsIgnoreCase("ioerror")) {
                Toast.makeText(Homepage.this, "Eroare de conectare!", Toast.LENGTH_LONG).show();
            }

            //pentru succes
            try {
                JSONArray jsonArray = new JSONArray(result);
                Integer JSONArraySize = jsonArray.length();

                //id-uri
                idSpectacol = new int[JSONArraySize];
                idTeatru = new int[JSONArraySize];

                String[] numeSpectacol = new String[JSONArraySize];
                String[] numeTeatru = new String[JSONArraySize];
                String[] data = new String[JSONArraySize];
                String[] path = new String[JSONArraySize];
                String[] nota = new String[JSONArraySize];

                for (int i = 0; i < JSONArraySize; i++) {
                    JSONObject jsonobject = jsonArray.getJSONObject(i);

                    idSpectacol[i] = jsonobject.getInt("IdSpectacol");
                    idTeatru[i] = jsonobject.getInt("IdTeatru");

                    numeSpectacol[i] = jsonobject.getString("NumeSpectacol");
                    numeTeatru[i] = jsonobject.getString("NumeTeatru");
                    data[i] = jsonobject.getString("DataSpectacol");
                    path[i] = jsonobject.getString("PathPoza");
                    nota[i] = jsonobject.getString("NotaSpectacol");
                }

                //creare lista
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
