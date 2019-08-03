package com.ulfa.bimbelbungabangsa.model;

public class jadwal {
    public String id_jadwal;
   public  String hari;
    public String nama_guru;
    public String matpell;
    public String kelass;
    public String jam_mulai;
    public String jam_selesai;

    public jadwal(){

    }

    public jadwal(String id, String hari, String nama_guru, String matpell, String kelass, String jam_mulai, String jam_selesai) {
        this.id_jadwal = id;
        this.hari = hari;
        this.nama_guru = nama_guru;
        this.matpell = matpell;
        this.kelass = kelass;
        this.jam_mulai = jam_mulai;
        this.jam_selesai = jam_selesai;
    }


    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public String getNama_guru() {
        return nama_guru;
    }

    public void setNama_guru(String nama_guru) {
        this.nama_guru = nama_guru;
    }

    public String getMatpell() {
        return matpell;
    }

    public void setMatpell(String matpell) {
        this.matpell = matpell;
    }

    public String getKelass() {
        return kelass;
    }

    public void setKelass(String kelass) {
        this.kelass = kelass;
    }

    public String getJam_mulai() {
        return jam_mulai;
    }

    public void setJam_mulai(String jam_mulai) {
        this.jam_mulai = jam_mulai;
    }

    public String getJam_selesai() {
        return jam_selesai;
    }

    public void setJam_selesai(String jam_selesai) {
        this.jam_selesai = jam_selesai;
    }

    public String getId_jadwal() {
        return id_jadwal;
    }

    public void setId_jadwal(String id_jadwal) {
        this.id_jadwal = id_jadwal;
    }
}
