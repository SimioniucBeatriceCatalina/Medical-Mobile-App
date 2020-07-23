package com.example.heartmed;

import java.io.Serializable;
import java.util.List;

public class FisaEvaluare  implements Serializable {



    private String id;
    private String idPacient;
    private String idMedic;
    private String data;
    private String diagnostic;
    private String simptome;
    private String recomandari;
private String numeMedic;

    public FisaEvaluare() {
    }

    public FisaEvaluare(String id, String idPacient, String idMedic, String data, String diagnostic, String simptome) {
        this.id = id;
        this.idPacient = idPacient;
        this.idMedic = idMedic;
        this.data = data;
        this.diagnostic = diagnostic;
        this.simptome = simptome;
    }

    public String getNumeMedic() {
        return numeMedic;
    }

    public void setNumeMedic(String numeMedic) {
        this.numeMedic = numeMedic;
    }

    public String getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getRecomandari() {
        return recomandari;
    }

    public void setRecomandari(String recomandari) {
        this.recomandari = recomandari;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdPacient() {
        return idPacient;
    }

    public void setIdPacient(String idPacient) {
        this.idPacient = idPacient;
    }

    public String getIdMedic() {
        return idMedic;
    }

    public void setIdMedic(String idMedic) {
        this.idMedic = idMedic;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getSimptome() {
        return simptome;
    }

    public void setSimptome(String simptome) {
        this.simptome = simptome;
    }

    @Override
    public String toString() {
        return "FisaEvaluare{" +
                "id='" + id + '\'' +
                ", idPacient='" + idPacient + '\'' +
                ", idMedic='" + idMedic + '\'' +
                ", data='" + data + '\'' +
                ", diagnostic='" + diagnostic + '\'' +
                ", simptome='" + simptome + '\'' +
                ", recomandari='" + recomandari + '\'' +
                ", numeMedic='" + numeMedic + '\'' +
                '}';
    }
}
