package com.ulfa.bimbelbungabangsa.model;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Pengajar {

    public String pengajarId_User;
    public String pengajarId;
    public String pengajarName;
    public String pengajarPendidikan;
    public String pengajarNoHP;
    public String pengajarAlamat;
    public String pengajarMatpel;
    public String pengajarJeniskel;
    public String pengajarTanggalLahir;

    public String getPengajarJeniskel() {
        return pengajarJeniskel;
    }

    public void setPengajarJeniskel(String pengajarJeniskel) {
        this.pengajarJeniskel = pengajarJeniskel;
    }

    public String getPengajarTanggalLahir() {
        return pengajarTanggalLahir;
    }

    public void setPengajarTanggalLahir(String pengajarTanggalLahir) {
        this.pengajarTanggalLahir = pengajarTanggalLahir;
    }


    public Pengajar(String id, String id_user, String name, String pendidikan, String nohp, String alamat, String matpel, String jeniskel, String tgllahir) {
        this.pengajarId = id;
        this.pengajarId_User = id_user;
        this.pengajarName = name;
        this.pengajarPendidikan = pendidikan;
        this.pengajarNoHP = nohp;
        this.pengajarAlamat = alamat;
        this.pengajarMatpel = matpel;
        this.pengajarJeniskel = jeniskel;
        this.pengajarTanggalLahir = tgllahir;
    }

    public String getpengajarId() {
        return pengajarId;
    }

    public String getpengajarName() {
        return pengajarName;
    }

    public String getpengajarKelas() {
        return pengajarPendidikan;
    }

    public String getpengajarNoHP() {
        return pengajarNoHP;
    }

    public String getpengajarAlamat() {
        return pengajarAlamat;
    }

    public String getpengajarMatpel() {
        return pengajarMatpel;
    }

    public String getPengajarId_User() {
        return pengajarId_User;
    }

    public void setPengajarId_User(String pengajarId_User) {
        this.pengajarId_User = pengajarId_User;
    }
}
