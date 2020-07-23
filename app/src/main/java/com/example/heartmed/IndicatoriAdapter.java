package com.example.heartmed;

import android.content.Context;
import android.net.Uri;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class IndicatoriAdapter  extends ArrayAdapter<Indicatori_cardiaci> {



    private Context context;
    private int resource;
    private List<Indicatori_cardiaci> inregistrari;
    private LayoutInflater layout;
    String tip=null;
    DocumentReference docref;

    public FirebaseAuth fAuth;
    String userId;
    TextView tipact;
    FirebaseFirestore firestore;
    String indicatorrr;
    public IndicatoriAdapter(Context context, int resource, List<Indicatori_cardiaci> inregistrari, LayoutInflater layout) {
        super(context, resource, inregistrari);
        this.context=context;
        this.resource= resource;
        this.inregistrari = inregistrari;
        this.layout = layout;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = layout.inflate(resource, viewGroup, false);
        final Indicatori_cardiaci indicator=inregistrari.get(i);

        if(cview==null){
            cview=layout.inflate(R.layout.item_grafic,null,true);
        }
        TextView ora=cview.findViewById(R.id.ora);
        TextView data= cview.findViewById(R.id.data);
        TextView puls = cview.findViewById(R.id.tpuls);
        TextView tensiune= cview.findViewById(R.id.ttensiune);
        SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
        tipact=cview.findViewById(R.id.txttip);

        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();



        puls.setText(""+indicator.getPuls());
        tensiune.setText(""+indicator.getTensiune());
        data.setText(indicator.getData());
        ora.setText(""+indicator.getOra());
        tipact.setText(indicator.getTipactivitate());


        return cview;
    }
}
