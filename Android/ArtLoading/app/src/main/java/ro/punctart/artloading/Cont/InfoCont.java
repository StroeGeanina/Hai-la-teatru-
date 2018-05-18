package ro.punctart.artloading.Cont;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ro.punctart.artloading.DB.PunctArtDBHelper;
import ro.punctart.artloading.Homepage;
import ro.punctart.artloading.R;

import static android.R.attr.onClick;

/**
 * Created by dg on 5/19/2017.
 */

public class InfoCont extends AppCompatActivity {

    TextView firstNameTV, lastNameTV, emailTV;
    String nume, prenume, email;
    Button btnLogOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info_cont);

        firstNameTV = (TextView) findViewById(R.id.firstNameTV);
        lastNameTV = (TextView) findViewById(R.id.lastNameTV);
        emailTV = (TextView) findViewById(R.id.emailET);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);

        PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());

        nume = db.getNume();
        prenume = db.getPrenume();
        email = db.getEmail();

        firstNameTV.setText(prenume);
        lastNameTV.setText(nume);
        emailTV.setText(email);

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(InfoCont.this, "V-ati delogat de pe cont.", Toast.LENGTH_LONG).show();

                PunctArtDBHelper db = new PunctArtDBHelper(getApplicationContext());
                db.delete();

                Intent i = new Intent(InfoCont.this, Homepage.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.putExtra("EXIT", true);
                startActivity(i);

                finish();
            }
        });
    }
}
