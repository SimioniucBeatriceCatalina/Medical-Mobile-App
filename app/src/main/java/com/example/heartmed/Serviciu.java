package com.example.heartmed;

import android.text.Editable;

public class Serviciu {

     private String id;
    private String denumire;
    private int pret;

    public Serviciu() {
    }

    public Serviciu(String id, String denumire, int pret) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
    }

    public String  getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public int getPret() {
        return pret;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Serviciu{" +
                "denumire=" + denumire +
                ", pret=" + pret +
                '}';
    }


}
