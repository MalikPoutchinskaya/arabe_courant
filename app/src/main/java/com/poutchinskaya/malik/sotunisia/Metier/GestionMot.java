package com.poutchinskaya.malik.sotunisia.Metier;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.Presentation.Mot;
import com.poutchinskaya.malik.sotunisia.Dao.MotDao;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.ArrayList;

/**
 * Created by Malik on 21/11/2015.
 * http://fr.jeffprod.com/blog/2015/utilisation-d-une-base-sqlite-sous-android.html
 */
public class GestionMot {

    Context context;

    MediaPlayer mediaPlayer;

    //Mot utilise actuellement
    Mot currentMot;


    public GestionMot(Context context) {
        this.context = context;
    }

    private ArrayList<Mot> getAllMots() {

        ArrayList<Mot> listMot = new ArrayList<Mot>();

        MotDao m = new MotDao(context); // gestionnaire de la table "animal"
        m.open(); // ouverture de la table en lecture/écriture


        // Listing des enregistrements de la table
        Cursor c = m.getMots();
        if (c.moveToFirst()) {
            do {
                //TODO:On assimile le codeAudio a un audioPlayer
                // c.getString(c.getColumnIndex(MotDao.KEY_MOT_AUDIO))

                mediaPlayer = MediaPlayer.create(context, R.raw.test_audio);

                Mot mot = new Mot(c.getString(c.getColumnIndex(MotDao.KEY_MOT_TUNISIEN)),
                        c.getString(c.getColumnIndex(MotDao.KEY_MOT_FRANCAIS)),
                        mediaPlayer,
                        c.getString(c.getColumnIndex(MotDao.KEY_MOT_PHONETIQUE)));

                listMot.add(mot);

            }
            while (c.moveToNext());
        }
        c.close(); // fermeture du curseur

        // fermeture du gestionnaire
        m.close();
        //Renvoi la liste de tous les mots
        return listMot;
    }

    public Mot getUnMotRandom() {
        int lower = 0;
        int higher = getAllMots().size();
        int random = (int) (Math.random() * (higher - lower)) + lower;

        //On met à jour le mot actuel dans la classe
        setCurrentMot(getAllMots().get(random));

        return getAllMots().get(random);

    }

    //quand on appuis sur le bouton Next
    public void modeQuestion(View rootView) {

        Mot suiteMot = getUnMotRandom();


        TextView textQuestion = (TextView) rootView.findViewById(R.id.textViewQuestion);

        TextView textReponse1 = (TextView) rootView.findViewById(R.id.textViewReponse1);
        TextView textReponse2 = (TextView) rootView.findViewById(R.id.textViewReponse2);
        TextView ligneDesign1 = (TextView) rootView.findViewById(R.id.textViewLigne1);

        ImageView boutonNext = (ImageView) rootView.findViewById(R.id.buttonNextMot);
        ImageView boutonReponse = (ImageView) rootView.findViewById(R.id.buttonReponse);
        ImageView boutonEcouteMot = (ImageView) rootView.findViewById(R.id.buttonEcouteMot);


        textQuestion.setText(suiteMot.getFrancais());
        textReponse1.setText(suiteMot.getPhonetique());
        textReponse2.setText(suiteMot.getTunisien());
        textReponse1.setVisibility(View.INVISIBLE);
        textReponse2.setVisibility(View.INVISIBLE);

        boutonReponse.setVisibility(View.VISIBLE);
        boutonNext.setVisibility(View.INVISIBLE);
        boutonEcouteMot.setVisibility(View.INVISIBLE);
        ligneDesign1.setVisibility(View.INVISIBLE);



    }

    //quand on appuis sur le bouton ?
    public void modeReponse(View rootView) {

        TextView textReponse1 = (TextView) rootView.findViewById(R.id.textViewReponse1);
        TextView textReponse2 = (TextView) rootView.findViewById(R.id.textViewReponse2);
        TextView ligneDesign1 = (TextView) rootView.findViewById(R.id.textViewLigne1);
        ImageView boutonNext = (ImageView) rootView.findViewById(R.id.buttonNextMot);
        ImageView boutonReponse = (ImageView) rootView.findViewById(R.id.buttonReponse);
        ImageView boutonEcouteMot = (ImageView) rootView.findViewById(R.id.buttonEcouteMot);



        textReponse1.setVisibility(View.VISIBLE);
        textReponse2.setVisibility(View.VISIBLE);

        boutonReponse.setVisibility(View.INVISIBLE);
        boutonNext.setVisibility(View.VISIBLE);
        boutonEcouteMot.setVisibility(View.VISIBLE);
        ligneDesign1.setVisibility(View.VISIBLE);



    }

    //Lance et arret l'ecoute du mot
    public void ecouteMot(boolean b) {
        //Si true on ecoute
        if (b == true && !getCurrentMot().getAudioPlayer().isPlaying()) {
            getCurrentMot().getAudioPlayer().start();

        } else if(getCurrentMot().getAudioPlayer().isPlaying()) {
            getCurrentMot().getAudioPlayer().pause();
        }
    }

    //Getters Setter du Mot actuel

    public Mot getCurrentMot() {
        return currentMot;
    }

    public void setCurrentMot(Mot currentMot) {
        this.currentMot = currentMot;
    }
}
