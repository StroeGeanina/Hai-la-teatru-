package ro.punctart.artloading;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ro.punctart.artloading.DB.PunctArtDB;
import ro.punctart.artloading.DB.PunctArtDBHelper;

/**
 * Created by Costin on 29.05.2017.
 */

public class SplashActivity extends AppCompatActivity{
    private final int SPLASH_DISPLAY_LENGTH = 1500;
    String email;
    String parola;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splash_screen);

        PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
        final boolean isLogged = db.savedLogin();
        email = db.getEmail();
        parola = db.getPass();

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
            if(isLogged == true) {
                new Check().execute(email, parola);
            }
            else {
                Intent intent = new Intent(SplashActivity.this, Homepage.class);
                startActivity(intent);
                finish();
            }
            }
        }, SPLASH_DISPLAY_LENGTH);
    }

    class Check extends AsyncTask<String, String, String> {
        OkHttpClient client = new OkHttpClient();
        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String url = "http://ip.costinsobaru.ro/_android/_script/checkLogin.php";
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
                Toast.makeText(SplashActivity.this, "Datele salvate au fost schimbate.", Toast.LENGTH_LONG).show();

                PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
                db.delete();

                Intent intent = new Intent(SplashActivity.this, Homepage.class);
                startActivity(intent);
                finish();
            }
            else if (result.equalsIgnoreCase("error")) {
                Toast.makeText(SplashActivity.this, "Eroare de conectare.", Toast.LENGTH_LONG).show();
            }
            else //if(result.matches("^[0-9]*$"))
            {
                Intent intent = new Intent(SplashActivity.this, Homepage.class);
                startActivity(intent);
                finish();
            }

        }
    }

}
