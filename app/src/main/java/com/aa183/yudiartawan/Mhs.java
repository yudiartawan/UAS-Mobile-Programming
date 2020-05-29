package com.aa183.yudiartawan;

import java.util.Date;

public class Mhs {

    private int idMhs;
    private String nim;
    private String nama;
    private String gambar;
    private String noTelp;
    private String prodi;
    private String statusTransfer;
    private String statusInvestasi;
    private String kampus;
    private String statusKelas;
    private String konsentrasi;


    public Mhs(int idMhs, String nim, String gambar, String noTelp, String nama, String prodi, String statusTransfer, String statusInvestasi, String kampus, String statusKelas, String konsentrasi) {
        this.idMhs = idMhs;
        this.nim = nim;
        this.gambar = gambar;
        this.noTelp = noTelp;
        this.nama = nama;
        this.prodi = prodi;
        this.statusTransfer = statusTransfer;
        this.statusInvestasi = statusInvestasi;
        this.kampus = kampus;
        this.konsentrasi = konsentrasi;
        this.statusKelas = statusKelas;
    }

    //id
    public int getIdMhs() {
        return idMhs;
    }

    public void setIdMhs(int idMhs) {
        this.idMhs = idMhs;
    }

    //nim
    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    //gambar
    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    //notelp
    public String getNoTelp() {
        return noTelp;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    //nama
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    //prodi
    public String getProdi() {
        return prodi;
    }

    public void setProdi(String prodi) {
        this.prodi = prodi;
    }

    //status tf
    public String getStatusTransfer() {
        return statusTransfer;
    }

    public void setStatusTransfer(String statusTransfer) {
        this.statusTransfer = statusTransfer;
    }

    //status inv
    public String getStatusInvestasi() {
        return statusInvestasi;
    }

    public void setStatusInvestasi(String statusInvestasi) {
        this.statusInvestasi = statusInvestasi;
    }

    //kampus
    public String getKampus() {
        return kampus;
    }

    public void setKampus(String kampus) {
        this.kampus = kampus;
    }


    //konsentrasi
    public String getKonsentrasi() {
        return konsentrasi;
    }

    public void setKonsentrasi(String konsentrasi) {
        this.konsentrasi = konsentrasi;
    }

    //statsu kelas
    public String getStatusKelas() {
        return statusKelas;
    }

    public void setStatusKelas(String statusKelas) {
        this.statusKelas = statusKelas;
    }


}
