package com.ronirusmayadi.sahabatqu.Model;

public class QuranModel {

    public String getNama() {
        return nama;
    }

    public String getAsma() {
        return asma;
    }

    public String getArti() {
        return arti;
    }

    public String getAyat() {
        return ayat;
    }

    String nama;
    String asma;
    String arti;
    String ayat;

    public String getNomor() {
        return nomor;
    }

    String nomor;

    public String getAudio() {
        return audio;
    }

    String audio;

    public QuranModel(String nama, String asma, String arti, String ayat, String nomor, String audio){
        this.nama = nama;
        this.asma = asma;
        this.arti = arti;
        this.ayat = ayat;
        this.nomor = nomor;
        this.audio = audio;

    }
}
