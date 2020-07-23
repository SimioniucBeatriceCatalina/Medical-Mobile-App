package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.content.Context;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MediciActivity extends AppCompatActivity {


    private RecyclerView rec;
    public FirebaseAuth fAuth;
    String userId;
    List<Medic>medici=new ArrayList<>();
    FirebaseFirestore firestore;
    Medic m=new Medic();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medici);

        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();

        final ArrayList<Medic> lista= new ArrayList<>();
        CustomAdapter adapter = new CustomAdapter(getApplicationContext(),lista);

        rec=findViewById(R.id.recyclerView);
        rec.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rec.setAdapter(adapter);

        userId=fAuth.getCurrentUser().getUid();
        CollectionReference docRef = firestore.collection("users");
        docRef.whereEqualTo("tip","medic").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {

            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                  Medic m1=documentSnapshot.toObject(Medic.class);
                      lista.add(m1);
                     CustomAdapter adapter = new CustomAdapter(getApplicationContext(),lista);
                    adapter.notifyDataSetChanged();rec=findViewById(R.id.recyclerView);
                    rec.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                            LinearLayoutManager.HORIZONTAL, false));
                    rec.setAdapter(adapter);

                }


            }
        });


        }
    }



