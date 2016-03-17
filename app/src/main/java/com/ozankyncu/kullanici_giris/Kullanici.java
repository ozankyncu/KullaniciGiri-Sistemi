package com.ozankyncu.kullanici_giris;

/**
 * Created by ozankoyuncu on 17.3.2016.
 */
public class Kullanici {
    private long id;
    private String kullanici_adi;
    private String sifre;

    public Kullanici(String kullanici_adi, String sifre) {
        this.kullanici_adi = kullanici_adi;
        this.sifre = sifre;
    }

    public long getId() {
        return id;
    }


    public Kullanici(){}

    public String getKullanici_adi() {
        return kullanici_adi;
    }

    public void setKullanici_adi(String kullanici_adi) {
        this.kullanici_adi = kullanici_adi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public void setId(long id) {
        this.id = id;
    }
}
