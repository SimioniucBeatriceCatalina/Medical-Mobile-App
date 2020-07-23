package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

public class InfoActivity extends AppCompatActivity {


    private TextView descriere;
    private  TextView edemail;
    private  TextView edtelefon;
    private  TextView edadresa;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    Clinica c=new Clinica();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        descriere=findViewById(R.id.txtinfor);
        edemail=findViewById(R.id.txtemail);
        edtelefon=findViewById(R.id.txttelefon);
        edadresa=findViewById(R.id.txtadresa);
        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();

        CollectionReference docRef = firestore.collection("clinica medicala");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    c=documentSnapshot.toObject(Clinica.class);
                    descriere.setText(c.getDescriere());
                    edemail.setText(c.getEmail());
                    edtelefon.setText(c.getNrTelefon());
                    edadresa.setText(c.getAdresa());

                    }


            }
        });



    }
}
