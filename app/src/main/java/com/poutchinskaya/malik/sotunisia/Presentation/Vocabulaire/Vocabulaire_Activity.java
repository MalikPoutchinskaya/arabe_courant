package com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.poutchinskaya.malik.sotunisia.R;

public class Vocabulaire_Activity extends FragmentActivity {

    public static String langueChoisie;
    public static String domaineChoisie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        //Récupération de la langue choisie
        Intent intent = getIntent();
        langueChoisie = intent.getStringExtra("langueChoisie");
        domaineChoisie = intent.getStringExtra("domaineChoisie");


        setContentView(R.layout.activity_vocabulaire);


    }

    public static String getLangueChoisie() {
        return langueChoisie;
    }

    public static String getDomaineChoisie() {
        return domaineChoisie;
    }
}



