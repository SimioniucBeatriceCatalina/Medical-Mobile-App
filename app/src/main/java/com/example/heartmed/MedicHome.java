package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MedicHome extends AppCompatActivity {

    Button btc;
    Button buser;
    Button bfisaevaluare;
    Button bpacienti;
    ImageButton actualizare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medic_home);

        btc=findViewById(R.id.btnconsme);
        buser=findViewById(R.id.btnprofilmed);
         bfisaevaluare=findViewById(R.id.btnfisaev);
         bpacienti=findViewById(R.id.btncautap);
          actualizare=findViewById(R.id.btnadm);
        btc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(getApplicationContext(),IstoricConsultatiiMedic.class);
                startActivity(i);
            }
        });

        buser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(),MedicUser.class);
                startActivity(ii);
            }
        });
        bfisaevaluare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(),FisaMedicala.class);
                startActivity(ii);
            }
        });

        bpacienti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(),CautarePacinet.class);
                startActivity(ii);
            }
        });
        actualizare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(getApplicationContext(),AdministrareClinica.class);
                startActivity(ii);
            }
        });

    }
}
