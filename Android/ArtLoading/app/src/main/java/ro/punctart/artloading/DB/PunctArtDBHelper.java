package ro.punctart.artloading.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Costin on 29.05.2017.
 */

public class PunctArtDBHelper extends SQLiteOpenHelper {
    public PunctArtDBHelper(Context context) {
        super(context, PunctArtDB.DB_NAME,null,PunctArtDB.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTable = "CREATE TABLE IF NOT EXISTS "+ PunctArtDB.PunctArtEntry.TABLE_NAME+
                " ( " +PunctArtDB.PunctArtEntry._ID + " INTEGER PRIMARY KEY, "
                + PunctArtDB.PunctArtEntry.NUME + " VARCHAR, "
                + PunctArtDB.PunctArtEntry.PRENUME + " VARCHAR, "
                + PunctArtDB.PunctArtEntry.PAROLA + " VARCHAR, "
                + PunctArtDB.PunctArtEntry.EMAIL + " VARCHAR, "
                + PunctArtDB.PunctArtEntry.ISLOGED + " INTEGER)";
        sqLiteDatabase.execSQL(createTable);
        // AICI : am aduagat artificial userul :D
        // AICI
        // AICI
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }

    public void insert(String nume,String prenume,String email,String parola){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PunctArtDB.PunctArtEntry.NUME, nume);
        values.put(PunctArtDB.PunctArtEntry.PRENUME, prenume);
        values.put(PunctArtDB.PunctArtEntry.EMAIL, email);
        values.put(PunctArtDB.PunctArtEntry.PAROLA, parola);
        // AICI : 0 inseamna ca NU este logat, 1 inseamna ca este logat
        // avand in vedere ca il introduci doar o data in aplicatie, il pui automat cu 1
        // gen cand il pui il si loghezi
        values.put(PunctArtDB.PunctArtEntry.ISLOGED, 1);
        long newRow = db.insert(PunctArtDB.PunctArtEntry.TABLE_NAME, null, values);
        db.close();

    }

    public void delete() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+PunctArtDB.PunctArtEntry.TABLE_NAME);
        db.close();
    }

    public boolean savedLogin(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + PunctArtDB.PunctArtEntry.TABLE_NAME + " WHERE isloged = '"+1+"'", null);
        c.moveToNext();
        int x = c.getCount();
        return x == 1;
    }

    public String getEmail() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ PunctArtDB.PunctArtEntry.TABLE_NAME + " WHERE isloged = '"+1+"'", null);
        c.moveToNext();
        int x = c.getCount();

        if(x == 0)
            return null;


        String email =
                (c.getString(c.getColumnIndex("email")));
        db.close();
        return  email;
    }

    public String getPass() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ PunctArtDB.PunctArtEntry.TABLE_NAME + " WHERE isloged = '"+1+"'", null);
        c.moveToNext();
        int x = c.getCount();

        if(x == 0)
            return null;


        String parola =
                (c.getString(c.getColumnIndex("parola")));
        db.close();
        return parola;
    }

    public String getNume() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ PunctArtDB.PunctArtEntry.TABLE_NAME + " WHERE isloged = '"+1+"'", null);
        c.moveToNext();
        int x = c.getCount();

        if(x == 0)
            return null;


        String email =
                (c.getString(c.getColumnIndex("nume")));
        db.close();
        return  email;
    }

    public String getPrenume() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM "+ PunctArtDB.PunctArtEntry.TABLE_NAME + " WHERE isloged = '"+1+"'", null);
        c.moveToNext();
        int x = c.getCount();

        if(x == 0)
            return null;


        String email =
                (c.getString(c.getColumnIndex("prenume")));
        db.close();
        return  email;
    }

}
