package com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.Metier.GestionMot;
import com.poutchinskaya.malik.sotunisia.Presentation.Model.Mot;
import com.poutchinskaya.malik.sotunisia.R;

/**
 * Created by Malik on 31/12/2015.
 */
public class VocabulaireFragment extends Fragment {

    String langueChoisie;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_vocabulaire, container, false);

        //Transmition de la langue choisie
        langueChoisie = ((Vocabulaire_Activity) getActivity()).getLangueChoisie();


        //On affiche un mot au hasard
        final GestionMot gestionMot = new GestionMot(rootView.getContext(), langueChoisie);
        Mot premierMot = gestionMot.getUnMotRandom();
        TextView textQuestion = (TextView) rootView.findViewById(R.id.textViewQuestion);
        TextView textReponse1 = (TextView) rootView.findViewById(R.id.textViewReponse1);
        TextView textReponse2 = (TextView) rootView.findViewById(R.id.textViewReponse2);

        textQuestion.setText(premierMot.getFrancais());
        textReponse1.setText(premierMot.getPhonetique());
        textReponse2.setText(premierMot.getTunisien());


        //On passe à un nouveau mot lors du clic sur le bouton ==>
        final ImageView loginButtonNext = (ImageView) rootView.findViewById(R.id.buttonNextMot);
        loginButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //On arret l'écoute avant de trouver un nouveau mot
                gestionMot.ecouteMotForNext(false);


                gestionMot.modeQuestion(rootView);


            }
        });

        //On affiche les réponse et le bouton next quand clic sur bouton reponse
        //On passe à un nouveau mot lors du clic sur le bouton
        final ImageView loginButtonReponse = (ImageView) rootView.findViewById(R.id.buttonReponse);
        loginButtonReponse.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gestionMot.modeReponse(rootView);
            }
        });

        //Bonton d'ecoute
        final ImageView loginbuttonEcouteMot = (ImageView) rootView.findViewById(R.id.buttonEcouteMot);
        loginbuttonEcouteMot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                gestionMot.ecouteMot(true);
            }
        });
        return rootView;
    }

}
