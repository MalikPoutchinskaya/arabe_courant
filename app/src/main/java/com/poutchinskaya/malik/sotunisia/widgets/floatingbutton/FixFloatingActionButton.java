package com.poutchinskaya.malik.sotunisia.widgets.floatingbutton;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;

/**
 * custom floating button pour fix le show()
 */

public class FixFloatingActionButton extends FloatingActionButton {
    public FixFloatingActionButton(Context context) {
        super(context);
    }

    public FixFloatingActionButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixFloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void hide() {
//        super.hide();
        //FIX: bug sur la
        this.setVisibility(INVISIBLE);
    }
}
