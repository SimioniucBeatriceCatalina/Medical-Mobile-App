package com.example.heartmed;

        import android.os.Parcel;
        import android.os.Parcelable;

        import androidx.annotation.NonNull;
        import androidx.room.ColumnInfo;
        import androidx.room.Entity;
        import androidx.room.ForeignKey;
        import androidx.room.Ignore;
        import androidx.room.PrimaryKey;

        import java.io.Serializable;
        import java.util.Date;


        import static androidx.room.ForeignKey.CASCADE;




public class Consultatie implements Serializable,Comparable{

    private String idConsultatie;

    private boolean efectuata;
    @ColumnInfo(name = "pacientemail")
    @ForeignKey(entity=Pacient.class,parentColumns = "email",childColumns = "pamail",onDelete = CASCADE)
    private String  pacientemail;


    @ColumnInfo(name = "medicemail")
    @ForeignKey(entity=Pacient.class,parentColumns = "email",childColumns = "mail",onDelete = CASCADE)

    private String medicemail;

    @ColumnInfo(name = "data")
    private Date data;

    @ColumnInfo(name = "ora")
    private String ora;
private String numeMedic;
    private String confirmare;
    @Ignore
    public Consultatie() {
    }

    public Consultatie(boolean efectuata, String pacientemail, String medicemail, Date data, String ora, String confirmare) {
        this.efectuata = efectuata;
        this.pacientemail = pacientemail;
        this.medicemail = medicemail;
        this.data = data;
        this.ora = ora;
        this.confirmare = confirmare;
    }


    public Consultatie(String idConsultatie, boolean efectuata, String pacientemail, String medicemail, Date data, String ora, String confirmare) {
        this.idConsultatie = idConsultatie;
        this.efectuata = efectuata;
        this.pacientemail = pacientemail;
        this.medicemail = medicemail;
        this.data = data;
        this.ora = ora;
        this.confirmare = confirmare;
    }

    public String getIdConsultatie() {
        return idConsultatie;
    }

    public void setIdConsultatie(String  idConsultatie) {
        this.idConsultatie = idConsultatie;
    }

    public String getNumeMedic() {
        return numeMedic;
    }

    public void setNumeMedic(String numeMedic) {
        this.numeMedic = numeMedic;
    }

    public String getPacientemail() {
        return pacientemail;
    }

    public void setPacientemail(String pacientemail) {
        this.pacientemail = pacientemail;
    }

    public String getMedicemail() {
        return medicemail;
    }

    public void setMedicemail(String medicemail) {
        this.medicemail = medicemail;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public boolean isEfectuata() {
        return efectuata;
    }

    public void setEfectuata(boolean efectuata) {
        this.efectuata = efectuata;
    }

    @Override
    public String toString() {
        return "Consultatie{" +
                "idConsultatie='" + idConsultatie + '\'' +
                ", efectuata=" + efectuata +
                ", pacientemail='" + pacientemail + '\'' +
                ", medicemail='" + medicemail + '\'' +
                ", data=" + data +
                ", ora='" + ora + '\'' +
                ", numeMedic='" + numeMedic + '\'' +
                ", confirmare='" + confirmare + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Date dd=((Consultatie)o).getData();
        return dd.compareTo(this.data);
    }
}
