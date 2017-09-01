package com.poutchinskaya.malik.sotunisia.fragments;


import com.poutchinskaya.malik.sotunisia.model.Word;

/**
 * Call back de la reponse à l'exercice
 */

public interface IExerciceResponse {
    void onResponseSelected(int position, Word word);
}
