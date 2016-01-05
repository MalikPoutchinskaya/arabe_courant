package com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.poutchinskaya.malik.sotunisia.Metier.GestionMot;
import com.poutchinskaya.malik.sotunisia.Presentation.Model.Mot;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Malik on 05/01/2016.
 */
public class ListeVocabulaire_Activity extends Activity {

    private ListView maListViewPerso2;
    private ArrayList<Mot> listMotVocab;
    private GestionMot gestionMot;
    private String langueChoisie;
    private String domaineChoisi;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_vocab);

        //Récupération de la langue choisie
        Intent intent = getIntent();
        langueChoisie = intent.getStringExtra("langueChoisie");
        domaineChoisi = intent.getStringExtra("domaineChoisie");

        //Récupération de la liste de prononcitation
        gestionMot = new GestionMot(this, langueChoisie, domaineChoisi);
        listMotVocab = gestionMot.gettAllMotFilterByDomaine();


        //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso2 = (ListView) findViewById(R.id.listviewperso2);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;


        for (Mot mot : listMotVocab
                ) {
            //Création d'une HashMap pour insérer les informations du premier item de notre listView
            map = new HashMap<String, String>();
            //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
            map.put("mot_arabe", mot.getTunisien());
            //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
            map.put("mot_phonetique", mot.getPhonetique());
            //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
            map.put("mot_francais", mot.getFrancais());
            //enfin on ajoute cette hashMap dans la arrayList
            listItem.add(map);

            //On refait la manip plusieurs fois avec des données différentes pour former les items de notre ListView
        }


        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.ligne_liste_vocab,
                new String[]{"mot_arabe", "mot_phonetique", "mot_francais"}, new int[]{R.id.vocab_arabe, R.id.vocab_phonetique, R.id.vocab_francais});

        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso2.setAdapter(mSchedule);

        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso2.getItemAtPosition(position);
                //on créer un message
                Toast.makeText(getApplicationContext(), "L'audio " + map.get("mot_francais") + " n'est pas encore disponible.", Toast.LENGTH_LONG).show();

            }
        });

    }
}

