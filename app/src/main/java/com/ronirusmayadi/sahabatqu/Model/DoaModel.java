package com.ronirusmayadi.sahabatqu.Model;

public class DoaModel {

    private String nama;
    private String arab;
    private String arti;
    private String nomor;

    public String getNomor() {
        return nomor;
    }

    public String getNama() {
        return nama;
    }

    public String getArab() {
        return arab;
    }

    public String getArti() {
        return arti;
    }

    public DoaModel(String nomor, String nama, String arab, String arti){
        this.nomor = nomor;
        this.nama = nama;
        this.arab = arab;
        this.arti = arti;
    }

}
