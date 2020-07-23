package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.vivekkaushik.datepicker.DatePickerTimeline;
import com.vivekkaushik.datepicker.OnDateSelectedListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.heartmed.Login.EMAIL;
import static com.example.heartmed.Login.PFRE_EMAIL;

public class IstoricConsultatiiMedic extends AppCompatActivity {


    private ListView lv1;

    private List<Consultatie> lista5 = new ArrayList<>();
    DatePickerTimeline datepicker;
    public FirebaseAuth fAuth;
    String userId;
    Consultatie cons;
    FirebaseFirestore firestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_istoric_consultatii_medic);
        datepicker = findViewById(R.id.datePickerTimeline);
        lv1 = findViewById(R.id.listamedic);
        fAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        userId = fAuth.getCurrentUser().getUid();
        ConsultatiiMedicAdaptor adapter = new ConsultatiiMedicAdaptor(getApplicationContext(),
                R.layout.consultatiimedic_item, lista5, getLayoutInflater());
        lv1.setAdapter(adapter);


        int year1 = 2020;
        final int month1 = 6;
        int day1 = 13;

          datepicker.setInitialDate(year1, month1, day1);

            datepicker.setOnDateSelectedListener(new OnDateSelectedListener() {
                                                     @Override
                                                     public void onDateSelected(final int year,  int month , final int day, int dayOfWeek) {

                    SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
                    int month2=month+1;
                      final  String dt=" "+day+"/"+ month2 +"/"+year;
                      lista5.clear();
                                                         CollectionReference docRef = firestore.collection("consultatie");
                                                         docRef.whereEqualTo("medicemail", userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                             @Override
                                                             public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                 for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                                     SimpleDateFormat s=new SimpleDateFormat("dd/MM/yyyy");
                                                                     cons = documentSnapshot.toObject(Consultatie.class);
                                                                        String df=s.format(cons.getData());

                                                                     try {
                                                                         if(s.parse(dt).compareTo(s.parse(s.format(cons.getData())))==0){

                                                                             lista5.add(cons);

                                                                             ConsultatiiMedicAdaptor adapter = new ConsultatiiMedicAdaptor(getApplicationContext(),
                                                                                     R.layout.consultatiimedic_item, lista5, getLayoutInflater());
                                                                             lv1.setAdapter(adapter);
                                                                             adapter.notifyDataSetChanged();
                                                                         }
                                                                     } catch (ParseException e) {
                                                                         e.printStackTrace();
                                                                     }
                                                                 }
                                                             }
                                                         });




                                             }


                                                 }
            );

        }
    }
