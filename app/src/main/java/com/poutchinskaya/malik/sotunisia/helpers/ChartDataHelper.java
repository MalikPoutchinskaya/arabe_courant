package com.poutchinskaya.malik.sotunisia.helpers;

import android.app.Application;
import android.content.Context;

import com.github.mikephil.charting.data.Entry;
import com.poutchinskaya.malik.sotunisia.model.Score;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/**
 *  Helper pour le calcul de la progression de l'utilisateur
 */

public class ChartDataHelper {
    private EntityHelper entityHelper;
    private Context context;
    private Application application;
    private Score[] dataObjects;
    /**
     * Tous les scores de l'application
     */
    private List<Score> scores;
    /**
     * les scores additionées en fonction de l'algo
     */
    private List<Score> accumulatedScores;
    /**
     * les données prisent en compte par le chart
     */
    private List<Entry> entries;
    /**
     * Liste de score déjà dont le déltat à déja été calculé
     */
    List<Score> passedScores;
    /**
     * le score cumulé
     */
    float cummulatedScore;


    /**
     * Constructeur
     * @param context le context
     * @param application l'application
     */
    public ChartDataHelper(Context context, Application application) {
        this.context = context;
        this.application = application;
    }

    /**
     * Calcul la progression de l'utilisateur en fonction du score pour chaque catégorie de mot
     * @return la liste des résultats de progression de l'utilisateur
     */
    public List<Entry> getProgressionEntries() {
        init();
        computeProgression();
        return entries;
    }

    /**
     * Initialise les variables
     */
    private void init() {
        entityHelper = new EntityHelper(context, application);
        entries = new ArrayList<Entry>();
        scores = new ArrayList<>(entityHelper.getScoresByLanguage());
        accumulatedScores = new ArrayList<>();
        passedScores = new ArrayList<>();
        cummulatedScore = 0;
    }

    /**
     * Calcul la progression de  l'utilisateur
     */
    private void computeProgression() {
        //parcours de tous les scores recupérés de la bdd
        for (Score score : scores) {
            //creation du score a accumuler
            Score scoreToAdd = copyScore(score);
            cummulatedScore += computeDeltaByCategory(scoreToAdd);
            scoreToAdd.setValue(cummulatedScore);

            accumulatedScores.add(scoreToAdd);
        }

        fillEntries();
    }

    /**
     * transforme les résultats en données pour le chart
     */
    private void fillEntries() {
        if (accumulatedScores.size() > 2) {
            Collections.sort(accumulatedScores, new Comparator<Score>() {
                @Override
                public int compare(Score c1, Score c2) {
                    return Math.round(c1.getDate() - c2.getDate()); // Ascending
                }
            });
        }

        dataObjects = new Score[accumulatedScores.size()];
        int i = 0;
        for (Score score : accumulatedScores) {
            score.setDate(i);
            dataObjects[i] = score;
            i++;
        }

        for (Score data : dataObjects) {
            // turn your data into Entry objects
            entries.add(new Entry(data.getDate(), data.getValue()));
        }
    }


    /**
     * Calcul la différence du score entre un ancien score par category
     *
     * @param score
     * @return soit la différence si un ancien score existe pour une categorie, soit le score
     */
    private float computeDeltaByCategory(Score score) {
        //on ajoute le score qui vient d'etre passé à la liste score passé
        Score scorePassed = copyScore(score);


        //si le score n'est pas le premier..
        if (passedScores.size() > 1) {
            //on trie la liste des scores passés par date descendantes
            Collections.sort(passedScores, new Comparator<Score>() {
                @Override
                public int compare(Score c1, Score c2) {
                    return Math.round(c2.getDate() - c1.getDate()); // Descending
                }
            });

            //On parcours les scores passés
            for (Score oldScore : passedScores) {
                //Si une category est deja renseigné
                if (Objects.equals(oldScore.getCategoryId(), scorePassed.getCategoryId())) {
                    //on calcule le delta (forcément positif car on sauvegarde le réultat
                    // que si suppérieur à un score précédent
                    return scorePassed.getValue() - oldScore.getValue();
                }
            }
        }

        //on ajoute le score au score déjà validé
        passedScores.add(scorePassed);

        return scorePassed.getValue();
    }

    /**
     * copie un score
     *
     * @param scoreToCopy le score à copier
     * @return une copie du score
     */
    private Score copyScore(Score scoreToCopy) {
        Score scoreCopied = new Score();
        scoreCopied.setCategoryId(scoreToCopy.getCategoryId());
        scoreCopied.setDate(scoreToCopy.getDate());
        scoreCopied.setValue(scoreToCopy.getValue());
        return scoreCopied;
    }
}
