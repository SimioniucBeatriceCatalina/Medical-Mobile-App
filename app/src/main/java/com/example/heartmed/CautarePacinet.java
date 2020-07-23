package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CautarePacinet extends AppCompatActivity {



    private SearchView cautare;
    private ListView lv;
    private List<FisaEvaluare> lista=new ArrayList<>();
    private List<FisaEvaluare> listafiltrata=new ArrayList<>();
    private DosarAdapter adapter;
    public FirebaseAuth fAuth;
    String userId;
    String nume;
    String idc;
    Pacient p=new Pacient();
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cautare_pacinet);

        cautare=findViewById(R.id.searchView);
        lv=findViewById(R.id.lvcautare);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();


        CollectionReference docRef = firestore.collection("fisa de evaluare");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    FisaEvaluare f = documentSnapshot.toObject(FisaEvaluare.class);
                    lista.add(f);


                }


            }
        });


        cautare.setOnQueryTextListener(new SearchView.OnQueryTextListener() {



            @Override
            public boolean onQueryTextSubmit(final String query) {




                return false;
            }

            @Override
            public boolean onQueryTextChange(final String s) {
                listafiltrata.clear();

                CollectionReference docRef = firestore.collection("users");
                docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            p = documentSnapshot.toObject(Pacient.class);
                            if((p.getNume()+" "+p.getPrenume()).equals(s)) {
                                for (FisaEvaluare fisaev : lista) {

                                    if (p.getIdP().equals(fisaev.getIdPacient())) {

                                        listafiltrata.add(fisaev);
                                        DosarAdapter adaptor = new DosarAdapter(getApplicationContext(),
                                                R.layout.fisaevaluare_view, listafiltrata, getLayoutInflater());
                                        lv.setAdapter(adaptor);



                                    }
                                }
                            }
                        }
                    }
                });

                return false;
            }




        });





    }


}
