package com.poutchinskaya.malik.sotunisia.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;

import com.poutchinskaya.malik.sotunisia.R;
import com.poutchinskaya.malik.sotunisia.appmanagement.AppParamEnum;
import com.poutchinskaya.malik.sotunisia.helpers.EntityHelper;

public class ExerciceDoneFragment extends Fragment {
    /**
     * le score de l'exercice
     */
    private float score;
    private EntityHelper entityHelper;
    private long categoryId;
    boolean isNewRecord;

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialogfragment_exercice, container, false);
        entityHelper = new EntityHelper(view.getContext(), getActivity().getApplication());
        //recupere le score
        score = getArguments().getFloat(AppParamEnum.SCORE.name());
        //recuper l'id de la categorie
        categoryId = getArguments().getLong(AppParamEnum.CATEGORY.name());
        //nouveau record a feter?
        isNewRecord = entityHelper.isANewRecord(categoryId, score);
        if (isNewRecord){
            view.findViewById(R.id.exercice_new_record_ic).setVisibility(View.VISIBLE);
            view.findViewById(R.id.exercice_new_record_text).setVisibility(View.VISIBLE);
        }
        //on affiche le score
        ((RatingBar) view.findViewById(R.id.exercice_score_ratingbar)).setRating(score);
        final FragmentActivity fragmentActivity = getActivity();
        // Sauvegarde de la valeur et retour
        view.findViewById(R.id.dialogfragment_exercice_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewRecord) {
                    entityHelper.saveScore(score, categoryId);
                }

                fragmentActivity.finish();

            }
        });
        return view;
    }


}