package ro.punctart.artloading;

/**
 * Created by Geanina on 10.04.2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaginaSpectacolTab1 extends Fragment {
    public static final int CONNECTION_TIMEOUT=10000;
    public static final int READ_TIMEOUT=15000;

    private TextView tvNumeSpectacol;
    private TextView tvNumeTeatru;
    private TextView tvData, tvDescriereSpectacol, tvOra;

    private ImageView ivPoster;

    private FragmentActivity myContext;
    private YouTubePlayer YPlayer;
    private static final String YoutubeDeveloperKey = "AIzaSyBx7v0YOb140fDO7EbfMx4l87raxezDWFw";
    private static final int RECOVERY_DIALOG_REQUEST = 1;

    private GridView gridView;
    private GridViewAdapter gridAdapter;

    JSONArray distributie;

    @Override
    public void onAttach(Activity activity) {

        if (activity instanceof FragmentActivity) {
            myContext = (FragmentActivity) activity;
        }

        super.onAttach(activity);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.tab1descriere, container, false);

        String numeSpectacol = getArguments().getString("infoSpecNume");
        tvNumeSpectacol = (TextView) rootView.findViewById(R.id.nume);
        tvNumeSpectacol.setText(numeSpectacol);

        String dataSpectacol = getArguments().getString("infoSpecData");
        String year, month, day, hour, minute;
        String[] timestamp, dataV, timeV;
        timestamp = dataSpectacol.split(" ");
        dataV = timestamp[0].split("-");
        timeV = timestamp[1].split(":");

        year = dataV[0];
        month = dataV[1];
        day = dataV[2];

        hour = timeV[0];
        minute = timeV[1];
        tvData = (TextView) rootView.findViewById(R.id.data);
        tvOra = (TextView) rootView.findViewById(R.id.ora);
        tvData.setText(day + "." + month + "." + year);
        tvOra.setText(hour + ":" + minute);


        String posterSpectacol = getArguments().getString("infoSpecPoster");
        ivPoster = (ImageView) rootView.findViewById(R.id.poster);
        Picasso.with(getContext()).load(posterSpectacol).into(ivPoster);

        String descriereSpectacol = getArguments().getString("infoSpecDesc");
        tvDescriereSpectacol = (TextView) rootView.findViewById(R.id.descriere);
        tvDescriereSpectacol.setText(descriereSpectacol);
        //Log.d("xcv", descriereSpectacol);

        final String url = getArguments().getString("infoSpecTrailer");

        if(!url.isEmpty()) {

            YouTubePlayerSupportFragment youTubePlayerFragment = YouTubePlayerSupportFragment.newInstance();
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            transaction.add(R.id.youtube_fragment, youTubePlayerFragment).commit();

            youTubePlayerFragment.initialize("DEVELOPER_KEY", new YouTubePlayer.OnInitializedListener() {

                @Override
                public void onInitializationSuccess(YouTubePlayer.Provider arg0, YouTubePlayer youTubePlayer, boolean b) {
                    if (!b) {
                        YPlayer = youTubePlayer;
                        //YPlayer.setFullscreen(true);
                        //YPlayer.cueVideo("2zNSgSzhBfM",1);

                        //parser link
                        String urlId = "";
                        String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
                        Pattern compiledPattern = Pattern.compile(pattern);
                        Matcher matcher = compiledPattern.matcher(url);
                        if(matcher.find()) {
                            urlId = matcher.group();
                        }
                        YPlayer.cueVideo(urlId, 1);
                    }
                }

                @Override
                public void onInitializationFailure(YouTubePlayer.Provider arg0, YouTubeInitializationResult arg1) {
                    // TODO Auto-generated method stub
                }
            });
        }
        else
        {}


        try {
            String jsonDistri = getArguments().getString("distributie");
            distributie = new JSONArray(jsonDistri);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        if(getData() != null) {
            gridView = (GridView) rootView.findViewById(R.id.gridView);
            gridAdapter = new GridViewAdapter(getContext(), R.layout.grid_item_layout, getData());
            gridView.setAdapter(gridAdapter);

            gridView.setEnabled(false);
            gridView.setVerticalScrollBarEnabled(false);


            int totalHeight = 0;
            int items = gridAdapter.getCount();
            int rows = 0;
            View listItem = gridAdapter.getView(0, null, gridView);
            listItem.measure(0, 0);
            totalHeight = listItem.getMeasuredHeight();

            int columns = 3;
            float x = 1;
            if( items > columns ){
                x = items/columns;
                rows = (int) (x + 1);
                totalHeight *= rows;
            }

            ViewGroup.LayoutParams params = gridView.getLayoutParams();
            params.height = totalHeight;
            gridView.setLayoutParams(params);

            /*
            gridView.setOnTouchListener(new View.OnTouchListener(){

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    return event.getAction() == MotionEvent.ACTION_MOVE;
                }

            });
            */
        }
       // getData();


        return rootView;
    }

    //------------------
    private ArrayList<ImageItem> getData() {
        final ArrayList<ImageItem> imageItems = new ArrayList<>();

        if(distributie.length() == 0) return null;

        Log.d("lenj", String.valueOf(distributie.length()));

        for (int i = 0; i < distributie.length(); i++) {
            try {
                JSONObject set = (JSONObject) distributie.get(i);
                String actorNume = set.getString("nume");
                String actorPoza = set.getString("poza");
             //   Log.d("actx", actorNume + " " + actorPoza);
                imageItems.add(new ImageItem(actorPoza, actorNume));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }

            //       String url =
     //       imageItems.add(new ImageItem(bitmap, "Image#" + i));
        }

        return imageItems;

        //adaugam un arraylist, toti actorii, adica URL+NUME
     //   return imageItems;
    }



    //------------------
}
