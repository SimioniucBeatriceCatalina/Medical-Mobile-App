package com.example.heartmed;

import android.media.Image;
import android.net.wifi.WifiManager;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "medic")
public class Medic  implements Serializable {
    private String image;
    private String  idP;
    private String nume;
    private String prenume;
    private String email;
    private String parola;
    private String adresa;
    private String telefon;
    private String grad;
    private String pregatire;
    String tip;
@Ignore
    public Medic() {
    }

    public Medic(String image, String nume, String prenume, String email, String parola, String adresa, String telefon, String grad, String pregatire, String tip) {
        this.image = image;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.adresa = adresa;
        this.telefon = telefon;
        this.grad = grad;
        this.pregatire = pregatire;
        this.tip = tip;
    }

    public Medic(String image, String idP, String nume, String prenume, String email, String parola, String adresa, String telefon, String grad, String pregatire, String tip) {
        this.image = image;
        this.idP = idP;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.parola = parola;
        this.adresa = adresa;
        this.telefon = telefon;
        this.grad = grad;
        this.pregatire = pregatire;
        this.tip = tip;
    }

    @Ignore


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

    @Ignore

    public String getIdP() {
        return idP;
    }

    public void setIdP(String idP) {
        this.idP = idP;
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

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }


    public String getTip() {
        return tip;
    }

    public void setTip(String  tip ) {
        this.tip=tip;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getPregatire() {
        return pregatire;
    }

    public void setPregatire(String pregatire) {
        this.pregatire = pregatire;
    }

    @Override
    public String toString() {
        return "Medic{" +
                "image='" + image + '\'' +
                ", idP='" + idP + '\'' +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", email='" + email + '\'' +
                ", parola='" + parola + '\'' +
                ", adresa='" + adresa + '\'' +
                ", telefon='" + telefon + '\'' +
                ", grad='" + grad + '\'' +
                ", pregatire='" + pregatire + '\'' +
                ", tip='" + tip + '\'' +
                '}';
    }
}
