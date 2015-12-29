package com.poutchinskaya.malik.sotunisia.Presentation.Model;

import android.media.MediaPlayer;

/**
 * Created by Malik on 27/12/2015.
 */
public class Prononciation {
    String lettreArabe;
    String lettrePhonetique;
    MediaPlayer lettreAudio;
    String lettreDetails;

    public Prononciation(String lettreArabe, String lettrePhonetique, MediaPlayer lettreAudio, String lettreDetails) {
        this.lettreArabe = lettreArabe;
        this.lettrePhonetique = lettrePhonetique;
        this.lettreAudio = lettreAudio;
        this.lettreDetails = lettreDetails;
    }

    //Getter and Setter

    public String getLettreArabe() {
        return lettreArabe;
    }

    public void setLettreArabe(String lettreArabe) {
        this.lettreArabe = lettreArabe;
    }

    public String getLettrePhonetique() {
        return lettrePhonetique;
    }

    public void setLettrePhonetique(String lettrePhonetique) {
        this.lettrePhonetique = lettrePhonetique;
    }

    public MediaPlayer getLettreAudio() {
        return lettreAudio;
    }

    public void setLettreAudio(MediaPlayer lettreAudio) {
        this.lettreAudio = lettreAudio;
    }

    public String getLettreDetails() {
        return lettreDetails;
    }

    public void setLettreDetails(String lettreDetails) {
        this.lettreDetails = lettreDetails;
    }
}
