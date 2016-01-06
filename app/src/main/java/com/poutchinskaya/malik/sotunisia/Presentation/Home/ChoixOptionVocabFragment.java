package com.poutchinskaya.malik.sotunisia.Presentation.Home;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.Metier.GestionMot;
import com.poutchinskaya.malik.sotunisia.Presentation.Prononciation.Prononciation_activiy;
import com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire.ListeVocabulaire_Activity;
import com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire.Vocabulaire_Activity;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Malik on 26/11/2015.
 */
public class ChoixOptionVocabFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String domaine = "";
    Context context;
    String[] listString;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_choix_option_vocabulaire, container, false);

        //Je valorise le context pour le spinner
        context = rootView.getContext();

        //Transmition de la langue choisie
        final String langueChoisie = getArguments().getString("langueChoisie");

        //Alimentation du Spinner Domaine
        spinner = (Spinner) rootView.findViewById(R.id.domaine_spinner);
        GestionMot gestionMot = new GestionMot(rootView.getContext(), langueChoisie, domaine);
        ArrayList<String> listAllDomaineMot = gestionMot.getAllDomaine();
        Collections.sort(listAllDomaineMot);
        //Change l'array en liste de string pour l'adapter du spinner
        int i = 0;
        listString = new String[listAllDomaineMot.size()];
        for (String s : listAllDomaineMot) {

            listString[i] = s;
            i++;
        }

        // Create an ArrayAdapter using the string array and a default spinner layout
       // ArrayAdapter<String> adapter = new ArrayAdapter<String>(rootView.getContext(), R.layout.ligne_spinner, listAllDomaineMot);
        // Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(new MyCustomAdapter(rootView.getContext(), R.layout.ligne_spinner, listString));
        spinner.setOnItemSelectedListener(this);


        // VOCABULAIRE
        final ImageView loginButtonVocab = (ImageView) rootView.findViewById(R.id.imageButtonTEST_VOCAB);
        loginButtonVocab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(rootView.getContext(), Vocabulaire_Activity.class);
                intent.putExtra("langueChoisie", langueChoisie);
                intent.putExtra("domaineChoisie", domaine);
                startActivity(intent);


            }
        });


        // LISTE VOCAB
        final ImageView loginButtonListeVocab = (ImageView) rootView.findViewById(R.id.imageButtonLISTE_VOCAB);
        loginButtonListeVocab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), ListeVocabulaire_Activity.class);
                intent.putExtra("langueChoisie", langueChoisie);
                intent.putExtra("domaineChoisie", domaine);
                startActivity(intent);

            }
        });

        // PRONONCIATION
        final ImageView loginButtonPrononciation = (ImageView) rootView.findViewById(R.id.imageViewPrononciation);
        loginButtonPrononciation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), Prononciation_activiy.class);
                startActivity(intent);

            }
        });


        return rootView;
    }


    //Règles du spinner
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner.setSelection(position);
        domaine = (String) spinner.getSelectedItem();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Je customise mon Spinner
    public class MyCustomAdapter extends ArrayAdapter<String> {

        public MyCustomAdapter(Context context, int textViewResourceId,
                               String[] objects) {
            super(context, textViewResourceId, objects);
            // TODO Auto-generated constructor stub
        }

        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            return getCustomView(position, convertView, parent);
        }

        public View getCustomView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            //return super.getView(position, convertView, parent);

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = inflater.inflate(R.layout.ligne_spinner, parent, false);
            TextView label = (TextView) row.findViewById(R.id.text_domaine_spinner);
            label.setText(listString[position]);


            //Pour ajouter une image en plus
            //ImageView icon = (ImageView) row.findViewById(R.id.icon);
            //if (listString[position] == "Sunday") {
            //    icon.setImageResource(R.drawable.icon);
            //} else {
            //    icon.setImageResource(R.drawable.icongray);
            //}

            return row;
        }
    }
}