package com.poutchinskaya.malik.sotunisia.widgets.floatingbutton;


import com.poutchinskaya.malik.sotunisia.model.Category;

import java.util.List;

/**
 * Call back de la reponse à l'exercice
 */

public interface IDialogFloatingResponse {
    void onSortSelected(List<Category> categories);
}
