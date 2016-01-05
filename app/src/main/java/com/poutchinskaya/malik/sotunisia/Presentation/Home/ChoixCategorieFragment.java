package com.poutchinskaya.malik.sotunisia.Presentation.Home;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
    ChoixOptionVocabFragment choixOptionVocabFragment;

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

                //Instance bundle pour transmition de la langue
                final Bundle b = new Bundle();
                choixOptionVocabFragment = new ChoixOptionVocabFragment();
                final FragmentManager fm = getFragmentManager();
                final FragmentTransaction transaction = fm.beginTransaction();


                //Envoie des données au prochain fragement
                b.putString("langueChoisie", langueChoisie);
                //Je catch l'erreur si pas de nouvelle langue choisie
                try {
                    choixOptionVocabFragment.setArguments(b);
                }catch(Exception e){
                    Log.e("Choix Categorie", "Argument déja instancié" + e);
                }


                // Replace whatever is in the fragment_container view with this fragment,
                // and add the transaction to the back stack so the user can navigate back
                transaction.setCustomAnimations(R.anim.enter_from_left, R.anim.exit_to_right);
                transaction.replace(R.id.fragment_choix_home, choixOptionVocabFragment);

                // Commit the transaction
                transaction.commit();

                //Changement du titre de pres utilisateur
                ((Home_activity) getActivity()).setPresUser("Selectionnez un thème et un mode :");


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