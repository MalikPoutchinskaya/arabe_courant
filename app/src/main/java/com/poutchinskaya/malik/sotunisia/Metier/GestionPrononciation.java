package com.poutchinskaya.malik.sotunisia.Metier;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;

import com.poutchinskaya.malik.sotunisia.Dao.PrononciationDao;
import com.poutchinskaya.malik.sotunisia.Presentation.Model.Prononciation;

import java.util.ArrayList;

/**
 * Created by Malik on 28/12/2015.
 */
public class GestionPrononciation {

    Context context;

    MediaPlayer mediaPlayer;


    //Toutes les tables.
    PrononciationDao m;

    //Variable de selection en table
    String lettreArabe;
    String lettreDetails;
    String lettrePhonetique;

    //Constructeur


    public GestionPrononciation(Context context) {
        this.context = context;
    }

    public ArrayList<Prononciation> getAllPrononciations() {

        ArrayList<Prononciation> listPrononciation = new ArrayList<Prononciation>();


        m = new PrononciationDao(context); // gestionnaire de la table "mot" pour tunisien


        m.open(); // ouverture de la table en lecture/écriture


        // Listing des enregistrements de la table
        Cursor c = m.getMots();
        if (c.moveToFirst()) {
            do {

                //Je vais chercher le string du codeAudio en table et je le remet en int
                String name = c.getString(c.getColumnIndex(PrononciationDao.KEY_LETTRE_AUDIO));
                //Si le name est null(=si null dans colonne audio), l'appli crash
                if (name == null){
                    name ="";
                }
                int codeAudio = context.getResources().getIdentifier(name, "raw", context.getPackageName());


                //Je crée mon média avec le int
                try {
                    mediaPlayer = MediaPlayer.create(context, codeAudio);

                } catch (Exception e) {
                    mediaPlayer = new MediaPlayer();
                }

                //Je cree mon mots
                Prononciation prononciation = new Prononciation(c.getString(c.getColumnIndex(PrononciationDao.KEY_LETTRE_ARABE)),
                        c.getString(c.getColumnIndex(PrononciationDao.KEY_LETTRE_PHONETIQUE)),
                        mediaPlayer,
                        c.getString(c.getColumnIndex(PrononciationDao.KEY_LETTRE_DETAILS)));

                listPrononciation.add(prononciation);

            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        // fermeture du gestionnaire
        m.close();
        //Renvoi la liste de tous les mots
        return listPrononciation;
    }
}
