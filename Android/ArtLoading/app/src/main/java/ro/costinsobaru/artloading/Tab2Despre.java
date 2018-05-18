package ro.costinsobaru.artloading;

/**
 * Created by Geanina on 10.04.2017.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Tab2Despre  extends Fragment {

    //ListView list;


    @Override


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2despre, container, false);
        ListView  listView = (ListView)rootView.findViewById(R.id.actori);
        String [] item  = {"Google Plus",
                "Twitter",
                "Windows",
                "Bing",
                "Itunes",
                "Wordpress",
                "Drupal"};

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, item);
        listView.setAdapter(adapter);

        return rootView;
    }
}
