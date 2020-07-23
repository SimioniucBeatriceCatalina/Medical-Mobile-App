package com.example.heartmed;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter  extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
    public static final String PFRE_DOCTOR2 = "prefdoctor2";
    public  static final String IMAGINE = "imaginedoctor";
    public static final String PFRE_DOCTOR3 = "prefdoctor3";
    public  static final String GRAD = "medicgrad";


    public static final String  PFRE_DOCTOR = "prefdoctor";
    public  static final String NUME = "numedoctor";
    public static final String PFRE_DOCTOR1 ="prefdoctor1" ;
    public  static final String PRENUME = "prenumedoctor";
    private Button btn;
    private Context context;
    private List<Medic> medici=new ArrayList<>();
        int poz=0;
    FirebaseAuth fAuth;
    String userId;
    FirebaseFirestore firestore;
    String http;
        public CustomAdapter(Context context, ArrayList<Medic> items) {
            this.context = context;
            this.medici = items;
        }
        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.item_viewpager, parent, false));
        }
        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            poz=position;
            holder.nume.setText(medici.get(position).getNume());
            holder.prenume.setText(medici.get(position).getPrenume());
            holder.grad.setText(medici.get(position).getGrad());
            holder.pregatire.setText(medici.get(position).getPregatire());
            String http=medici.get(position).getImage();
            Glide.with(context).load(Uri.parse(http)).into(holder.imagine);

        }
        @Override
        public int getItemCount() {
            return medici.size();
        }
        public class CustomViewHolder extends RecyclerView.ViewHolder {

            private TextView nume;
            private TextView prenume;
            private TextView grad;
            private TextView pregatire;
            private ImageView imagine;
            public CustomViewHolder(View view) {
                super(view);
                btn=view.findViewById(R.id.button);

                nume = view.findViewById(R.id.tnume);
                prenume=view.findViewById(R.id.tprenume);
                grad = view.findViewById(R.id.tgrad);
                pregatire=view.findViewById(R.id.tpregatire);
                imagine=view.findViewById(R.id.imageprofilmedic);
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(context,AdaugaConsultatie.class);
                        context.startActivity(intent);
                    }
                });
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String mednume = nume.getText().toString().trim();
                        String medprenume = prenume.getText().toString().trim();

                        fAuth = FirebaseAuth.getInstance();
                        firestore = FirebaseFirestore.getInstance();
                        userId=fAuth.getCurrentUser().getUid();
                        CollectionReference docRef = firestore.collection("users");
                        docRef.whereEqualTo("nume",mednume).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    Medic m= documentSnapshot.toObject(Medic.class);
                                    http = m.getImage();
                                }
                            }
                        });
                        SharedPreferences preferences3 = context.getSharedPreferences(PFRE_DOCTOR3, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor3 = preferences3.edit();
                        editor3.putString(GRAD, grad.getText().toString());
                        editor3.apply();
                        SharedPreferences preferences = context.getSharedPreferences(PFRE_DOCTOR, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(NUME, mednume);
                        editor.apply();
                        SharedPreferences preferences1 = context.getSharedPreferences(PFRE_DOCTOR1, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor1 = preferences1.edit();
                        editor1.putString(PRENUME, medprenume);
                        editor1.apply();
                        SharedPreferences preferences2 = context.getSharedPreferences(PFRE_DOCTOR2, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor2 = preferences2.edit();
                        editor2.putString(IMAGINE, http);
                        editor2.apply();
                        Intent intent =new Intent(context,AdaugaConsultatie.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);

                    }
                });
            }
        }

    }
