package com.ronirusmayadi.sahabatqu.Model;

public class User {

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getJmlh_saldo() {
        return jmlh_saldo;
    }

    public void setJmlh_saldo(String jmlh_saldo) {
        this.jmlh_saldo = jmlh_saldo;
    }

    public String nama;
    public String email;
    public String photo;
    public String jmlh_saldo;

    public User(String mNama, String mEmail, String mPoto, String mSaldo){
        this.nama = mNama;
        this.email = mEmail;
        this.photo = mPoto;
        this.jmlh_saldo = mSaldo;
    }

    public User(){}
}
