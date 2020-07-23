package com.example.heartmed;

import android.content.Context;
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

import java.util.List;

public class DosarAdapter extends ArrayAdapter<FisaEvaluare> {



public  Medic mm;
    private Context context;
    private int resource;
    private List<FisaEvaluare> fise;
    private LayoutInflater layout;
String nnume="Prundea";
String nprenume="Adnana";
String grad="Profesor Universitar Dr.";
    TextView medic;
    FirebaseAuth fAuth;
    FisaEvaluare fisa=new FisaEvaluare();
    String userId;
    FirebaseFirestore firestore;



    public DosarAdapter(Context context, int resource, List<FisaEvaluare> fise, LayoutInflater layout) {
        super(context, resource, fise);
        this.context=context;
        this.resource= resource;
        this.fise=fise;
        this.layout = layout;
    }


    @Override
    public int  getCount(){
        return fise.size();
    }

    @Override
    public FisaEvaluare getItem(int position){
        return fise.get(position);
    }


    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = layout.inflate(resource, viewGroup, false);
        fisa=fise.get(i);

        if(cview==null){
            cview=layout.inflate(R.layout.fisaevaluare_view,null,true);
        }
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        TextView data = cview.findViewById(R.id.txtdata);
         medic = cview.findViewById(R.id.txtmedic);
        TextView  simptome = cview.findViewById(R.id.txtsimptome);
        TextView  diagnostic = cview.findViewById(R.id.txtdiagnostic);
        TextView  recomndari= cview.findViewById(R.id.txtrecomandari);
        fisa=fise.get(i);
        data.setText("Data:"+" "+fisa.getData());

        medic.setText(fisa.getNumeMedic());
        simptome.setText(fisa.getSimptome());
        diagnostic.setText(fisa.getDiagnostic());
        recomndari.setText(fisa.getRecomandari());
        return cview;
    }
}
