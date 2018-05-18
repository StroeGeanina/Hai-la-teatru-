package ro.punctart.artloading.Cont;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Arrays;

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
 * Created by Costin on 29.05.2017.
 */

public class ContMain extends AppCompatActivity {

    Button loginBtn, createAccountBtn, facebookLoginBtn;
    Intent intent;

    LoginButton login_facebook_button;
    CallbackManager callbackManager;

    protected void onCreate(Bundle savedInstanceState) {
        PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
        final boolean isLogged = db.savedLogin();

        super.onCreate(savedInstanceState);

        if(isLogged == false) {
            FacebookSdk.sdkInitialize(getApplicationContext());
            setContentView(R.layout.cont_main);

            loginBtn = (Button) findViewById(R.id.loginBtn);
            loginBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(ContMain.this, Login.class);
                    startActivity(i);
                    finish();
                }
            });

            createAccountBtn = (Button) findViewById(R.id.createAccountBtn);
            createAccountBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent i = new Intent(ContMain.this, Inregistrare.class);
                    startActivity(i);
                    finish();
                }

            });


            //fb
            callbackManager = CallbackManager.Factory.create();
            login_facebook_button = (LoginButton) findViewById(R.id.loginButton);
            login_facebook_button.setReadPermissions(Arrays.asList("public_profile", "email"));
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    String accessToken = loginResult.getAccessToken().getToken();
                    Log.i("accessToken", accessToken.toString());
                    GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                        @Override
                        public void onCompleted(JSONObject object, GraphResponse response) {
                            Log.i("LoginActivity", response.toString());
                            // Get facebook data from login
                            Bundle bFacebookData = getFacebookData(object);

                            //data
                            String id = bFacebookData.getString("id");
                            String email = bFacebookData.getString("email");
                            String firstName = bFacebookData.getString("first_name");
                            String lastName = bFacebookData.getString("last_name");

                            PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
                            if(db.savedLogin() == false) {
                                db.insert(firstName, lastName, email, "asd");
                                Intent i = new Intent(ContMain.this, Homepage.class);
                                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                i.putExtra("EXIT", true);
                                startActivity(i);

                                finish();
                            }
                        }
                    });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id, first_name, last_name, email"); // Parámetros que pedimos a facebook
                    request.setParameters(parameters);
                    request.executeAsync();
                    //txt.setText("login Status"+loginResult.getAccessToken());
                }

                @Override
                public void onCancel() {
                }

                @Override
                public void onError(FacebookException error) {
                }
            });



        }
        else {
            Intent i = new Intent(ContMain.this, InfoCont.class);
            startActivity(i);
            finish();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private Bundle getFacebookData(JSONObject object) {

        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");


            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));


            return bundle;
        } catch (JSONException e) {
            Log.d("error", "Error parsing JSON");
        }
        return null;
    }



    //-----------


}