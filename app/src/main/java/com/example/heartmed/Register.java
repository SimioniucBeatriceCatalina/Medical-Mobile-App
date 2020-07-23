package com.example.heartmed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.heartmed.MainActivity.PFRE_TIPUSER;
import static com.example.heartmed.MainActivity.TIP;

public class Register extends AppCompatActivity {
    private static final String TAG = "tag";
    private EditText Nume, Prenume, Email, Parola, Confirmaparola;
    private Button btninreg;
    String tipuser=null;
    private TextView conectare;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    String userId;
    private RadioGroup radioGroup;
String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btninreg=findViewById(R.id.btninregistrare);
        Nume=findViewById(R.id.eteNume);
        Prenume=findViewById(R.id.etrPrenume);
        Email=findViewById(R.id.etrEmail);
        Parola=findViewById(R.id.etrParola);
        Confirmaparola=findViewById(R.id.etrConfparola);
        fAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
        radioGroup =findViewById(R.id.myradioGroup);
        conectare=findViewById(R.id.txtConectare);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.radiomedic) {
                    tipuser = "medic";
                } else if (checkedId == R.id.radiopacient) {
                    tipuser = "pacient";

                }

            }

        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if(task.isSuccessful()){
                    token=task.getResult().getToken();
                }
            }
        });

        conectare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){


                Intent intent1 = new Intent(Register.this,Login.class);
                startActivity(intent1);


            }
            });
        btninreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validare()){


                    Pacient p=crearePacient();

                    final String nume = Nume.getText().toString();
                    final String prenume = Prenume.getText().toString();
                    final String email = Email.getText().toString();
                     final String parola = Parola.getText().toString();

                    fAuth.createUserWithEmailAndPassword(email,parola).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(getApplicationContext(),"Userul a fost inregistrat!!",Toast.LENGTH_LONG).show();
                                userId=fAuth.getCurrentUser().getUid();
                                DocumentReference docref= firestore.collection("users").document(userId);
                                Pacient p =new Pacient();
                                p.setNume(nume);
                                p.setPrenume(prenume);
                                p.setEmail(email);
                                p.setParola(parola);
                                p.setTip(tipuser);
                                p.setIdP(userId);
                                p.setAdresa(null);
                                p.setTelefon(null);
                                p.setImage("https://firebasestorage.googleapis.com/v0/b/heartmed-5f1e3.appspot.com/o/ck.png?alt=media&token=4707d044-76f1-4332-baf8-78027dc53ec2");
                                docref.set(p).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
//                                    Toast.makeText(getApplicationContext(),"Succes!!",Toast.LENGTH_LONG).show();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(getApplicationContext(),"Eroare!!",Toast.LENGTH_LONG).show();

                                    }
                                });
//                                startActivity(new Intent(getApplicationContext(),Login.class));
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Userul nu  a fost inregistrat!!"+task.getException().getMessage(),Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                    Nume.setText("");
                    Prenume.setText("");
                    Email.setText("");
                    Parola.setText("");
                    Confirmaparola.setText("");
                   radioGroup.clearCheck();

                }
            }
        });
    }

    private Pacient crearePacient() {

        final String nume = Nume.getText().toString();
        final String prenume = Prenume.getText().toString();
        final String email = Email.getText().toString();
        final String parola = Parola.getText().toString();
        Pacient p =new Pacient();
        p.setNume(nume);
        p.setPrenume(prenume);
        p.setEmail(email);
        p.setParola(parola);
        p.setTip(tipuser);
        p.setIdP(userId);
        return p;

    }


    private boolean validare(){
//
        if (Nume.getText() == null || Nume.getText().toString().trim().isEmpty()) {
             Nume.requestFocus();
            Nume.setError("Introduceti numele!!");
            return false;
        }
        if (Prenume.getText() == null || Prenume.getText().toString().trim().isEmpty()) {
            Prenume.requestFocus();
            Prenume.setError("Introduceti prenumele!!");
            return false;
        }
        if (Email.getText() == null || Email.getText().toString().trim().isEmpty()){
            Email.requestFocus();
            Email.setError("Introduceti emailul!!");
            return false;
        }
        if (Patterns.EMAIL_ADDRESS.matcher(Email.getText()).matches()!=true){
            Email.requestFocus();

            Email.setError("Parola invalida!!");
            return false;
        }
        if (Parola.getText().length() <6 ) {
            Parola.requestFocus();
            Parola.setError("Parola trebuie sa aiba minim 6 caractere!!");
            return false;
        }
        if (Parola.getText() == null || Parola.getText().toString().trim().isEmpty()) {
            Parola.requestFocus();
            Parola.setError("Introduceti parola!!");
            return false;
        }

        if ((Confirmaparola.getText() == null || Confirmaparola.getText().toString().trim().isEmpty())) {
            Confirmaparola.requestFocus();
            Confirmaparola.setError("Confirmati parola!!");
            return false;
        }
        if (((Parola.getText().toString().compareTo(Confirmaparola.getText().toString())) != 0)) {
            Parola.requestFocus();
            Parola.setError("Parolele nu se potrivesc!!");
            return false;
        }
        if (tipuser == null) {
            Toast.makeText(getApplicationContext(), "Alegeti tipul de utilizator !!!", Toast.LENGTH_LONG).show();
            return  false;
        }

        return true;
    }
}
