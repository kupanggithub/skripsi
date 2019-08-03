package com.ulfa.bimbelbungabangsa.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Artost {
    public String artostId;
    public String artostId_user;
    public String artostMatpel;
    public String artostNilai;
    public String artostKeterangan;


    public Artost(String id, String id_user,String matpel, String nilai1, String keterangan) {
        this.artostId = id;
        this.artostId_user = id_user;
        this.artostMatpel = matpel;
        this.artostNilai = nilai1;
        this.artostKeterangan = keterangan;
    }

    public String getartostId() {
        return artostId;
    }

    public String getArtostId_user() {
        return artostId_user;
    }

    public void setArtostId_user(String artostId_user) {
        this.artostId_user = artostId_user;
    }
    public String getartostMatpel() {
        return artostMatpel;
    }

    public String getartostNilai() {
        return artostNilai;
    }

    public String getartostKeterangan() {
        return artostKeterangan;
    }
}
