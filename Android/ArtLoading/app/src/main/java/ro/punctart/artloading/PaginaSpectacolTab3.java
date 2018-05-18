package ro.punctart.artloading;

/**
 * Created by Geanina on 10.04.2017.
 */

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaginaSpectacolTab3 extends Fragment {

    ListView criticiLV;
    CriticiCustomList adapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab3opinii, container, false);


        try {
            String criticiAux = getArguments().getString("critici");
            JSONArray critici = new JSONArray(criticiAux);
            String[] nume = new String[critici.length()], text = new String[critici.length()];

            Log.d("json", criticiAux);

            for (int i = 0; i < critici.length(); i++) {
                JSONObject obj = (JSONObject) critici.get(i);
                nume[i] = obj.getString("nume");
                text[i] = obj.getString("text");
                Log.d("logz", nume[i]);
            }

            criticiLV = (ListView) rootView.findViewById(R.id.list);
            adapter = new CriticiCustomList(getActivity(), nume, text);
            criticiLV.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return rootView;
    }
}
