package com.example.heartmed;

import android.content.Context;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Date;
import java.util.List;

public class ConsultatieAdaptor1  extends ArrayAdapter<Consultatie> {



    private Context context;
    private int resource;
    private List<Consultatie> consultatii;
    private LayoutInflater layout;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    Medic mm=new Medic();
    String nnume;
    String nprenume;
    TextView medic;
    public ConsultatieAdaptor1(Context context, int resource, List<Consultatie> consultatii, LayoutInflater layout) {
        super(context, resource, consultatii);
        this.context=context;
        this.resource= resource;
        this.consultatii = consultatii;
        this.layout = layout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = layout.inflate(resource, viewGroup, false);
        final Consultatie consultatie=consultatii.get(i);

        if(cview==null){
            cview=layout.inflate(R.layout.item_consultatie1,null,true);
        }
        Date d=consultatie.getData();
       medic = cview.findViewById(R.id.consult_medic);
        TextView tzi = cview.findViewById(R.id.pacient);
        TextView tluna = cview.findViewById(R.id.consult_ora);
        TextView ora= cview.findViewById(R.id.consult_data);
        String day = (String) DateFormat.format("dd",   d);
        String month = (String) DateFormat.format("MMM",  d);
        fAuth = FirebaseAuth.getInstance();

        firestore = FirebaseFirestore.getInstance();

        CollectionReference docRef = firestore.collection("users");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    mm=documentSnapshot.toObject(Medic.class);
                    String nn= consultatie.getMedicemail();
                    if(mm.getIdP().equals(nn)){
                        nnume=mm.getNume();
                        nprenume=mm.getPrenume();

                    }
                }


            }
        });
        tzi.setText(day);
        tluna.setText(month);
       medic.setText(consultatie.getNumeMedic());
        ora.setText(consultatie.getOra());


        return cview;
    }
}
