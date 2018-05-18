package ro.punctart.artloading.Cont;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ro.punctart.artloading.DB.PunctArtDBHelper;
import ro.punctart.artloading.Homepage;
import ro.punctart.artloading.R;
import ro.punctart.artloading.SplashActivity;

/**
 * Created by dg on 5/19/2017.
 */

public class Login extends AppCompatActivity {

    EditText emailET, parolaET;
    Button loginBtn;

    String email, parola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        emailET = (EditText) findViewById(R.id.emailET);
        parolaET = (EditText) findViewById(R.id.passwordET);

        loginBtn = (Button)findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                email= emailET.getText().toString();
                parola = parolaET.getText().toString();

                //verifica daca exista contul in baza de date
                new Check().execute(email, parola);
                //redirectioneaza catre pagina spectacole
            }
        });
    }

    class Check extends AsyncTask<String, String, String> {
        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "http://ip.costinsobaru.ro/_android/_scripts/checkLogin.php";
        String uEmail;
        String uPassword;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            uEmail = params[0];
            uPassword = params[1];

            RequestBody requestBody;

            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("email", params[0])
                    .addFormDataPart("password", params[1])
                    .build();

            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseText = response.body().string();
                Log.d("login", "response: " + responseText);
                return responseText;
            }
            catch (IOException e) {
                e.printStackTrace();
                return "error";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(result.equalsIgnoreCase("0")) {
                Toast.makeText(Login.this, "Datele nu sunt corecte.", Toast.LENGTH_LONG).show();

                PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
                db.delete();
            }
            else if (result.equalsIgnoreCase("error")) {
                Toast.makeText(Login.this, "Eroare de conectare.", Toast.LENGTH_LONG).show();
            }
            else //if(result.matches("^[0-9]*$"))
            {
                String nume, prenume;
                try {
                    JSONObject obj = new JSONObject(result);
                    nume = obj.getString("nume");
                    prenume = obj.getString("prenume");

                    PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
                    db.insert(nume, prenume, uEmail, uPassword);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(Login.this, InfoCont.class);
                startActivity(intent);
                finish();
            }

        }
    }
}
