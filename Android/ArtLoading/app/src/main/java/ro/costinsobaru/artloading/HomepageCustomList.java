package ro.costinsobaru.artloading;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewGroupCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Costin on 10.04.2017.
 */

public class HomepageCustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] numeSpectacol;
    private final String[] numeTeatru;
    private final String[] data;
    private final String[] nota;
    private final String[] path;

    public HomepageCustomList(Activity context, String[] numeSpectacol, String[] numeTeatru, String[] data, String[] nota, String[] path) {
        super(context, R.layout.list_single, numeSpectacol);
        this.context = context;
        this.numeSpectacol = numeSpectacol;
        this.numeTeatru = numeTeatru;
        this.data = data;
        this.nota = nota;
        this.path = path;
        Log.d("constructor", "mam construit - ");
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);

        TextView tvTitlu = (TextView) rowView.findViewById(R.id.nume_piesa);
        TextView tvTeatru = (TextView) rowView.findViewById(R.id.teatru);
        TextView tvData  = (TextView)rowView.findViewById(R.id.data_ora);
        TextView tvNota = (TextView) rowView.findViewById(R.id.nota);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.poster);

        tvTitlu.setText(numeSpectacol[position]);
        tvTeatru.setText(numeTeatru[position]);
        tvData.setText(data[position]);
        tvNota.setText(nota[position]+"/5.0");
        //imageView.setImageResource(R.drawable.user);
        Log.d("linknormla", "m-am apelat");

        Picasso.with(getContext()).load(path[position]).into(imageView);
        //Log.d("lijnk", path[position]);
        return rowView;
    }
}
