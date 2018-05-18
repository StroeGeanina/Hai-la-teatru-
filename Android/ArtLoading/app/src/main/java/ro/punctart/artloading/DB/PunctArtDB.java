package ro.punctart.artloading.DB;

import android.provider.BaseColumns;

/**
 * Created by Costin on 29.05.2017.
 */

public final class PunctArtDB {
    private PunctArtDB(){}
    public static final String DB_NAME = "punctart.db";
    public static final int DB_VERSION = 1;
    public static class PunctArtEntry implements BaseColumns{
        public static final String TABLE_NAME = "Users";
        public static final String NUME = "nume";
        public static final String PRENUME= "prenume";
        public static final String EMAIL = "email";
        public static final String PAROLA = "parola";
        public static final String ISLOGED = "isloged";
    }
}
