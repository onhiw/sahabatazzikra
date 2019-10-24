package com.ronirusmayadi.sahabatqu.Model;

public class ArtikelModel {
    public String getJudul() {
        return judul;
    }

    public String getPublish() {
        return publish;
    }

    public String getLink() {
        return link;
    }

    public int getGambar() {
        return gambar;
    }

    private int gambar;
    private String judul;
    private String publish;
    private String link;

    public ArtikelModel(String judul, String publish, int gambar, String link){
        this.judul = judul;
        this.publish = publish;
        this.link = link;
        this.gambar = gambar;
    }
}
