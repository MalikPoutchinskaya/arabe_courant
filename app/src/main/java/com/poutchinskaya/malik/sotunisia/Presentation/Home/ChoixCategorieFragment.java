package com.poutchinskaya.malik.sotunisia.Presentation.Home;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire.VocabulaireController;
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
        final String langueChoisie = getArguments().getString("langueChoisi");



        // VOCABULAIRE
        final ImageView loginButtonVocab = (ImageView) rootView.findViewById(R.id.imageViewVocab);
        loginButtonVocab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rootView.getContext(), VocabulaireController.class);
                intent.putExtra("langueChoisie", langueChoisie);
                startActivity(intent);
            }
        });



        // PRONONCIATION
        final ImageView loginButtonPrononciation = (ImageView) rootView.findViewById(R.id.imageViewPrononciation);
        loginButtonPrononciation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(rootView.getContext(), VocabulaireController.class);
               // startActivity(intent);
                Toast.makeText(rootView.getContext(),"Coming soon...",Toast.LENGTH_LONG).show();
            }
        });






        return rootView;
    }
}