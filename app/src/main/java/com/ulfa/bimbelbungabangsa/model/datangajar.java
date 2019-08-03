package com.ulfa.bimbelbungabangsa.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class datangajar implements Serializable {
    private String pengajarId;
    private String pengajarId_User;
    private String pengajarName;
    private String pengajarPendidikan;
    private String pengajarNoHP;
    private String pengajarAlamat;
    private String pengajarMatpel;
    private String pengajarJeniskel;
    private String pengajarTanggalLahir;
    public String getPengajarId_user() {
        return pengajarId_User;
    }

    public void setPengajarId_user(String pengajarId_user) {
        this.pengajarId_User = pengajarId_user;
    }

    public String getPengajarJeniskel() {
        return pengajarJeniskel;
    }

    public void setPengajarJeniskel(String pengajarJenisKel) {
        this.pengajarJeniskel = pengajarJenisKel;
    }

    public String getPengajarTanggalLahir() {
        return pengajarTanggalLahir;
    }

    public void setPengajarTanggalLahir(String pengajarTanggalLahir) {
        this.pengajarTanggalLahir = pengajarTanggalLahir;
    }


    public String getPengajarId() {
        return pengajarId;
    }

    public void setPengajarId(String pengajarId) {
        this.pengajarId = pengajarId;
    }

    public String getPengajarName() {
        return pengajarName;
    }

    public void setPengajarName(String pengajarName) {
        this.pengajarName = pengajarName;
    }

    public String getPengajarPendidikan() {
        return pengajarPendidikan;
    }

    public void setPengajarPendidikan(String pengajarPendidikan) {
        this.pengajarPendidikan = pengajarPendidikan;
    }

    public String getPengajarNoHP() {
        return pengajarNoHP;
    }

    public void setPengajarNoHP(String pengajarNoHP) {
        this.pengajarNoHP = pengajarNoHP;
    }

    public String getPengajarAlamat() {
        return pengajarAlamat;
    }

    public void setPengajarAlamat(String pengajarAlamat) {
        this.pengajarAlamat = pengajarAlamat;
    }

    public String getPengajarMatpel() {
        return pengajarMatpel;
    }

    public void setPengajarMatpel(String pengajarMatpel) {
        this.pengajarMatpel = pengajarMatpel;
    }

    public datangajar(){
    }

    public datangajar(String pengajarName, String pengajarPendidikan, String pengajarNoHp, String pengajarAlamat, String pengajarMatpel, String pengajarID) {
        this.pengajarName = pengajarName;
        this.pengajarPendidikan = pengajarPendidikan;
        this.pengajarNoHP = pengajarNoHp;
        this.pengajarAlamat = pengajarAlamat;
        this.pengajarMatpel = pengajarMatpel;
        this.pengajarId = pengajarID;
    }






}