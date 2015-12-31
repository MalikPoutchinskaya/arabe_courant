package com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.poutchinskaya.malik.sotunisia.R;

public class Vocabulaire_Activity extends FragmentActivity {

    public static String langueChoisie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Instance bundle pour transmition de la langue
        final Bundle b = new Bundle();
        final VocabulaireFragment vocabulaireFragment = new VocabulaireFragment();


        //Récupération de la langue choisie
        Intent intent = getIntent();
        langueChoisie = intent.getStringExtra("langueChoisie");


        setContentView(R.layout.activity_vocabulaire);


    }

    public static String getLangueChoisie() {
        return langueChoisie;
    }


}



