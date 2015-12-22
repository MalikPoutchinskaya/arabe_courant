package com.poutchinskaya.malik.sotunisia.Presentation.Vocabulaire;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.poutchinskaya.malik.sotunisia.Metier.GestionLangues;
import com.poutchinskaya.malik.sotunisia.Metier.GestionMot;
import com.poutchinskaya.malik.sotunisia.Presentation.Home.Home_activity;
import com.poutchinskaya.malik.sotunisia.Presentation.Model.Mot;
import com.poutchinskaya.malik.sotunisia.Presentation.NavigationDrawerFragment;
import com.poutchinskaya.malik.sotunisia.R;

public class VocabulaireController extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

     public static String langueChoisie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vocabulaire);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        //Récupération de la langue choisie
        Intent intent = getIntent();
        langueChoisie = intent.getStringExtra("langueChoisie");

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 3:
                mTitle = getString(R.string.title_Accueil);
                Intent intent = new Intent(getApplicationContext(), Home_activity.class);
                startActivity(intent);
                break;
            case 2:
                mTitle = getString(R.string.title_Prononciation);
                Toast.makeText(getApplicationContext(),"Coming soon..",Toast.LENGTH_LONG).show();
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.vocabulaire, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_vocabulaire, container, false);



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
                    gestionMot.ecouteMot(false);


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

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((VocabulaireController) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }


}
