package com.example.ogrencievi2.Sayfalar.FirebaseClaslar;

public class HarcamaBilgi {
    private String evAdi;
    private String harcamaYapanKisi;
    private String  fiyat;
    private String kategori;
    private int kisiResim;
    private int kategoriResim;
    private String ID;


    public HarcamaBilgi(String evAdi, String harcamaYapanKisi, String fiyat, String kategori,int kategoriResim,int kisiResim,String ID) {
        this.evAdi = evAdi;
        this.harcamaYapanKisi = harcamaYapanKisi;
        this.fiyat = fiyat;
        this.kategori = kategori;
        this.kisiResim=kisiResim;
        this.kategoriResim=kategoriResim;
        this.ID=ID;

    }
    public HarcamaBilgi(){
        
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getKisiResim() {
        return kisiResim;
    }

    public void setKisiResim(int kisiResim) {
        this.kisiResim = kisiResim;
    }

    public int getKategoriResim() {
        return kategoriResim;
    }

    public void setKategoriResim(int kategoriResim) {
        this.kategoriResim = kategoriResim;
    }

    public String getEvAdi() {
        return evAdi;
    }

    public void setEvAdi(String evAdi) {
        this.evAdi = evAdi;
    }

    public String getHarcamaYapanKisi() {
        return harcamaYapanKisi;
    }

    public void setHarcamaYapanKisi(String harcamaYapanKisi) {
        this.harcamaYapanKisi = harcamaYapanKisi;
    }

    public String getFiyat() {
        return fiyat;
    }

    public void setFiyat(String fiyat) {
        this.fiyat = fiyat;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
