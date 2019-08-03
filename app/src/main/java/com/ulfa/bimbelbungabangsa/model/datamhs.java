package com.ulfa.bimbelbungabangsa.model;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
@IgnoreExtraProperties
public class datamhs implements Serializable {
    private String artistId;
    private String artistId_User;
    private String artistName;
    private String artistKelas;
    private String artistNoHP;
    private String artistAlamat;
    private String artistMatpel;
    private String artistJeniskel;
    private String artistTanggalLahir;
    public String getArtistId_user() {
        return artistId_User;
    }

    public void setArtistId_user(String artistId_user) {
        this.artistId_User = artistId_user;
    }

    public String getArtistJeniskel() {
        return artistJeniskel;
    }

    public void setArtistJeniskel(String artistJenisKel) {
        this.artistJeniskel = artistJenisKel;
    }

    public String getArtistTanggalLahir() {
        return artistTanggalLahir;
    }

    public void setArtistTanggalLahir(String artistTanggalLahir) {
        this.artistTanggalLahir = artistTanggalLahir;
    }


    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistKelas() {
        return artistKelas;
    }

    public void setArtistKelas(String artistKelas) {
        this.artistKelas = artistKelas;
    }

    public String getArtistNoHP() {
        return artistNoHP;
    }

    public void setArtistNoHP(String artistNoHP) {
        this.artistNoHP = artistNoHP;
    }

    public String getArtistAlamat() {
        return artistAlamat;
    }

    public void setArtistAlamat(String artistAlamat) {
        this.artistAlamat = artistAlamat;
    }

    public String getArtistMatpel() {
        return artistMatpel;
    }

    public void setArtistMatpel(String artistMatpel) {
        this.artistMatpel = artistMatpel;
    }

    public datamhs(){
    }

    public datamhs(String artistName, String artistKelas, String artistNoHp, String artisAlamat, String artisMatpel, String artisID) {
        this.artistName = artistName;
        this.artistKelas = artistKelas;
        this.artistNoHP = artistNoHp;
        this.artistAlamat = artisAlamat;
        this.artistMatpel = artisMatpel;
        this.artistId = artisID;
    }






}