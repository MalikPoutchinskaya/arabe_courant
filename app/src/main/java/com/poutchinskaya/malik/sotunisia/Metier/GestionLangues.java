package com.poutchinskaya.malik.sotunisia.Metier;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Malik on 21/12/2015.
 */
public class GestionLangues {

    final String Tn = "Tunisien";
    final String Al = "Algerien";
    final String Ma = "Marocain";

    private Context context;

    public String langueChoisie = null;

    public GestionLangues() {
    }

    //Remet à non selectionné les boutons, textes et images
    public void razChoix(ArrayList<ImageButton> listButton, ArrayList<TextView> textViews, ImageView imageView) {
        for (ImageButton imageButton : listButton
                ) {
            imageButton.setSelected(false);
        }

        for (TextView textView : textViews
                ) {
            textView.setVisibility(View.INVISIBLE);
        }

        imageView.setVisibility(View.INVISIBLE);
    }




    public String getLangueChoisie() {
        return langueChoisie;
    }

    public void setLangueChoisie(String langueChoisie) {
        this.langueChoisie = langueChoisie;
    }

    public String getTn() {
        return Tn;
    }

    public String getAl() {
        return Al;
    }

    public String getMa() {
        return Ma;
    }
}
