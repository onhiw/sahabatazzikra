package com.ronirusmayadi.sahabatqu.Model;

public class TransaksiModel {
    private String total_donasi;

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }

    private String idMember;

    public TransaksiModel(){

    }

    public String getTotal_donasi() {
        return total_donasi;
    }

    public void setTotal_donasi(String total_donasi) {
        this.total_donasi = total_donasi;
    }

    public String getTgl() {
        return tgl;
    }

    public void setTgl(String tgl) {
        this.tgl = tgl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private String tgl;
    private String status;

    public TransaksiModel(String idMember,String total_donasi, String tgl, String status){
        this.idMember = idMember;
        this.total_donasi = total_donasi;
        this.tgl = tgl;
        this.status = status;
    }

}
