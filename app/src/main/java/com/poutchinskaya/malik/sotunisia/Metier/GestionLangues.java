package com.poutchinskaya.malik.sotunisia.Metier;

/**
 * Created by Malik on 21/12/2015.
 */
public class GestionLangues {

    final String Tn = "Tunisien";
    final String Al = "Algerien";
    final String Ma = "Marocain";

    public String langueChoisie = null;

    public GestionLangues(String langueChoisie) {
        this.langueChoisie = langueChoisie;
    }

    public GestionLangues() {
    }


    public String getLangueChoisie() {
        return langueChoisie;
    }

    public void setLangueChoisie(String langueChoisie) {
        this.langueChoisie = langueChoisie;
    }

    public String getTn() {
        return Tn;
    }

    public String getAl() {
        return Al;
    }

    public String getMa() {
        return Ma;
    }
}
