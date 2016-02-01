package com.poutchinskaya.malik.sotunisia.Presentation.Home;

/**
 * Created by Malik on 24/08/2015.
 */

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.R;

public class Home_activity extends FragmentActivity {

    TextView PresUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        ChoixLanguesFragment choixLanguesFragment = new ChoixLanguesFragment();
        fragmentTransaction.add(R.id.fragment_container, choixLanguesFragment, "choixLangue");
        fragmentTransaction.commit();

    }

    public void setPresUser(String text){
        PresUser = (TextView) findViewById(R.id.textViewPresUtilisateur);
        PresUser.setText(text);
    }

    //Permet de revenir sur le choix des langues
    @Override
    public void onBackPressed() {

        int count = getFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getFragmentManager().popBackStack();
        }

    }


}