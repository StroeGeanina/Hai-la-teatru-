package ro.punctart.artloading;

/**
 * Created by Geanina on 10.04.2017.
 */

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class PaginaSpectacolTab2 extends Fragment implements OnMapReadyCallback {

    private FragmentActivity myContext;
    GoogleMap gm;

    String numeTeatru;
    double latTeatru, longTeatru;
    TextView tvNumeTeatru;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2despre, container, false);

        numeTeatru = getArguments().getString("infoTeatruNume");
        latTeatru = getArguments().getDouble("infoTeatruLat");
        longTeatru = getArguments().getDouble("infoTeatruLong");

        tvNumeTeatru = (TextView) rootView.findViewById(R.id.textView7);
        tvNumeTeatru.setText("Teatrul " + numeTeatru);

        //Log.d("gpsx", String.valueOf(latTeatru) + " " + String.valueOf(longTeatru))

        if (gm == null) {
            SupportMapFragment mapFrag = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            mapFrag.getMapAsync(this);
        }

        return rootView;
    }

    @Override
    public void onAttach(Activity act)
    {
        myContext = (FragmentActivity) act;
        super.onAttach(act);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        final LatLng sydney = new LatLng(latTeatru, longTeatru);
        final double lat  = sydney.latitude;
        final double lng = sydney.longitude;
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title(numeTeatru));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
        googleMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?q=loc:" + lat+","+lng + "("+numeTeatru+")" ));

                //Uri.parse("http://maps.google.com/maps?q=loc:" + sydney.toString() + " (Teatrul Excelsior+")");
                startActivity(intent);
            }
        });
    }
}
