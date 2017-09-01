package com.poutchinskaya.malik.sotunisia.widgets.graph;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet;
import com.poutchinskaya.malik.sotunisia.R;

import java.util.List;

/**
 *
 */

public class ScoreLineDataSet extends LineDataSet {
    private Context context;

    public ScoreLineDataSet(List<Entry> yVals, String label, Context context) {
        super(yVals, label);
        this.context = context;
        init();
    }

    /**
     * initialise les paramètres de la ligne du graphe
     */
    private void init(){
        this.setColor(ContextCompat.getColor(context, R.color.white)); //couleur de la ligne
        this.setLineWidth(2f); // epaisseur de ligne
        this.setDrawValues(true); // garde les valeurs
        this.setValueTextColor(ContextCompat.getColor(context, R.color.white)); //couleur des valeurs
        this.setValueFormatter(new ChartValueFormatter()); //formatte les valeurs
        this.setValueTextSize(12f); //taille de police des valeurs
        this.setDrawCircles(false); // retire les cercles represantant les valeurs
        this.setDrawFilled(true); // affiche un graph plein
        this.setMode(Mode.CUBIC_BEZIER); // Style de courbe arrondis
        // remplissage en fading
//        Drawable gradientDrawable = ContextCompat.getDrawable(context, R.drawable.fade_chart);
//        this.setFillDrawable(gradientDrawable);

        //autres styles non utilisé
        this.setFillColor(ContextCompat.getColor(context, R.color.white)); // couleur du remplissage (si pas de fading)
//        this.setValueTextColor(ContextCompat.getColor(context, R.color.orange)); // couleur des valeurs
//        this.setValueTextSize(15f); // font size

    }
}
