package com.example.heartmed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.room.Room;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConsultatiiMedicAdaptor  extends ArrayAdapter {


    private Context context;
    private int resource;
    private List<Consultatie> consultatii;
    private LayoutInflater layout;

    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    Pacient mm=new Pacient();
    String nnume;
    String nprenume;
    String nn;
    String idcons;
    Consultatie consultatie=new Consultatie();
    DocumentReference docref1;
    public ConsultatiiMedicAdaptor (Context context, int resource, List<Consultatie> consultatii, LayoutInflater layout) {
        super(context, resource, consultatii);
        this.context=context;
        this.resource= resource;
        this.consultatii = consultatii;
        this.layout = layout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = layout.inflate(resource, viewGroup, false);
         consultatie = consultatii.get(i);
         idcons=consultatie.getIdConsultatie();
        nn= consultatie.getPacientemail();
        if (cview == null) {
            cview = layout.inflate(R.layout.consultatiimedic_item, null, true);
        }

        TextView ora=cview.findViewById(R.id.textoram);
        final  TextView pacprenume=cview.findViewById(R.id.textpacientmed);


        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();



        CollectionReference docRef = firestore.collection("users");
        docRef.whereEqualTo("idP",nn).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    mm=documentSnapshot.toObject(Pacient.class);


                        pacprenume.setText(mm.getNume()+" "+mm.getPrenume());


                }

            }
        });
        ora.setText(consultatie.getOra());

        return cview;
    }
}
