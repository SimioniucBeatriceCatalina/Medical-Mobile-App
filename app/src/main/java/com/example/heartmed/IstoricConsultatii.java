package com.example.heartmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.example.heartmed.ConsultatieAdaptor.ITEM;
import static com.example.heartmed.ConsultatieAdaptor.ITEM1;
import static com.example.heartmed.ConsultatieAdaptor.PREF_DELETE;
import static com.example.heartmed.Login.EMAIL;
import static com.example.heartmed.Login.PFRE_EMAIL;


public class IstoricConsultatii extends AppCompatActivity {

    private static final int ADD =43 ;
    private ListView lv1;
    private ListView lv2;
    private List<Consultatie> lista1all = new ArrayList<>();
    private List<Consultatie> lista1 = new ArrayList<>();
    private List<Consultatie> lista2 = new ArrayList<>();
    private TextView returnm;
Button btad;
    DocumentReference docref;

    public FirebaseAuth fAuth;
    String userId;

    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_consultatii);

        btad=findViewById(R.id.buttonAdauga);
        lv1=findViewById(R.id.lvprezent);
        lv2=findViewById(R.id.lvtrecut);
        returnm=findViewById(R.id.returnmenu);
        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();




        initializareListe();
        returnm.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(IstoricConsultatii.this,HomeActivity.class);
        startActivity(intent1);
    }
});
btad.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent1 = new Intent(IstoricConsultatii.this,MediciActivity.class);
        startActivityForResult(intent1,ADD);

    }
});
        ConsultatieAdaptor adapter = new ConsultatieAdaptor(getApplicationContext(),
                R.layout.item_consultatie, lista1, getLayoutInflater());
        lv1.setAdapter(adapter);
        ConsultatieAdaptor adapter1 = new ConsultatieAdaptor(getApplicationContext(),
                R.layout.item_consultatie1, lista2, getLayoutInflater());
        lv2.setAdapter(adapter1);
    }

    private void initializareListe() {

        userId=fAuth.getCurrentUser().getUid();
        CollectionReference docRef = firestore.collection("consultatie");
        docRef.whereEqualTo("pacientemail",userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot  documentSnapshot:queryDocumentSnapshots){
                    Consultatie cons=documentSnapshot.toObject(Consultatie.class);

                    Date d=new Date();

                    if(cons.getData().after(d)){
                        lista1all.add(cons);

                   }
                    else {
                        lista2.add(cons);

                    }
                    ConsultatieAdaptor adapter = new ConsultatieAdaptor(getApplicationContext(),
                            R.layout.item_consultatie, lista1all, getLayoutInflater());
                    lv1.setAdapter(adapter);
                    ConsultatieAdaptor1 adapter1 = new ConsultatieAdaptor1(getApplicationContext(),
                            R.layout.item_consultatie1, lista2, getLayoutInflater());
                    lv2.setAdapter(adapter1);

                }


            }
        });

    }



}