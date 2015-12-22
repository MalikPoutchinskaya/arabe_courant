package com.poutchinskaya.malik.sotunisia.Presentation.Home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.Metier.GestionLangues;
import com.poutchinskaya.malik.sotunisia.R;

/**
 * Created by Malik on 26/11/2015.
 */
public class ChoixLanguesFragment extends Fragment {
    ImageView flagTn;
    ImageView flagTnSelected;
    ImageView flagNext;
    TextView tunisien;
    ChoixCategorieFragment choixCategorieFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.framgement_choix_langues, container, false);

        final GestionLangues languesChoisie = new GestionLangues();
        final Bundle b = new Bundle();

        flagTn = (ImageView) rootView.findViewById(R.id.imageViewTn);
        flagTnSelected = (ImageView) rootView.findViewById(R.id.imageViewTnSelected);
        flagNext = (ImageView) rootView.findViewById(R.id.imageViewNext);
        tunisien = (TextView) rootView.findViewById(R.id.textTn);

        choixCategorieFragment = new ChoixCategorieFragment();
        final FragmentManager fm = getFragmentManager();
        final FragmentTransaction transaction = fm.beginTransaction();



        // Tunisien
        final ImageView loginButtonTn = (ImageView) rootView.findViewById(R.id.imageViewTn);
        final ImageView loginButtonSelected = (ImageView) rootView.findViewById(R.id.imageViewTnSelected);

        loginButtonTn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flagTn.setVisibility(View.INVISIBLE);
                flagTnSelected.setVisibility(View.VISIBLE);
                flagNext.setVisibility(View.VISIBLE);
                tunisien.setVisibility(View.VISIBLE);

                languesChoisie.setLangueChoisie(languesChoisie.getTn());
            }
        });

        loginButtonSelected.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                flagTn.setVisibility(View.VISIBLE);
                flagTnSelected.setVisibility(View.INVISIBLE);
                flagNext.setVisibility(View.INVISIBLE);
                tunisien.setVisibility(View.INVISIBLE);

            }
        });

        // Next
        final ImageView loginButtonNext = (ImageView) rootView.findViewById(R.id.imageViewNext);
        loginButtonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                //Envoie des données au prochain fragement
                // Pour les récupérés
                b.putString("langueChoisi", languesChoisie.getLangueChoisie());
                choixCategorieFragment.setArguments(b);



                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.replace(R.id.fragement_choix_langues, choixCategorieFragment);
                //transaction.addToBackStack(null);

                // Commit the transaction
                transaction.commit();

                //Changement du titre de pres utilisateur
                ((Home_activity) getActivity()).setPresUser("Selectionnez une catégorie :");

            }
        });

        return rootView;
    }
}