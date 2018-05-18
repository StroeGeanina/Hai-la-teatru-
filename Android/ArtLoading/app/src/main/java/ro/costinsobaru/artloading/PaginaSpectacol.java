package ro.costinsobaru.artloading;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class PaginaSpectacol extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    //public static final String message ;
    //public static final String message2 ;
    //public static final String message3 ;
/*
    public String getM1() {
        return message;
    }
    public String getM2() {
        return message2;
    }
    public String getM3() {
        return message3;
    }
*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagina_spectacol);


      //  TextView textView =(TextView) findViewById(R.id.textView4);
       // TextView textView2 =(TextView) findViewById(R.id.textView5);
       // TextView textView3 =(TextView) findViewById(R.id.textView6);



        Intent intent = getIntent();
        String message = intent.getStringExtra("numeTeatru");
        String message2 = intent.getStringExtra("numeSpectacol");
        String message3 = intent.getStringExtra("data");


        Bundle bundle = new Bundle();
        bundle.putString("mesaj", "m-am tirmis");


        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabTextColors(Color.GRAY, Color.BLACK);

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
                    Tab1Descriere tab1 = new Tab1Descriere();

                    return tab1;
                case 1:
                    Tab2Despre tab2 = new Tab2Despre();
                    return tab2;
                case 2:
                    Tab3Opinii tab3 = new Tab3Opinii();
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
                    return "Despre";
                case 2:
                    return "Opinii";
            }
            return null;
        }
    }

}
