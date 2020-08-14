package com.example.ogrencievi2.Login;

public class Ev {
    private String evAdi;
    private String kisiAdi;
    private String sifre;
    private String ID;
    private String kisiresim;

    public Ev(String evAdi, String kisiAdi, String sifre,String ID,String kisiresim) {
        this.evAdi = evAdi;
        this.kisiAdi = kisiAdi;
        this.sifre = sifre;
        this.ID=ID;
        this.kisiresim=kisiresim;
    }
    public Ev(){


    }
    public String getKisiresim() {
        return kisiresim;
    }

    public void setKisiresim(String kisiresim) {
        this.kisiresim = kisiresim;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEvAdi() {
        return evAdi;
    }

    public void setEvAdi(String evAdi) {
        this.evAdi = evAdi;
    }

    public String getKisiAdi() {
        return kisiAdi;
    }

    public void setKisiAdi(String kisiAdi) {
        this.kisiAdi = kisiAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }
}
