package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class DosarMedicalActivity extends AppCompatActivity {



    ListView lv;
    List<FisaEvaluare> fise= new ArrayList<>();
    public FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dosar_medical);
        lv=findViewById(R.id.lvff);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();

        CollectionReference docRef = firestore.collection("fisa de evaluare");
        docRef.whereEqualTo("idPacient",userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    FisaEvaluare f = documentSnapshot.toObject(FisaEvaluare.class);
                     fise.add(f);
                    DosarAdapter adaptor = new DosarAdapter(getApplicationContext(),
                            R.layout.fisaevaluare_view, fise, getLayoutInflater());
                    lv.setAdapter(adaptor);


                }


            }
        });


    }
}
