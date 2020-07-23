package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;
//import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import static com.example.heartmed.CustomAdapter.GRAD;
import static com.example.heartmed.CustomAdapter.IMAGINE;
import static com.example.heartmed.CustomAdapter.NUME;
import static com.example.heartmed.CustomAdapter.PFRE_DOCTOR;
import static com.example.heartmed.CustomAdapter.PFRE_DOCTOR1;
import static com.example.heartmed.CustomAdapter.PFRE_DOCTOR3;
import static com.example.heartmed.CustomAdapter.PRENUME;

public class AdaugaConsultatie extends AppCompatActivity {

    public static final String ADAUGA_CONS = "add";
    public static final String PREF_NOTIFICARE ="creareNotificare" ;
    public static final String MEDIC ="notifmedic" ;
    public static final String PREF_NOTIFICARE1 ="notificareora" ;
    public static final String ORA ="creareora" ;
    TextView numemedic;
    TextView prenumemedic;
    TextView tdata;
    TextView tora;
    TextView teroare;
    ImageView imgd;
    Button adauga;
    Button salveaza;
    Button o1;
    Button o2;
    Button o3;
    Button o4;
    Button o5;
    Button o6;
    Date date;
    String http;
    private int notificationid=1;
    String oora;
    private List<Consultatie>lli=new ArrayList<>();
    private List<Consultatie>lli1=new ArrayList<>();
    int buttonState1 = 1;
    int buttonState2 = 1;
    int buttonState3 = 1;
    int buttonState4 = 1;
    int buttonState5 = 1;
    int buttonState6 = 1;
    FirebaseAuth fAuth;
    FirebaseFirestore firestore;
    Intent i = new Intent();
    String ora = null;
    String userId;
    int yearn;
    int monthn;
    int dayn;
  String idd=null;
    Medic mm=new Medic();
    String numecompletMedic;
int yearc;
int monthc;
int dayc;
    Consultatie consult =new Consultatie();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_consultatie);


        if (getIntent().hasExtra("myimage")) {
            ImageView iv = findViewById(R.id.imageView5);
            Bitmap bmp = BitmapFactory.decodeByteArray(getIntent().getByteArrayExtra("myimage"), 0, getIntent().getByteArrayExtra("myimage").length);
            iv.setImageBitmap(bmp);
        }

        prenumemedic = findViewById(R.id.tvpremeddd);
        teroare = findViewById(R.id.tvalarma);
        tdata = findViewById(R.id.tvdata);
        adauga = findViewById(R.id.btndate);
        salveaza = findViewById(R.id.btnadd);
        tora = findViewById(R.id.tvora);
        o1 = findViewById(R.id.btno1);
        o2 = findViewById(R.id.btno4);
        o3 = findViewById(R.id.btno3);
        o4 = findViewById(R.id.btno2);
        o5 = findViewById(R.id.btno5);
        o6 = findViewById(R.id.btno6);
        imgd=findViewById(R.id.imagedoct);
        i = getIntent();


        fAuth= FirebaseAuth.getInstance();
        firestore= FirebaseFirestore.getInstance();
        userId=fAuth.getCurrentUser().getUid();
        SharedPreferences preferences = getApplicationContext().getSharedPreferences(PFRE_DOCTOR, Context.MODE_PRIVATE);
        String value1 = preferences.getString(NUME, "e");
        SharedPreferences preference1 = getApplicationContext().getSharedPreferences(PFRE_DOCTOR1, Context.MODE_PRIVATE);
        String value2 = preference1.getString(PRENUME, "e");
        SharedPreferences preferences3 = getApplicationContext().getSharedPreferences(PFRE_DOCTOR3, Context.MODE_PRIVATE);
        String value4 = preferences3.getString(GRAD, "e");
        prenumemedic.setText(value4+ " "+value1+" "+value2);
        numecompletMedic=value4+"         "+value1+" "+value2;




        SharedPreferences preference2 = getApplicationContext().getSharedPreferences(PFRE_DOCTOR1, Context.MODE_PRIVATE);
        String value3 = preference2.getString(IMAGINE, "e");
        Glide.with(getApplicationContext()).load(Uri.parse(value3)).into(imgd);


        CollectionReference docRef = firestore.collection("users");
        docRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                for(QueryDocumentSnapshot documentSnapshot:queryDocumentSnapshots){
                    mm=documentSnapshot.toObject(Medic.class);
                    String nn= prenumemedic.getText().toString().trim();
                    String numecautat=mm.getGrad()+" "+mm.getNume().trim()+" "+mm.getPrenume().trim();
                    if(numecautat.equals(nn)){
                        http = mm.getImage();
                        Glide.with(getApplicationContext()).load(Uri.parse(http)).into(imgd);
                        idd=mm.getIdP();

                        consult.setMedicemail(idd);

                    }


                }


            }
        });



        o1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teroare.setError(null);
                teroare.setText(null);
                if (buttonState1 % 2 == 1) {
                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape4));

                } else {
                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape7));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    ora = "9:30";


                }
                buttonState1++;

            }
        });


        o2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonState2 % 2 == 1) {
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));

                } else {
                    teroare.setError(null);
                    teroare.setText(null);

                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape7));
                    o3.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    ora = "14:30";
                }
                buttonState2++;

            }
        });




        o3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonState3 % 2 == 1) {
                    o3.setBackground(getResources().getDrawable(R.drawable.button_shape4));

                } else {
                    teroare.setError(null);
                    teroare.setText(null);
                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o3.setBackground(getResources().getDrawable(R.drawable.button_shape7));
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    ora = "10:30";
                }
                buttonState3++;

            }
        });

        o4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonState4 % 2 == 1) {
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape4));

                } else {
                    teroare.setError(null);
                    teroare.setText(null);
                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o3.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape7));
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    ora = "11:30";
                }
                buttonState4++;

            }
        });

        o5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonState5 % 2 == 1) {
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape4));

                } else {
                    teroare.setError(null);
                    teroare.setText(null);
                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o3.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape7));
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    ora = "12:30";
                }
                buttonState5++;

            }
        });
        o6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (buttonState6 % 2 == 1) {
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape4));

                } else {
                    teroare.setError(null);
                    teroare.setText(null);
                    o1.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o2.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o3.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o4.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o5.setBackground(getResources().getDrawable(R.drawable.button_shape4));
                    o6.setBackground(getResources().getDrawable(R.drawable.button_shape7));
                    ora = "13:30";
                }
                buttonState6++;

            }
        });
        Calendar newCalendar = Calendar.getInstance();


        final DatePickerDialog datepicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                tdata = findViewById(R.id.tvdata);
                date = new Date(year - 1900, monthOfYear, dayOfMonth);
                monthn=monthOfYear;
                yearn=year;
                dayn=dayOfMonth;
                final SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");
                Date today = new Date();
               final String data=dateformat.format(date);

                o1.setEnabled(true);
                o2.setEnabled(true);
                o3.setEnabled(true);
                o4.setEnabled(true);
                o5.setEnabled(true);
                o6.setEnabled(true);

                yearc=year;
                monthc=monthOfYear;
                dayc=dayOfMonth;
                tdata.setText(data);

                CollectionReference docRef = firestore.collection("consultatie");
                docRef.whereEqualTo("medicemail",idd).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot  documentSnapshot:queryDocumentSnapshots){
                            Consultatie cons=documentSnapshot.toObject(Consultatie.class);
                            if(dateformat.format(cons.getData()).equals(data)) {
                                oora=cons.getOra();
                                lli.add(cons);
                                String oo="9:30";
                                String oo1="10:30";
                                String oo2="11:30";
                                String oo3="12:30";
                                String oo4="13:30";
                                String oo5="14:30";

                                if(oora.equals(oo)){
//                                    Toast.makeText(getApplicationContext(),oora,Toast.LENGTH_LONG).show();

                                    o1.setEnabled(false);
                                }
                                if(oora.equals(oo5)){
//                                    Toast.makeText(getApplicationContext(),oora,Toast.LENGTH_LONG).show();

                                    o2.setEnabled(false);
                                }
                                if(oora.equals(oo1)){
//                                    Toast.makeText(getApplicationContext(),oora,Toast.LENGTH_LONG).show();

                                    o3.setEnabled(false);
                                }
                                if(oora.equals(oo2)){
//                                    Toast.makeText(getApplicationContext(),oora,Toast.LENGTH_LONG).show();

                                    o4.setEnabled(false);
                                }
                                if(oora.equals(oo3)){
//                                    Toast.makeText(getApplicationContext(),oora,Toast.LENGTH_LONG).show();

                                    o5.setEnabled(false);
                                }
                                if(oora.equals(oo4)){
//                                    Toast.makeText(getApplicationContext(),oora,Toast.LENGTH_LONG).show();

                                    o6.setEnabled(false);
                                }
                            }
                            if(lli.size()!=0){


                            }



                        }


                    }
                });






            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));



        Date today = new Date();
        newCalendar.setTime(today);
        newCalendar.add(Calendar.DAY_OF_MONTH, 1);
        long minDate = newCalendar.getTime().getTime();
        datepicker.getDatePicker().setMinDate(minDate);

        newCalendar.setTime(today);
        newCalendar.add(Calendar.YEAR, 1);
        long maxDate = newCalendar.getTime().getTime();
        datepicker.getDatePicker().setMaxDate(maxDate);
        if (newCalendar.get(Calendar.DAY_OF_MONTH) == Calendar.SUNDAY) {


        }
        adauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datepicker.show();
            }
        });

        salveaza.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            userId = fAuth.getCurrentUser().getUid();
if(validare()){
                                            consult.setPacientemail(userId);
                                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                                            consult.setData(date);
                                            consult.setOra(ora);
                                          String idgenerat=UUID.randomUUID().toString();
                                            consult.setMedicemail(idd);
                                            consult.setIdConsultatie(idgenerat);
                                            consult.setEfectuata(false);
                                            consult.setNumeMedic(prenumemedic.getText().toString());
//                                            Toast.makeText(AdaugaConsultatie.this, idd, Toast.LENGTH_LONG).show();
                                            consult.setMedicemail(idd);
                                            DocumentReference docref = firestore.collection("consultatie").document(idgenerat);

                                            docref.set(consult);

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.YEAR, yearn);
    calendar.set(Calendar.MONTH, monthn);
    calendar.set(Calendar.DAY_OF_MONTH, dayn-1);
    calendar.set(Calendar.HOUR_OF_DAY,7 );
    calendar.set(Calendar.MINUTE,39);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);

    Intent intent4=new Intent(AdaugaConsultatie.this,ReminderBroadcast.class);
    PendingIntent pending=PendingIntent.getBroadcast(AdaugaConsultatie.this,0,intent4,0);

    AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

    alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pending);




    Intent intent1 = new Intent(AdaugaConsultatie.this, IstoricConsultatii.class);
                                            startActivity(intent1);
                                        }


            }

        });


    }


    private boolean validare() {

        if (date == null) {
            tdata.requestFocus();
            tdata.setError("Alegeti data!!");
//            tdata.setTextColor(R.color.colorAccent);
            tdata.setText("Alegeti data!!");
            return false;
        }
        tdata.setError(null);
        if (buttonState6 % 2 == 1 && buttonState6 == 1 && buttonState5 == 1 && buttonState4 == 1 && buttonState3 == 1 && buttonState2 == 1 && buttonState1 == 1 && buttonState4 % 2 == 1 && buttonState4 % 2 == 1 && buttonState3 % 2 == 1 && buttonState2 % 2 == 1 && buttonState1 % 2 == 1) {
            teroare.requestFocus();
            teroare.setError("Alegeti ora!!");
            teroare.setText("Alegeti ora!!");
            return false;
        }
        teroare.setError(null);
        teroare.setText(null);


        return true;


    }



}








