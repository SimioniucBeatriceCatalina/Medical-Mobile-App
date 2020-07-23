package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Servicii extends AppCompatActivity {

    public FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    List<Serviciu> lista=new ArrayList<>();
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicii);


        listView=findViewById(R.id.lvservicii);
        ServiciiAdapter adapter = new ServiciiAdapter(getApplicationContext(),
                R.layout.item_servicii, lista, getLayoutInflater());
        listView.setAdapter(adapter);
        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();


//        Serviciu s1=new Serviciu("EKG",250);
//        Serviciu s2=new Serviciu("Consultatie",100);
//        Serviciu s3=new Serviciu("Consultatie la domiciliu",400);
//        Serviciu s4=new Serviciu("Corectar malformatii congenitale",200);
//        Serviciu s5=new Serviciu("Ecografie Doppler",200);
//        Serviciu s6=new Serviciu("Coronarografie",250);
//        Serviciu s7=new Serviciu("Embolizare anevrism cerebral",48700);
//        Serviciu s8=new Serviciu("Embolizare hemangiom",2630);
//        Serviciu s9=new Serviciu("Embolizare varicocel",4530);
//        Serviciu s10=new Serviciu("Implant defibrilator cardiac",3510);
//        Serviciu s11=new Serviciu("Interpretare analize",130);
//        Serviciu s12=new Serviciu("Interpretare investigatii cardiologice",130);
//        Serviciu s13=new Serviciu("Oscilometrie",30);
//        Serviciu s14=new Serviciu("Pachet investigatii cardiologie",700);
//        Serviciu s15=new Serviciu("Pulsoximetrie",35);
//        Serviciu s16=new Serviciu("Test de efort",320);
//        lista.add(s1);
//        lista.add(s2);
//        lista.add(s3);
//        lista.add(s4);
//        lista.add(s5);
//        lista.add(s6);
//        lista.add(s7);
//        lista.add(s8);
//        lista.add(s9);
//        lista.add(s10);
//        lista.add(s11);
//        lista.add(s12);
//        lista.add(s13);
//        lista.add(s14);
//        lista.add(s15);
//        lista.add(s16);




        CollectionReference docRef1 = firestore.collection("servicii medicale");
        docRef1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Serviciu s=documentSnapshot.toObject(Serviciu.class);
                    lista.add(s);
                    ServiciiAdapter adapter = new ServiciiAdapter(getApplicationContext(),
                            R.layout.item_servicii, lista, getLayoutInflater());
                    listView.setAdapter(adapter);
                }


            }
        });
    }





}
