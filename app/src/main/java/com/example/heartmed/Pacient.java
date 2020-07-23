package com.example.heartmed;

import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


public class Pacient   {


    private String tip;

    private String idP;

    private String image;

    private String nume;

    private String prenume;
    private String email;
    private String parola;
    private String token;
    private String adresa;
    private  String telefon;

    @Ignore
    public Pacient() {
    }

    public Pacient(String tip, String image, String nume, String prenume, String email, String parola, String adresa, String telefon) {
        this.tip = tip;
        this.image = image;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    @Ignore
    public Pacient(String tip, String idP, String image, String nume, String prenume, String email, String parola, String token, String adresa, String telefon) {
        this.tip = tip;
        this.idP = idP;
        this.image = image;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.token = token;
        this.adresa = adresa;
        this.telefon = telefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIdP() {
        return idP;
    }

    public void setIdP(String id) {
        this.idP = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return "Pacient{" +
                "tip='" + tip + '\'' +
                ", idP='" + idP + '\'' +
                ", image='" + image + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                ", token='" + token + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                '}';
    }
}
