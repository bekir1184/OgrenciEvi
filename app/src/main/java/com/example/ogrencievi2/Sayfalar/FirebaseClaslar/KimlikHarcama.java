package com.example.ogrencievi2.Sayfalar.FirebaseClaslar;

public class KimlikHarcama {
     String kullaiciAdi;
     String kategori;
    int kategoriResim;
    int fiyat;

    public KimlikHarcama(String kullaiciAdi, String kategori, int kategoriResim, int fiyat) {
        this.kullaiciAdi = kullaiciAdi;
        this.kategori = kategori;
        this.kategoriResim = kategoriResim;
        this.fiyat = fiyat;
    }

    public String getKullaiciAdi() {
        return kullaiciAdi;
    }

    public void setKullaiciAdi(String kullaiciAdi) {
        this.kullaiciAdi = kullaiciAdi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public int getKategoriResim() {
        return kategoriResim;
    }

    public void setKategoriResim(int kategoriResim) {
        this.kategoriResim = kategoriResim;
    }

    public int getFiyat() {
        return fiyat;
    }

    public void setFiyat(int fiyat) {
        this.fiyat = fiyat;
    }
}
