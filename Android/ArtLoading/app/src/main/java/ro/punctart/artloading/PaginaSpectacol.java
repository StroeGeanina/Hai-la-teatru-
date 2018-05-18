package ro.punctart.artloading;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.ExecutionException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import ro.punctart.artloading.Cont.ContMain;

public class PaginaSpectacol extends AppCompatActivity {
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    Bundle bTab1 = new Bundle();
    Bundle bTab2 = new Bundle();
    Bundle bTab3 = new Bundle();

    String qData; int qIdSpectacol, qIdTeatru;
    String numeTeatru, numeSpectacol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        //primirea informatiilor din homepage
        Intent intent = getIntent();
        numeTeatru = intent.getStringExtra("numeTeatru");
        numeSpectacol = intent.getStringExtra("numeSpectacol");

        qData = intent.getStringExtra("qData");
        qIdSpectacol = intent.getIntExtra("qIdSpectacol", 0);
        qIdTeatru = intent.getIntExtra("qIdTeatru", 0);



        AsyncTask task = new getDetails().execute();
        //executaa();;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_spectacol);

//        setContentView(R.layout.activity_pagina_spectacol);
//
//
//
//        // Create the adapter that will return a fragment for each of the three
//        // primary sections of the activity.
//        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
//
//        // Set up the ViewPager with the sections adapter.
//        mViewPager = (ViewPager) findViewById(R.id.container);
//        mViewPager.setAdapter(mSectionsPagerAdapter);
//
//        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
//        tabLayout.setupWithViewPager(mViewPager);
//        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);

        ImageButton btnCont = (ImageButton) findViewById(R.id.buttonAccount);
        btnCont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PaginaSpectacol.this, ContMain.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //deleted PlaceHolderFragment  class from here

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    // set Fragmentclass Arguments
                    PaginaSpectacolTab1 tab1 = new PaginaSpectacolTab1();
                    tab1.setArguments(bTab1);
                    return tab1;
                case 1:
                    PaginaSpectacolTab2 tab2 = new PaginaSpectacolTab2();
                    tab2.setArguments(bTab2);
                    return tab2;
                case 2:
                    PaginaSpectacolTab3 tab3 = new PaginaSpectacolTab3();
                    tab3.setArguments(bTab3);
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Descriere";
                case 1:
                    return "Locație";
                case 2:
                    return "Opinii";
            }
            return null;
        }
    }


    //DATABASE QUERY
    public class getDetails extends AsyncTask<String, String, String> {
        ProgressDialog pdLoading = new ProgressDialog(PaginaSpectacol.this);
        OkHttpClient client = new OkHttpClient();
        String url = "http://ip.costinsobaru.ro/_android/_scripts/paginaSpectacol.php";

        //se executa pe GUI
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tLoading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            Request request;
            RequestBody requestBody;
            Response response;

            requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("idSpectacol", String.valueOf(qIdSpectacol))
                    .addFormDataPart("idTeatru", String.valueOf(qIdTeatru))
                    .addFormDataPart("data", qData)
                    .build();

            request = new Request.Builder().url(url).post(requestBody).build();
            response = null;
            try {
                response = client.newCall(request).execute();
                String responseText = response.body().string();
                return responseText;
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

        @Override
        protected void onPostExecute(String result) {
            //pentru erori de conectare
            if (result.equalsIgnoreCase("urlerror") || result.equalsIgnoreCase("ioerror")) {
                Toast.makeText(PaginaSpectacol.this, "Eroare de conectare!", Toast.LENGTH_LONG).show();
            }

            try {
                JSONObject obj = new JSONObject(result);

                JSONObject jsonInfo = new JSONObject(String.valueOf(obj.getJSONObject("info")));
                JSONArray jsonDistributie = new JSONArray(String.valueOf(obj.getJSONArray("distributie")));
                JSONArray jsonCritici = new JSONArray(String.valueOf(obj.getJSONArray("critici")));
                JSONArray jsonComentarii = new JSONArray(String.valueOf(obj.getJSONArray("comentarii")));
                JSONArray jsonGalerie = new JSONArray(String.valueOf(obj.getJSONArray("galerie")));

                //tab1
                bTab1.putString("infoSpecNume", numeSpectacol);
                bTab1.putString("infoTeatruNume", numeTeatru);
                bTab1.putString("infoSpecData", qData);

                //bTab1.putString("infoSpecDesc", (String) jsonInfo.get("specDesc"));
                bTab1.putString("infoSpecTrailer", (String) jsonInfo.get("specTrailer"));
                bTab1.putDouble("infoSpecNota", Double.valueOf(String.valueOf(jsonInfo.get("specNota"))));
                bTab1.putString("infoSpecPoster", (String) jsonInfo.get("specPoster"));

                bTab1.putString("infoSpecDesc", (String) jsonInfo.get("specDesc"));

                //tab2
                bTab2.putString("infoSpecNume", numeSpectacol);
                bTab1.putString("distributie", jsonDistributie.toString());
                //Log.d("actx", jsonDistributie.toString());

                bTab2.putString("infoTeatruNume", numeTeatru);
                bTab2.putDouble("infoTeatruLat", Double.valueOf(String.valueOf(jsonInfo.get("teatruLat"))));
                bTab2.putDouble("infoTeatruLong", Double.valueOf(String.valueOf(jsonInfo.get("teatruLong"))));


                //tab3
                bTab3.putString("critici", jsonCritici.toString());
                bTab3.putString("comentarii", jsonComentarii.toString());

                Log.d("asd", jsonCritici.toString());
                Log.d("asd", jsonComentarii.toString());
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

//            setContentView(R.layout.activity_pagina_spectacol);



            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(mViewPager);
            tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);

            pdLoading.dismiss();

        }


    }
}
