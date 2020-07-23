package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.view.LineChartView;

import static com.example.heartmed.Login.EMAIL;
import static com.example.heartmed.Login.PFRE_EMAIL;

public class GraficActivity extends AppCompatActivity {


    ListView lv;
    List<Indicatori_cardiaci> lista= new ArrayList<>();;
     Button button;
    public FirebaseAuth fAuth;
    String userId;
    float medie;
   private TextView meniu;
    FirebaseFirestore firestore;
    List<Integer>ll=new ArrayList<>();
    LineChartView lineChartView;
    List<String>axisData= new ArrayList<>();
    List<Integer>yAxisData= new ArrayList<>();

    Indicatori_cardiaci in=new Indicatori_cardiaci();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grafic);

        lv = findViewById(R.id.lvgrafic);
        button = findViewById(R.id.btimageView5);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        meniu=findViewById(R.id.txtMeniu);
        initializare();

        IndicatoriAdapter adapter = new IndicatoriAdapter(getApplicationContext(),
                R.layout.item_grafic, lista, getLayoutInflater());
        lv.setAdapter(adapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), InregistrareIndici.class);
                startActivity(intent);
            }
        });

      meniu.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              Intent intent1 = new Intent(getApplicationContext(), HomeActivity.class);
              startActivity(intent1);
          }
      });
    }

    private void initializare() {



        userId=fAuth.getCurrentUser().getUid();
        CollectionReference docRef = firestore.collection("indicatori");
        docRef.whereEqualTo("email",userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                Indicatori_cardiaci i=documentSnapshot.toObject(Indicatori_cardiaci.class);
                    lista.add(i);
                   IndicatoriAdapter adapter = new IndicatoriAdapter(getApplicationContext(),
                            R.layout.item_grafic, lista, getLayoutInflater());
                    lv.setAdapter(adapter);

                }


            }
        });

        }


}

