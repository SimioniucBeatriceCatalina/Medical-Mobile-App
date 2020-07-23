package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AdministrareClinica extends AppCompatActivity {


    public FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    EditText edii;
    EditText em;
    EditText tt;
    EditText addr;
    ImageButton editdate;
    ListView lvserv;
    ImageButton addserv;
    EditText denumirea;
    EditText   preta;
    List<Serviciu> listaservicii=new ArrayList<>();
    ServiciiAdapter adaptor;
    Serviciu s=new Serviciu();
    int position;
    Serviciu ss=new Serviciu();
    String den;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrare_clinica);


        edii=findViewById(R.id.edddescriere);
        em=findViewById(R.id.eddemail);
        tt=findViewById(R.id.edtelefon);
        addr=findViewById(R.id.edadresa);
        editdate=findViewById(R.id.btnedtdate);
        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        lvserv=findViewById(R.id.lvservedit);
        addserv=findViewById(R.id.btnadds);
//        ServiciiAdapter adapter = new ServiciiAdapter(getApplicationContext(),
//                R.layout.item_servicii, listaservicii, getLayoutInflater());
//        lvserv.setAdapter(adapter);

        final CollectionReference docRef = firestore.collection("clinica medicala");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Clinica c=documentSnapshot.toObject(Clinica.class);
                     edii.setText(c.getDescriere());
                     em.setText(c.getEmail());
                     tt.setText(c.getNrTelefon());
                     addr.setText(c.getAdresa());
                }


            }
        });

        CollectionReference docRef1 = firestore.collection("servicii medicale");
        docRef1.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    Serviciu s=documentSnapshot.toObject(Serviciu.class);
                    listaservicii.add(s);
                    ServiciiAdapter adapter = new ServiciiAdapter(getApplicationContext(),
                            R.layout.item_servicii, listaservicii, getLayoutInflater());
                    lvserv.setAdapter(adapter);
                }


            }
        });

        lvserv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                position = i;
                AlertDialog.Builder build = new AlertDialog.Builder(AdministrareClinica.this);
                build.setMessage("Vreti sa stergeti serviciul medical?");

                build.setPositiveButton("DA",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {

                                s = listaservicii.get(position);

                                DocumentReference docref = firestore.collection("servicii medicale").document(s.getId());
                                docref.delete();
                                listaservicii.remove(s);
                                ServiciiAdapter adapter = new ServiciiAdapter(getApplicationContext(),
                                        R.layout.item_servicii, listaservicii, getLayoutInflater());
                                lvserv.setAdapter(adapter);
                                Toast.makeText(getApplicationContext(), "Serviciu medical sters !!", Toast.LENGTH_SHORT).show();


                            }

                        });


                build.setNegativeButton("NU",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                build.show();
                  return false;
            }

        });
        addserv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AdministrareClinica.this);


                alertDialog.setMessage("Vreti sa adaugati un serviciu medical?");
                LayoutInflater inflater=getLayoutInflater();
                View view1=inflater.inflate(R.layout.customview_alertdialog,null);
                alertDialog.setView(view1);


                denumirea=view1.findViewById(R.id.adden);
                preta=view1.findViewById(R.id.adpret);
                alertDialog.setPositiveButton("Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                if (denumirea.getText() == null || denumirea.getText().toString().trim().isEmpty()) {
                                    denumirea.setError("Completeaza campurile!!");
                                } else if(preta.getText() == null || preta.getText().toString().trim().isEmpty()) {
                                    denumirea.setError("Completeaza campurile!!");

                                }
                                    else{
                                        String idd= UUID.randomUUID().toString();
                                        s.setDenumire(denumirea.getText().toString());
                                    s.setPret(Integer.parseInt(preta.getText().toString()));
                                    s.setId(idd);

                                        DocumentReference docref = firestore.collection("servicii medicale").document(idd);

                                        docref.set(s);
                                        listaservicii.add(s);
                                        ServiciiAdapter adapter = new ServiciiAdapter(getApplicationContext(),
                                                R.layout.item_servicii, listaservicii, getLayoutInflater());
                                        lvserv.setAdapter(adapter);
                                        Toast.makeText(getApplicationContext(), "Serviciu medical adaugat!!", Toast.LENGTH_SHORT).show();


                                    }
                                }
                            }
                        );
                alertDialog.setNegativeButton("NU",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                alertDialog.show();
            }
        });
        editdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                DocumentReference docref= firestore.collection("clinica medicala").document("NtLXcKKkJh2R82qLLV9x");
                final Map<String,Object> clinica= new HashMap<>();
                clinica.put("descriere",edii.getText().toString());
                clinica.put("email",em.getText().toString());
                clinica.put("nrTelefon",tt.getText().toString());
                clinica.put("adresa",addr.getText().toString());

                docref.set(clinica);
                Toast.makeText(getApplicationContext(),
                        "Date actualizate cu succes!!", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
