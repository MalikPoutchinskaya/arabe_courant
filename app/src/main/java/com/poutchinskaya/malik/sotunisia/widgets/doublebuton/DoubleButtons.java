package com.poutchinskaya.malik.sotunisia.widgets.doublebuton;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.events.FilterEvent;
import com.poutchinskaya.malik.sotunisia.fragments.SearchFragment;

import org.greenrobot.eventbus.EventBus;

import static android.content.ContentValues.TAG;

/**
 * Double bouton avec deux actions: recherche et filtre
 */

public class DoubleButtons extends RelativeLayout {

    View rootView;

    public DoubleButtons(Context context) {
        super(context);
        init(context);
    }

    public DoubleButtons(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(final Context context) {
        rootView = inflate(context, R.layout.button_double, this);


        rootView.findViewById(R.id.action_search_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Return the fragment manager
                    // If using the Support lib.
                    // return activity.getSupportFragmentManager();

                    Fragment searchFragment = new SearchFragment();
                    // adding fragment to relative layout by using layout id
                    FragmentTransaction transaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(R.anim.enter_from_bottom, R.anim.exit_to_bottom);
                    transaction.replace(R.id.search_fragment, searchFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();

                } catch (ClassCastException e) {
                    Log.d(TAG, "Can't get the fragment manager with this");
                }

            }
        });

        rootView.findViewById(R.id.action_filter_fragment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: la manipulation de la liste de category ne permet pas de traiter l'event du click ici
                EventBus.getDefault().post(new FilterEvent());
            }
        });
    }
}