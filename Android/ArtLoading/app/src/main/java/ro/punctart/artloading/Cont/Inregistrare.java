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

public class Inregistrare extends AppCompatActivity {

    Button createAccountBtn;

    EditText etNume, etPrenume, etEmail, etParola, etConfParola;
    String nume,prenume,email,parola,confParola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inregistrare);

        etNume = (EditText) findViewById(R.id.lastNameET);
        etPrenume = (EditText) findViewById(R.id.firstNameET);
        etEmail = (EditText) findViewById(R.id.emailET);
        etParola = (EditText) findViewById(R.id.passwordET);
        etConfParola = (EditText) findViewById(R.id.confPassET);

        createAccountBtn = (Button)findViewById(R.id.newAccountBtn);
        createAccountBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nume = String.valueOf(etNume.getText());
                prenume = String.valueOf(etPrenume.getText());
                email = String.valueOf(etEmail.getText());
                parola = String.valueOf(etParola.getText());
                confParola = String.valueOf(etConfParola.getText());

                if( nume.isEmpty() || prenume.isEmpty() || email.isEmpty() || parola.isEmpty() || confParola.isEmpty() )
                {
                    Toast toast = Toast.makeText(getApplicationContext(), "Toate câmpurile trebuie completate!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else if(!parola.equals(confParola)){
                    Toast toast = Toast.makeText(getApplicationContext(), "Parolele nu coincid!", Toast.LENGTH_SHORT);
                    toast.show();
                }

                else {
                    //Toast toast = Toast.makeText(getApplicationContext(), "Contul a fost creat.", Toast.LENGTH_SHORT);
                    //toast.show();

                    new registerUser().execute(nume, prenume, email, parola);

                    //Intent i=new Intent(Inregistrare.this, Login.class);
                    //startActivity(i);
                    //finish();
                }

            }
        });


    }

    class registerUser extends AsyncTask<String, String, String> {
        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "http://ip.costinsobaru.ro/_android/_scripts/registerUser.php";
        String uNume, uPrenume, uEmail, uPassword;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            uNume = params[0];
            uPrenume = params[1];
            uEmail = params[2];
            uPassword = params[3];

            RequestBody requestBody;

            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("nume", params[0])
                    .addFormDataPart("prenume", params[1])
                    .addFormDataPart("email", params[2])
                    .addFormDataPart("parola", params[3])
                    .build();

            Request request = new Request.Builder().url(url).post(requestBody).build();
            Response response = null;
            try {
                response = client.newCall(request).execute();
                String responseText = response.body().string();
                Log.d("register", responseText);
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

            if(result.equalsIgnoreCase("-1")) {
                Toast.makeText(Inregistrare.this, "Eroare.", Toast.LENGTH_LONG).show();
            }
            else if (result.equalsIgnoreCase("1")) {
                Toast.makeText(Inregistrare.this, "E-mail-ul deja exista in baza de date.", Toast.LENGTH_LONG).show();
            }
            else if(result.equalsIgnoreCase("0"))
            {
                Toast.makeText(Inregistrare.this, "Utilizatorul a fost creat.", Toast.LENGTH_LONG).show();

                PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
                db.insert(uNume, uPrenume, uEmail, uPassword);

                Intent i = new Intent(Inregistrare.this, Homepage.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                startActivity(i);

                finish();
            }

        }
    }
}
