package com.ulfa.bimbelbungabangsa.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Artist {

    public String artistId_User;
    public String artistId;
    public String artistName;
    public String artistKelas;
    public String artistNoHP;
    public String artistAlamat;
    public String artistMatpel;
    public String artistJeniskel;
    public String artistTanggalLahir;

    public String getArtistJeniskel() {
        return artistJeniskel;
    }

    public void setArtistJeniskel(String artistJeniskel) {
        this.artistJeniskel = artistJeniskel;
    }

    public String getArtistTanggalLahir() {
        return artistTanggalLahir;
    }

    public void setArtistTanggalLahir(String artistTanggalLahir) {
        this.artistTanggalLahir = artistTanggalLahir;
    }


    public Artist(String id, String id_user, String name, String kelas, String nohp, String alamat, String matpel, String jeniskel, String tgllahir) {
        this.artistId = id;
        this.artistId_User = id_user;
        this.artistName = name;
        this.artistKelas = kelas;
        this.artistNoHP = nohp;
        this.artistAlamat = alamat;
        this.artistMatpel = matpel;
        this.artistJeniskel = jeniskel;
        this.artistTanggalLahir = tgllahir;
    }

    public String getartistId() {
        return artistId;
    }

    public String getartistName() {
        return artistName;
    }

    public String getartistKelas() {
        return artistKelas;
    }

    public String getartistNoHP() {
        return artistNoHP;
    }

    public String getartistAlamat() {
        return artistAlamat;
    }

    public String getartistMatpel() {
        return artistMatpel;
    }

    public String getArtistId_User() {
        return artistId_User;
    }

    public void setArtistId_User(String artistId_User) {
        this.artistId_User = artistId_User;
    }
}
