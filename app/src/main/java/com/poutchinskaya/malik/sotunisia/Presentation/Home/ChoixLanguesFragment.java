package com.poutchinskaya.malik.sotunisia.Presentation.Home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.Metier.GestionLangues;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.ArrayList;

/**
 * Created by Malik on 26/11/2015.
 */
public class ChoixLanguesFragment extends Fragment {

    TextView tunisien;
    TextView marocain;
    TextView algerien;
    ImageView flagNext;

    ChoixOptionVocabFragment choixCategorieFragment;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.framgement_choix_langues, container, false);

        //Instance du metier
        final GestionLangues gestionLangues = new GestionLangues();




        //Initialise les textes des boutons
        tunisien = (TextView) rootView.findViewById(R.id.textTn);
        marocain = (TextView) rootView.findViewById(R.id.textViewMa);
        algerien = (TextView) rootView.findViewById(R.id.textViewAl);

        final ArrayList<TextView> listTextLangue = new ArrayList<>();
        listTextLangue.add(tunisien);
        listTextLangue.add(marocain);
        listTextLangue.add(algerien);


        //Initialise les Bouttons
        final ImageButton loginButtonMa = (ImageButton) rootView.findViewById(R.id.imageButtonMa);
        final ImageButton loginButtonTn = (ImageButton) rootView.findViewById(R.id.imageButtonTn);
        final ImageButton loginButtonAl = (ImageButton) rootView.findViewById(R.id.imageButtonAl);
        final ArrayList<ImageButton> listBoutton = new ArrayList<>();
        listBoutton.add(loginButtonAl);
        listBoutton.add(loginButtonMa);
        listBoutton.add(loginButtonTn);

        flagNext = (ImageView) rootView.findViewById(R.id.imageViewNext);


        // Tunisien

        loginButtonTn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (loginButtonTn.isSelected()) {

                    //raz des choix précedent
                    gestionLangues.razChoix(listBoutton, listTextLangue,flagNext);

                    flagNext.setVisibility(View.INVISIBLE);

                } else {
                    //raz des choix précedent
                    gestionLangues.razChoix(listBoutton, listTextLangue,flagNext);

                    loginButtonTn.setSelected(true);
                    tunisien.setVisibility(View.VISIBLE);
                    flagNext.setVisibility(View.VISIBLE);
                }

                gestionLangues.setLangueChoisie(gestionLangues.getTn());
            }
        });


        // Marocain
        loginButtonMa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (loginButtonMa.isSelected()) {

                    //raz des choix précedent
                    gestionLangues.razChoix(listBoutton, listTextLangue,flagNext);

                    flagNext.setVisibility(View.INVISIBLE);

                } else {
                    //raz des choix précedent
                    gestionLangues.razChoix(listBoutton, listTextLangue,flagNext);

                    loginButtonMa.setSelected(true);
                    marocain.setVisibility(View.VISIBLE);
                    flagNext.setVisibility(View.VISIBLE);
                }

                gestionLangues.setLangueChoisie(gestionLangues.getMa());

            }
        });

        // Algérien
        loginButtonAl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (loginButtonAl.isSelected()) {

                    //raz des choix précedent
                    gestionLangues.razChoix(listBoutton, listTextLangue,flagNext);

                    flagNext.setVisibility(View.INVISIBLE);

                } else {
                    //raz des choix précedent
                    gestionLangues.razChoix(listBoutton, listTextLangue,flagNext);

                    loginButtonAl.setSelected(true);
                    algerien.setVisibility(View.VISIBLE);
                    flagNext.setVisibility(View.VISIBLE);
                }

                gestionLangues.setLangueChoisie(gestionLangues.getAl());
            }
        });


        // Next
        final ImageView loginButtonNext = (ImageView) rootView.findViewById(R.id.imageViewNext);
        loginButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                //Instance bundle pour transmition de la langue
                final Bundle b = new Bundle();
                choixCategorieFragment = new ChoixOptionVocabFragment();
                final FragmentManager fm = getFragmentManager();

                final FragmentTransaction transaction = fm.beginTransaction();


                //Envoie des données au prochain fragement
                b.putString("langueChoisie", gestionLangues.getLangueChoisie());
                choixCategorieFragment.setArguments(b);


                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                //transaction.remove(fm.findFragmentById(R.id.fragement_choix_langues));

                transaction.replace(R.id.fragment_container, choixCategorieFragment);
                transaction.addToBackStack("returnToLangue");

                // Commit the transaction
                transaction.commit();

                //Changement du titre de pres utilisateur
                ((Home_activity) getActivity()).setPresUser("Selectionnez un thème et un mode :");

            }
        });



        return rootView;
    }




}