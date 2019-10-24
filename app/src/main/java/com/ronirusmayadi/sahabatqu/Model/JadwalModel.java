package com.ronirusmayadi.sahabatqu.Model;

public class JadwalModel {

    public String getDate_for() {
        return date_for;
    }

    public String getFajr() {
        return fajr;
    }

    public String getShurooq() {
        return shurooq;
    }

    public String getDhuhr() {
        return dhuhr;
    }

    public String getAsr() {
        return asr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public String getIsha() {
        return isha;
    }

    String date_for;
    String fajr;
    String shurooq;
    String dhuhr;
    String asr;
    String maghrib;
    String isha;



    public JadwalModel(String date_for, String fajr, String shurooq, String dhuhr, String asr, String maghrib, String isha){
        this.date_for = date_for;
        this.fajr = fajr;
        this.shurooq = shurooq;
        this.dhuhr = dhuhr;
        this.asr = asr;
        this.maghrib = maghrib;
        this.isha = isha;

    }
}
