package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FisaMedicala extends AppCompatActivity {


    private EditText ednumeP;
    private EditText edSimptome;
    private EditText edDiagnostic;
    private Button btsalveaza;
    FirebaseAuth fAuth;
    String userId;
    String idd;
    private EditText den;
    private EditText con;
    private EditText obs;
    private TextView meddm;
    private TextView adaugaMed;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private CheckBox cb6;
    private CheckBox cb7;
    private CheckBox cb8;
    private CheckBox cb9;
    String nn;
    String numebaza;
    ListView lv;
    ArrayAdapter<Medicament> adaptor;
    FisaEvaluare fisa = new FisaEvaluare();
    List<String> recomandari = new ArrayList<>();
    String alimentatie;
    String alimentatie1;
    String alimentatie2;
    String activitate =" " ;
    String medicamente;
    Medicament m = new Medicament();
    Pacient pp = new Pacient();
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fisa_medicala);
        ednumeP = findViewById(R.id.fisaNumep);
        edSimptome = findViewById(R.id.fisaSimptp);
        edDiagnostic = findViewById(R.id.fisaDiagP);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        btsalveaza = findViewById(R.id.salveazaFisa);
        cb1 = findViewById(R.id.alim);
        cb2 = findViewById(R.id.alim1);
        cb3 = findViewById(R.id.alim4);
        cb4 = findViewById(R.id.alim5);
        cb5 = findViewById(R.id.alim3);
        cb6 = findViewById(R.id.activitatea1);
        cb7 = findViewById(R.id.activitatea3);
        cb8 = findViewById(R.id.activitate4);
        cb9 = findViewById(R.id.checkBox4);
        meddm = findViewById(R.id.fisamed);
        btsalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Check(view);


                String medicamente = meddm.getText().toString();
//                recomandari.add(medicamente);
                Date d = new Date();
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                fisa.setData(sdf.format(d));
                fisa.setDiagnostic(edDiagnostic.getText().toString());
                fisa.setSimptome(edSimptome.getText().toString());
                fisa.setIdMedic(fAuth.getCurrentUser().getUid());
                if (cb4.isChecked())
                    alimentatie2= " Cresterea consumului de apa(min.2L/zi)."+"\n";
                alimentatie1=" Reducere consumului de:"+alimentatie+".\n";
                fisa.setRecomandari("-alimentatie:\n"+alimentatie1 +alimentatie2+"\n"+"-activitate:\n"+activitate +"\n"+"-medicamente:\n"+medicamente);


                CollectionReference docRef = firestore.collection("users");
                docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            pp = documentSnapshot.toObject(Pacient.class);
                          nn = ednumeP.getText().toString();
                          numebaza = pp.getNume()+" "+pp.getPrenume();

                            if (numebaza.equals(nn)) {
                                idd = pp.getIdP();

                                fisa.setIdPacient(idd);

                            }


                        }

                        CollectionReference docref = firestore.collection("fisa de evaluare");

                        docref.add(fisa);
//                        Toast.makeText(getApplicationContext(), "Fisa de evaluare inregistrata cu succes!", Toast.LENGTH_LONG).show();
                            ednumeP.setText(" ");
                             edSimptome.setText(" ");
                        edDiagnostic.setText(" ");
                        cb1.setChecked(false);
                        cb2.setChecked(false);
                        cb3.setChecked(false);
                        cb4.setChecked(false);
                        cb5.setChecked(false);
                        cb6.setChecked(false);
                        cb7.setChecked(false);
                        cb8.setChecked(false);
                        cb9.setChecked(false);
                        meddm.setText(" ");


                        Toast.makeText(getApplicationContext(), " Fișă înregistrată cu succes!", Toast.LENGTH_LONG).show();

                    }
                });


            }
        });
    }

    public void Check(View v) {
        if (cb1.isChecked())
            alimentatie =" alcool";
        if (cb2.isChecked() == true)
            alimentatie = alimentatie +","+ " grasimi animale";
        if (cb3.isChecked() == true)
            alimentatie = alimentatie +","+ " apa(1L/zi)";
        if (cb5.isChecked() == true)
            alimentatie = alimentatie +","+ " sare";
        if (cb6.isChecked())
            activitate = " Cresterea activitatii fizice"+"\n";;
        if (cb7.isChecked() == true)
            activitate = activitate + "Activitate fizica moderata"+"\n";
        if (cb8.isChecked() == true)
            activitate = activitate + " Reducerea greutatii corporale"+"\n";
        if (cb9.isChecked() == true)
            activitate = activitate + " Evitarea efortului fizic"+"\n";

    }
}
