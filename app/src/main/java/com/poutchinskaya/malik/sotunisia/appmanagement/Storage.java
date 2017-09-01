package com.poutchinskaya.malik.sotunisia.appmanagement;

import android.content.Context;
import android.content.SharedPreferences;

/**
 *
 */

public class Storage {

    private Context context;

    private SharedPreferences sharedPreferences;

    public Storage(Context context) {
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(AppParamEnum.APP_PREFERENCE.name(), Context.MODE_PRIVATE);
    }

    public Long getLanguage() {
        return this.sharedPreferences.getLong(AppParamEnum.LANGUAGE.name(), 0);
    }

    public String getCategory() {
        return this.sharedPreferences.getString(AppParamEnum.CATEGORY.name(), null);
    }

    public String getScore() {
        return this.sharedPreferences.getString(AppParamEnum.SCORE.name(), null);
    }

}
