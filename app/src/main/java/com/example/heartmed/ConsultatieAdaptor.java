package com.example.heartmed;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Room;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.NotNull;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.heartmed.Login.EMAIL;
import static com.example.heartmed.Login.PFRE_EMAIL;

public class ConsultatieAdaptor  extends ArrayAdapter<Consultatie> {


    public static final String PREF_DELETE = "delete";
    public static final String ITEM ="item" ;
    public static final String ITEM1 ="item1" ;
    private Context context;
    private int resource;
    private List<Consultatie> consultatii;
    private LayoutInflater layout;
    private Button btn;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    Medic mm=new Medic();
    Medic m1=new Medic();
    String nnume;
    String nprenume;
    String numeMedic;
    TextView medic;
    final int position=0;
     String iddd;
     Consultatie ob=new Consultatie();
    String tt;
    String ff;
   int position1;
    String idcautat;
Consultatie consultatie =new Consultatie();
    DocumentReference docref;
    private String PFRE_DELETE;

    public ConsultatieAdaptor(Context context, int resource, List<Consultatie> consultatii, LayoutInflater layout) {
        super(context, resource, consultatii);
        this.context=context;
        this.resource= resource;
        this.consultatii = consultatii;
        this.layout = layout;
    }

    @NotNull
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cview = layout.inflate(resource, viewGroup, false);
        position1=i;
      consultatie = consultatii.get(i);
         iddd=consultatie.getIdConsultatie();
        final int position = i;
        if (cview == null) {
            cview = layout.inflate(R.layout.item_consultatie, null, true);
        }
        Date d = consultatie.getData();
      medic = cview.findViewById(R.id.consult_medic);
        TextView tzi = cview.findViewById(R.id.pacient);
        TextView tluna = cview.findViewById(R.id.consult_ora);
        TextView ora = cview.findViewById(R.id.consult_data);
        String day = (String) DateFormat.format("dd", d);
        String month = (String) DateFormat.format("MMM", d);
        btn = cview.findViewById(R.id.btnanulare);
        fAuth = FirebaseAuth.getInstance();

        firestore = FirebaseFirestore.getInstance();
 btn.setOnClickListener(new View.OnClickListener() {
     @Override
     public void onClick(View view) {


         docref= firestore.collection("consultatie").document(consultatie.getIdConsultatie());
         docref.delete();
         consultatii.remove( consultatie);
        notifyDataSetChanged();
     }

 });




        medic.setText(consultatie.getNumeMedic());
        tzi.setText(day);
        tluna.setText(month);
        ora.setText(consultatie.getOra());

        return cview;
    }
    public void numeMedic(){

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
       medic.setText(nnume+ " " +nprenume);


                    }
                }


            }
        });
    }

}
