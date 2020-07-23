package com.example.heartmed;

import android.net.Uri;
import android.widget.ArrayAdapter;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "indicatori")
public class Indicatori_cardiaci  implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idConsultatie")
    private long idConsultatie;

    @ColumnInfo(name = "emailuser")
    @ForeignKey(entity=Pacient.class,parentColumns = "email",childColumns = "usermail",onDelete = CASCADE)
    private String email;

    @ColumnInfo(name = "puls")
    private int puls;

    @ColumnInfo(name = "tensiune")
    private int tensiune;

    @ColumnInfo(name = "data")
    private String data;


    @ColumnInfo(name = "ora")
    private String ora;


    private String tipactivitate;
@Ignore
    public Indicatori_cardiaci() {
    }

    public Indicatori_cardiaci(long idConsultatie, String email, int puls, int tensiune, String data, String ora, String tipactivitate) {
        this.idConsultatie = idConsultatie;
        this.email = email;
        this.puls = puls;
        this.tensiune = tensiune;
        this.data = data;
        this.ora = ora;
        this.tipactivitate = tipactivitate;
    }

    public long getIdConsultatie() {
        return idConsultatie;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public void setIdConsultatie(long idConsultatie) {
        this.idConsultatie = idConsultatie;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipactivitate() {
        return tipactivitate;
    }

    public void setTipactivitate(String tipactivitate) {
        this.tipactivitate = tipactivitate;
    }

    public int getPuls() {
        return puls;
    }

    public void setPuls(int puls) {
        this.puls = puls;
    }

    public int getTensiune() {
        return tensiune;
    }

    public void setTensiune(int tensiune) {
        this.tensiune = tensiune;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Indicatori_cardiaci{" +
                "email='" + email + '\'' +
                ", puls=" + puls +
                ", tensiune=" + tensiune +
                ", data='" + data + '\'' +
                ", ora='" + ora + '\'' +
                ", tipactivitate='" + tipactivitate + '\'' +
                '}';
    }
}
