package com.example.ogrencievi2.Sayfalar.FirebaseClaslar;

public class Not {
    private String baslik;
    private String icerik;
    private String id;
    private String ekleyenKisi;
    private String evAdi;

    public Not(String baslik, String icerik, String id, String ekleyenKisi, String evAdi) {
        this.baslik = baslik;
        this.icerik = icerik;
        this.id = id;
        this.ekleyenKisi = ekleyenKisi;
        this.evAdi = evAdi;
    }
    public Not(){

    }

    public String getBaslik() {
        return baslik;
    }

    public void setBaslik(String baslik) {
        this.baslik = baslik;
    }

    public String getIcerik() {
        return icerik;
    }

    public void setIcerik(String icerik) {
        this.icerik = icerik;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEkleyenKisi() {
        return ekleyenKisi;
    }

    public void setEkleyenKisi(String ekleyenKisi) {
        this.ekleyenKisi = ekleyenKisi;
    }

    public String getEvAdi() {
        return evAdi;
    }

    public void setEvAdi(String evAdi) {
        this.evAdi = evAdi;
    }
}
