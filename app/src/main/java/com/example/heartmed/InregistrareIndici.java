package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static com.example.heartmed.Login.EMAIL;
import static com.example.heartmed.Login.PFRE_EMAIL;

public class InregistrareIndici extends AppCompatActivity {


    Button btnsalveaza;
    Button btnanuleaza;
    EditText puls;
    ImageButton b1;
    ImageButton b2;
    ImageButton b3;
    EditText tensiune;
    EditText data;
    EditText ora;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    String userId;
    String mod = null;
    Indicatori_cardiaci ind1 = new Indicatori_cardiaci();
    String im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inregistrare_indici);

        btnsalveaza = findViewById(R.id.button4);
        btnanuleaza = findViewById(R.id.button3);
        puls = findViewById(R.id.editText3);
        tensiune = findViewById(R.id.editText);
        data = findViewById(R.id.editText2);
        ora = findViewById(R.id.editText4);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        b1 = findViewById(R.id.btnresting);
        b2 = findViewById(R.id.btnwalking);
        b3 = findViewById(R.id.btnrunning);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ind1.setTipactivitate("Repaus fizic");
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ind1.setTipactivitate("Activitate moderată");

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ind1.setTipactivitate("Activitate intensă");

            }
        });
        btnsalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                userId = fAuth.getCurrentUser().getUid();
if(validare()) {
    Indicatori_cardiaci ind = creareIndicator();


    CollectionReference docref = firestore.collection("indicatori");
    docref.add(ind);
    Toast.makeText(getApplicationContext(), "Adaugare reusita!!", Toast.LENGTH_LONG).show();
    Intent intent1 = new Intent(InregistrareIndici.this, GraficActivity.class);
    startActivity(intent1);
}
            }
        });

        btnanuleaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private Indicatori_cardiaci creareIndicator() {


        ind1.setEmail(userId);
        ind1.setPuls(Integer.valueOf(puls.getText().toString()));
        ind1.setTensiune(Integer.valueOf(tensiune.getText().toString()));
        ind1.setData(data.getText().toString());
        ind1.setOra(ora.getText().toString());
        return ind1;
    }

    private boolean validare() {
        if (tensiune.getText() == null || tensiune.getText().toString().trim().isEmpty()) {
            tensiune.requestFocus();
            tensiune.setError("Introduceti tensiune!!");
            return false;
        }
        if (puls.getText() == null || puls.getText().toString().trim().isEmpty()) {
            puls.requestFocus();
            puls.setError("Introduceti pulsul!!");
            return false;
        }
        if (data.getText() == null || data.getText().toString().trim().isEmpty()) {
            data.requestFocus();
            data.setError("Introduceti data!!");
            return false;
        }
        return true;
    }
}