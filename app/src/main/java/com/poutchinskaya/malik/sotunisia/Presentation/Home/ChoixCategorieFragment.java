package com.poutchinskaya.malik.sotunisia.Presentation.Home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.poutchinskaya.malik.sotunisia.Presentation.Prononciation.Prononciation_activiy;
import com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire.VocabulaireFragment;
import com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire.Vocabulaire_Activity;
import com.poutchinskaya.malik.sotunisia.R;

/**
 * Created by Malik on 26/11/2015.
 */
public class ChoixCategorieFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragement_choix_home, container, false);

        //Transmition de la langue choisie
        final String langueChoisie = getArguments().getString("langueChoisie");


        // VOCABULAIRE
        final ImageView loginButtonVocab = (ImageView) rootView.findViewById(R.id.imageViewVocab);
        loginButtonVocab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(rootView.getContext(), Vocabulaire_Activity.class);
                intent.putExtra("langueChoisie", langueChoisie);
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
}