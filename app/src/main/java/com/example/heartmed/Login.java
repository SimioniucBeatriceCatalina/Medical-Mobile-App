package com.example.heartmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.List;

import static com.example.heartmed.MainActivity.PFRE_TIPUSER;
import static com.example.heartmed.MainActivity.TIP;

public class Login extends AppCompatActivity {


    public static final String EMAIL = "pref";
    public static final String PFRE_EMAIL = "prefname";
    EditText etemail;
EditText etparola;
    TextView txtinreg;
    Button logare;
    Intent intent2;
    Intent intent3;
    FirebaseAuth fauth;
    FirebaseFirestore firestore;
 String tip;
Pacient p=new Pacient();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etemail = findViewById(R.id.etEmail);
        etparola = findViewById(R.id.etParola);
        txtinreg = findViewById(R.id.txtinreg);
        logare = findViewById(R.id.btnlogare);
        fauth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();

        txtinreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        logare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (validare()) {

                    String email = etemail.getText().toString();
                    String parola = etparola.getText().toString();


                    fauth.signInWithEmailAndPassword(email, parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
//                                Intent i1=new Intent( getApplicationContext(), HomeActivity.class);
//                                Intent i2=new Intent(getApplicationContext(), MedicHome.class);

                                CollectionReference docRef = firestore.collection("users");
                                docRef.whereEqualTo("email",etemail.getText().toString()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {

                                            Intent i1=new Intent( getApplicationContext(), HomeActivity.class);
                                            Intent i2=new Intent(getApplicationContext(), MedicHome.class);
                                            if(documentSnapshot.get("tip").equals("pacient")) {
//
                                                startActivity(i1);
                                            }
                                            else if(documentSnapshot.get("tip").equals("medic")) {
                                                startActivity(i2);

                                            }
                                        }
                                    }


                                });


                            }

                        }

                    });
                }


            }
        });
    }

                                      private boolean validare() {


                                          if (etemail.getText() == null || etemail.getText().toString().trim().isEmpty()) {
                                              etemail.requestFocus();
                                              etemail.setError("Introduceti emailul!!");
                                              return false;
                                          }
                                          if (etparola.getText() == null || etparola.getText().toString().trim().isEmpty()) {
                                              etparola.requestFocus();
                                              etparola.setError("Introduceti parola!!");
                                              return false;
                                          }

                                          return true;
                                      }
                                  }


