package com.poutchinskaya.malik.sotunisia.widgets.graph;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.poutchinskaya.malik.sotunisia.R;

/**
 *
 */

public class ScoreLineChart extends LineChart {
    public ScoreLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public ScoreLineChart(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize();
    }

    public ScoreLineChart(Context context) {
        super(context);
        initialize();
    }

    /**
     * Initilise les paramètres du graph
     */
    private void initialize() {
        this.animateY(1000); //animation
        this.getLegend().setEnabled(false); //supp la legende
        this.disableScroll(); //supp le scroll sur le graph
        this.setTouchEnabled(false); //également : supp le scroll sur le graph
        //supp la description en bas de graph
        Description description = new Description();
        description.setText("");
        this.setDescription(description);
        //supp les axes
        this.getAxisLeft().setDrawLabels(false);
        this.getAxisRight().setDrawLabels(false);
        this.getXAxis().setDrawLabels(false);
        //supp le background
        this.setDrawGridBackground(false);
        //supp la grille
        this.getAxisLeft().setDrawGridLines(false);
        this.getAxisRight().setDrawGridLines(false);
        this.getXAxis().setDrawGridLines(false);
        //supp les bords
        this.getAxisRight().setDrawAxisLine(false);
        this.getAxisLeft().setDrawAxisLine(false);
        this.getXAxis().setDrawAxisLine(false);
        // (retire) définit le padding du chart
        this.setViewPortOffsets(0f, 20f, 0f, 20f);
        //description si pas de data
        this.setNoDataText("Exercez-vous pour voir votre progression");
        this.setNoDataTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

}
