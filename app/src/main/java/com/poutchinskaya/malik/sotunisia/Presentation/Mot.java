package com.poutchinskaya.malik.sotunisia.Presentation;

import android.media.MediaPlayer;

/**
 * Created by Malik on 21/11/2015.
 */
public class Mot {
    private String tunisien;
    private String francais;
    private String audioCode;
    private String phonetique;
    private MediaPlayer audioPlayer;

    //Constructeur

    public Mot(String tunisien, String francais, MediaPlayer audio, String phonetique) {
        this.tunisien = tunisien;
        this.francais = francais;
        this.audioPlayer = audio;
        this.phonetique = phonetique;
    }

    //Getter Setter

    public String getTunisien() {
        return tunisien;
    }

    public void setTunisien(String tunisien) {
        this.tunisien = tunisien;
    }

    public String getFrancais() {
        return francais;
    }

    public void setFrancais(String francais) {
        this.francais = francais;
    }

    public String getAudioCode() {
        return audioCode;
    }

    public void setAudioCode(String audioCode) {
        this.audioCode = audioCode;
    }

    public String getPhonetique() {
        return phonetique;
    }

    public void setPhonetique(String phonetique) {
        this.phonetique = phonetique;
    }

    public MediaPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public void setAudioPlayer(MediaPlayer audioPlayer) {
        this.audioPlayer = audioPlayer;
    }
}

