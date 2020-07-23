package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
Button btmedic;
Button btconsultatii;
Button btservicii;
Button btprofil;
Button btgrafic;
Button info;
Button dosar;
TextView deconectare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btmedic = findViewById(R.id.btnmedici);
        btservicii = findViewById(R.id.btnservicii);
        btprofil=findViewById(R.id.btnprofil);
        btgrafic=findViewById(R.id.btngrafic);
        info=findViewById(R.id.btnmedicament);
        dosar=findViewById(R.id.btndosar);
        deconectare=findViewById(R.id.txtdeconectare);
        deconectare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,Login.class);
                startActivity(intent1);
            }
        });
        btconsultatii = findViewById(R.id.btncons);
                btconsultatii.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent1 = new Intent(HomeActivity.this,IstoricConsultatii.class);
                        startActivity(intent1);
                    }
                });
        btmedic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,MediciActivity.class);
                startActivity(intent1);
            }
        });

        btservicii.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,Servicii.class);
                startActivity(intent1);
            }
        });

        btprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,Profil.class);
                startActivity(intent1);


            }
        });

      dosar.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent1 = new Intent(HomeActivity.this,DosarMedicalActivity.class);
              startActivity(intent1);
          }
      });
        btgrafic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(HomeActivity.this,GraficActivity.class);
                startActivity(intent1);
            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent5 = new Intent(HomeActivity.this,InfoActivity.class);
                startActivity(intent5);
            }
        });
            }
}



