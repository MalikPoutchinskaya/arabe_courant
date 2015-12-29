package com.poutchinskaya.malik.sotunisia.Dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Malik on 21/11/2015.
 */
public class PrononciationDao {

    private static final String TABLE_NAME = "prononciationTb";
    public static final String KEY_ID_PRONONCIATIONT="id_prononciation";
    public static final String KEY_LETTRE_ARABE ="lettreArabe";
    public static final String KEY_LETTRE_DETAILS="lettreDetails";
    public static final String KEY_LETTRE_PHONETIQUE="lettrePhonetique";
    public static final String KEY_LETTRE_AUDIO="lettreAudio";


    public static final String CREATE_TABLE_MOT = "CREATE TABLE "+TABLE_NAME+
            " (" +
            " "+KEY_ID_PRONONCIATIONT+" INTEGER primary key," +
            " "+ KEY_LETTRE_ARABE +" TEXT" +
            " "+KEY_LETTRE_DETAILS+" TEXT" +
            " "+KEY_LETTRE_PHONETIQUE+" TEXT" +
            " "+KEY_LETTRE_AUDIO+" TEXT" +
            ");";

    private MySQLite maBaseSQLite; // notre gestionnaire du fichier SQLite
    private SQLiteDatabase db;


    // Constructeur
    public PrononciationDao(Context context)
    {
        maBaseSQLite = MySQLite.getInstance(context);
    }


    public void open()
    {
        //on ouvre la table en lecture/écriture
        db = maBaseSQLite.getWritableDatabase();
    }


    public void close()
    {
        //on ferme l'accès à la BDD
        db.close();
    }


    public Cursor getMots() {
        // sélection de tous les enregistrements de la table
        return db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
    }
}
