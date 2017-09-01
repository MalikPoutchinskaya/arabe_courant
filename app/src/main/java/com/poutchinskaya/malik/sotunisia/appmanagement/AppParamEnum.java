package com.poutchinskaya.malik.sotunisia.appmanagement;

/**
 *
 */

public enum AppParamEnum {

    APP_PREFERENCE("app_preference"),
    CATEGORY ("category"),
    LANGUAGE ("language"),
    SCORE ("score");


    private String name = "";

    //Constructeur
    AppParamEnum(String name){
        this.name = name;
    }

    public String toString(){
        return name;
    }

}
