package com.example.heartmed;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "medicament")
public class Medicament  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idMedicament")
    private long id;

    @ColumnInfo(name = "denumirem")
    private String denumire;
    @ColumnInfo(name = "concentratiem")
    private String concentratie;

    @ColumnInfo(name = "observatiim")
    private String observatii;

    public Medicament() {
    }

    public Medicament(String denumire,String concentratie, String observatii) {
        this.denumire = denumire;
        this.concentratie = concentratie;
        this.observatii = observatii;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getConcentratie() {
        return concentratie;
    }

    public void setConcentratie(String concentratie) {
        this.concentratie = concentratie;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "denumire='" + denumire + '\'' +
                ", concentratie=" + concentratie +
                ", observatii='" + observatii + '\'' +
                '}';
    }
}
