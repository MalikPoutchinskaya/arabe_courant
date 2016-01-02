package com.poutchinskaya.malik.sotunisia.Metier;

import android.content.Context;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.poutchinskaya.malik.sotunisia.Dao.IMotDao;
import com.poutchinskaya.malik.sotunisia.Dao.MotAlgerienDao;
import com.poutchinskaya.malik.sotunisia.Dao.MotMarocainDao;
import com.poutchinskaya.malik.sotunisia.Presentation.Model.Mot;
import com.poutchinskaya.malik.sotunisia.Dao.MotTunisienDao;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Malik on 21/11/2015.
 * http://fr.jeffprod.com/blog/2015/utilisation-d-une-base-sqlite-sous-android.html
 */
public class GestionMot {

    Context context;

    MediaPlayer mediaPlayer;

    //Mot utilise actuellement
    Mot currentMot;

    //Récupération de la langue choisie et du domaine
    GestionLangues gestionLangues = new GestionLangues();
    String langueChoisie;
    String domaineChoisie;

    //Toutes les tables.
    IMotDao m;

    //Variable de selection en table
    String motArabe;
    String motFrancais;
    String motPhonetique;
    String motDomaine;


    public GestionMot(Context context, String langueChoisie, String domaineChoisie) {
        this.context = context;
        this.langueChoisie = langueChoisie;
        this.domaineChoisie = domaineChoisie;
    }

    private ArrayList<Mot> getAllMots() {

        ArrayList<Mot> listMot = new ArrayList<Mot>();

        //Test de la langue choisie
        if (gestionLangues.getTn().equals(langueChoisie)) {
            m = new MotTunisienDao(context); // gestionnaire de la table "mot" pour tunisien
            motArabe = MotTunisienDao.KEY_MOT_ARABE;
            motFrancais = MotTunisienDao.KEY_MOT_FRANCAIS;
            motPhonetique = MotTunisienDao.KEY_MOT_PHONETIQUE;
            motDomaine = MotTunisienDao.KEY_MOT_DOMAINE;
        } else if (gestionLangues.getAl().equals(langueChoisie)) {
            m = new MotAlgerienDao(context);
            motArabe = MotAlgerienDao.KEY_MOT_ARABE;
            motFrancais = MotAlgerienDao.KEY_MOT_FRANCAIS;
            motPhonetique = MotAlgerienDao.KEY_MOT_PHONETIQUE;
        } else if (gestionLangues.getMa().equals(langueChoisie)) {
            m = new MotMarocainDao(context);
            motArabe = MotMarocainDao.KEY_MOT_ARABE;
            motFrancais = MotMarocainDao.KEY_MOT_FRANCAIS;
            motPhonetique = MotMarocainDao.KEY_MOT_PHONETIQUE;
        }

        m.open(); // ouverture de la table en lecture/écriture


        // Listing des enregistrements de la table
        Cursor c = m.getMots();
        if (c.moveToFirst()) {
            do {

                //Je vais chercher le string du codeAudio en table et je le remet en int
                int codeAudio = context.getResources().getIdentifier(c.getString(c.getColumnIndex(MotTunisienDao.KEY_MOT_AUDIO)), "raw",
                        context.getPackageName());

                //Je crée mon média avec le int
                try {
                    mediaPlayer = MediaPlayer.create(context, codeAudio);

                } catch (Exception e) {
                    mediaPlayer = null;
                }

                //Je cree mon mots
                Mot mot = new Mot(c.getString(c.getColumnIndex(motArabe)),
                        c.getString(c.getColumnIndex(motFrancais)),
                        mediaPlayer,
                        c.getString(c.getColumnIndex(motPhonetique)),
                        c.getString(c.getColumnIndex(motDomaine)));

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
        ArrayList<Mot> listMotByDomaine = gettAllMotFilterByDomaine();
        int lower = 0;
        int higher = listMotByDomaine.size();
        int random = (int) (Math.random() * (higher - lower)) + lower;
        Mot motRandom = listMotByDomaine.get(random);


        //On met à jour le mot actuel dans la classe
        setCurrentMot(motRandom);

        return motRandom;

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
        try {
            if (b == true && !getCurrentMot().getAudioPlayer().isPlaying()) {
                getCurrentMot().getAudioPlayer().start();

            } else if (getCurrentMot().getAudioPlayer().isPlaying()) {
                getCurrentMot().getAudioPlayer().pause();
            }
        } catch (Exception e) {
            Toast.makeText(context, "Désolé, pas d'audio disponible pour ce mot pour l'instant.", Toast.LENGTH_SHORT).show();
        }
    }

    //Lance et arret l'ecoute du mot
    public void ecouteMotForNext(boolean b) {
        //Si true on ecoute
        try {
            if (b == true && !getCurrentMot().getAudioPlayer().isPlaying()) {
                getCurrentMot().getAudioPlayer().start();

            } else if (getCurrentMot().getAudioPlayer().isPlaying()) {
                getCurrentMot().getAudioPlayer().pause();
            }
        } catch (Exception e) {
        }
    }

    //Getters Setter du Mot actuel

    public Mot getCurrentMot() {
        return currentMot;
    }

    public void setCurrentMot(Mot currentMot) {
        this.currentMot = currentMot;
    }


    //Retourne la liste des domaines de la table
    public ArrayList<String> getAllDomaine() {
        ArrayList<String> listAllDomaine = new ArrayList<>();
        ArrayList<Mot> listMots = getAllMots();
        String domaine;
        for (Mot mot : listMots
                ) {
            domaine = mot.getMotDomaine();
            listAllDomaine.add(domaine);

        }

        Set set = new HashSet();
        set.addAll(listAllDomaine);
        ArrayList listSansDoublonDomaine = new ArrayList(set);
        return listSansDoublonDomaine;
    }

    //Retourne la liste des mots du domaine choisie
    private ArrayList<Mot> gettAllMotFilterByDomaine() {
        ArrayList<Mot> listAllMotByDomaine = new ArrayList<>();
        ArrayList<Mot> listMots = getAllMots();

        for (Mot mot : listMots
                ) {
            if (mot.getMotDomaine().equals(domaineChoisie)) {
                listAllMotByDomaine.add(mot);
            }

        }
        return listAllMotByDomaine;
    }
}
