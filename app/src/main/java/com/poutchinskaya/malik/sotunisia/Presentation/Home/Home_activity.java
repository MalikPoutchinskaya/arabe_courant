package com.poutchinskaya.malik.sotunisia.Presentation.Home;

/**
 * Created by Malik on 24/08/2015.
 */

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;

import com.poutchinskaya.malik.sotunisia.R;

public class Home_activity extends FragmentActivity {

    TextView PresUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

    }

    public void setPresUser(String text){
        PresUser = (TextView) findViewById(R.id.textViewPresUtilisateur);
        PresUser.setText(text);
    }

    //Permet de revenir sur le choix des langues
    @Override
    public void onBackPressed(){
        //FragmentManager fm = getSupportFragmentManager();
        //if (fm.getBackStackEntryCount() > 1) {
            getFragmentManager().popBackStack();
        //} else {
        //    finish();
        //}
    }


}