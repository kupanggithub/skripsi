package com.ulfa.bimbelbungabangsa.model;

import java.io.Serializable;

public class datanilai implements Serializable {
    private String artostId;
    private String artostId_user;
    private String artostMatpel;
    private String artostNilai;
    private String artostKeterangan;


    public String getArtostId() {
        return artostId;
    }

    public void setArtostId(String artostId) {
        this.artostId = artostId;
    }

    public String getArtostId_user() {
        return artostId_user;
    }

    public void setArtostId_user(String artostId_user) {
        this.artostId_user = artostId_user;
    }

    public String getArtostMatpel() {
        return artostMatpel;
    }

    public void setArtostMatpel(String artostMatpel) {
        this.artostMatpel = artostMatpel;
    }

    public String getArtostNilai() {
        return artostNilai;
    }

    public void setArtostNilai(String artostNilai) {
        this.artostNilai = artostNilai;
    }

    public String getArtostKeterangan() {
        return artostKeterangan;
    }

    public void setArtostKeterangan(String artostKeterangan) {
        this.artostKeterangan = artostKeterangan;
    }

    public  datanilai(){

    }

    public datanilai(String artostId, String artostId_user, String artostMatpel, String artostNilai, String artostKeterangan) {
        this.artostId = artostId;
        this.artostId_user = artostId_user;
        this.artostMatpel = artostMatpel;
        this.artostNilai = artostNilai;
        this.artostKeterangan = artostKeterangan;
    }
}
