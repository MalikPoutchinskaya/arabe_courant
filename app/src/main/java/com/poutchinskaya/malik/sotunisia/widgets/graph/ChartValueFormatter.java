package com.poutchinskaya.malik.sotunisia.widgets.graph;

import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.text.DecimalFormat;

/**
 * Formattage des données du graph
 */

public class ChartValueFormatter implements IValueFormatter {

    private DecimalFormat mFormat;

    public ChartValueFormatter() {
        mFormat = new DecimalFormat("###,###,##0"); // use zero decimal
    }

    @Override
    public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
        // write your logic here
//        return mFormat.format(value) + " £"; // e.g. append a dollar-sign
        return mFormat.format(value); // e.g. append a dollar-sign
    }
}