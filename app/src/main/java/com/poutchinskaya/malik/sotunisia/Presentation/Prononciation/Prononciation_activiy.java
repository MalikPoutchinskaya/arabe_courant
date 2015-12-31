package com.poutchinskaya.malik.sotunisia.Presentation.Prononciation;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.poutchinskaya.malik.sotunisia.Metier.GestionPrononciation;
import com.poutchinskaya.malik.sotunisia.Presentation.Model.Prononciation;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Malik on 28/12/2015.
 */
public class Prononciation_activiy extends Activity {

    private ListView maListViewPerso;
    private ArrayList<Prononciation> listPrononciation;
    private GestionPrononciation gestionPrononciation;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prononciation);

        //Récupération de la liste de prononcitation
        gestionPrononciation = new GestionPrononciation(this);
        listPrononciation = gestionPrononciation.getAllPrononciations();


        //Récupération de la listview créée dans le fichier main.xml
        maListViewPerso = (ListView) findViewById(R.id.listviewperso);

        //Création de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();

        //On déclare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;


        for (Prononciation prononciation : listPrononciation
                ) {
            //Création d'une HashMap pour insérer les informations du premier item de notre listView
            map = new HashMap<String, String>();
            //on insère un élément titre que l'on récupérera dans le textView titre créé dans le fichier affichageitem.xml
            map.put("lettre_arabe", prononciation.getLettreArabe());
            //on insère un élément description que l'on récupérera dans le textView description créé dans le fichier affichageitem.xml
            map.put("lettre_details", prononciation.getLettreDetails());
            //on insère la référence à l'image (convertit en String car normalement c'est un int) que l'on récupérera dans l'imageView créé dans le fichier affichageitem.xml
            map.put("lettre_phonetique", prononciation.getLettrePhonetique());
            //enfin on ajoute cette hashMap dans la arrayList
            listItem.add(map);

            //On refait la manip plusieurs fois avec des données différentes pour former les items de notre ListView
        }


        //Création d'un SimpleAdapter qui se chargera de mettre les items présent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter(this.getBaseContext(), listItem, R.layout.ligne_prononciation,
                new String[]{"lettre_arabe", "lettre_details", "lettre_phonetique"}, new int[]{R.id.lettre_arabe, R.id.lettre_details, R.id.lettre_phonetique});

        //On attribut à notre listView l'adapter que l'on vient de créer
        maListViewPerso.setAdapter(mSchedule);

        //Enfin on met un écouteur d'évènement sur notre listView
        maListViewPerso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            @SuppressWarnings("unchecked")
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                //on récupère la HashMap contenant les infos de notre item (titre, description, img)
                HashMap<String, String> map = (HashMap<String, String>) maListViewPerso.getItemAtPosition(position);
                //on créer un message
                Toast.makeText(getApplicationContext(), "L'audio " + map.get("lettre_phonetique") + " n'est pas encore disponible.", Toast.LENGTH_LONG).show();

            }
        });

    }
}

