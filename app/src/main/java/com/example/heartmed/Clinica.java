package com.example.heartmed;

public class Clinica {




    private String descriere;
    private String email;
    private String nrTelefon;
    private String Email;
    private String adresa;

    public Clinica() {
    }

    public Clinica(String descriere, String email, String nrTelefon, String email1, String adresa) {
        this.descriere = descriere;
        this.email = email;
        this.nrTelefon = nrTelefon;
        Email = email1;
        this.adresa = adresa;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    @Override
    public String toString() {
        return "Clinica{" +
                "descriere='" + descriere + '\'' +
                ", email='" + email + '\'' +
                ", nrTelefon='" + nrTelefon + '\'' +
                ", Email='" + Email + '\'' +
                ", adresa='" + adresa + '\'' +
                '}';
    }
}
