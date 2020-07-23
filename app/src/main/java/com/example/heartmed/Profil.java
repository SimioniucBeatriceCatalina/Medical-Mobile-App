package com.example.heartmed;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.google.android.gms.common.internal.safeparcel.SafeParcelable.NULL;


public class Profil extends AppCompatActivity {
    private static final int RESULT_LOAD_IMAGE =43 ;
    private static Bitmap Image = null;
    private static Bitmap rotateImage = null;
    private ImageView imageView;
    private ImageButton edit;
    private ImageButton editnume;
    private ImageButton editparola;
    private ImageButton edittelefon;
    private ImageButton editadresa;
    private TextView etnume;
    private TextView etmail;
    private TextView etparola;
    private TextView tnume;
    private TextView tprenume;
    private TextView tparola;
    private TextView temail;
    private TextView ttelefon;
    private TextView tadresa;
    private final int PICK_IMAGE_REQUEST = 22;
String ad;
    public FirebaseAuth fAuth;
    FirebaseStorage storage;
    StorageReference storageReference;
    String userId;
    Uri filePath;
Uri imageurl;
    FirebaseFirestore firestore;
    public  static final int PERMISSION_REQUEST = 0;
    List<Pacient>pacienti=new ArrayList<>();
    private static final int GALLERY = 1;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
//       etnume=findViewById(R.id.tvnumeprofil);
//        etmail=findViewById(R.id.tvemailprofil);
//        etparola=findViewById(R.id.tvparolaprofil);
        editnume=findViewById(R.id.imageeditnume);
        editparola=findViewById(R.id.imageeditparola);
        editadresa=findViewById(R.id.imageeditadresa);
        edittelefon=findViewById(R.id.imageedittelefon);
        tnume=findViewById(R.id.tvnumeprofil1);
        tprenume=findViewById(R.id.tvprenumeprofil1);
        tparola=findViewById(R.id.tvparolaprofil1);
        temail=findViewById(R.id.tvemailprofil1);
        ttelefon=findViewById(R.id.tvtelefonprofil1);
        tadresa=findViewById(R.id.tvadresaprofil1);
        fAuth=FirebaseAuth.getInstance();
        firestore=FirebaseFirestore.getInstance();
         userId=fAuth.getCurrentUser().getUid();
         imageView=findViewById(R.id.imageprofil);
        edit=findViewById(R.id.imageedit);
        imageView=findViewById(R.id.imageprofil);
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();


        userId=fAuth.getCurrentUser().getUid();
        CollectionReference docRef = firestore.collection("users");
        docRef.whereEqualTo("idP",userId).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                                         @Override
                                                                         public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                                             for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                                                                 Pacient p = documentSnapshot.toObject(Pacient.class);

                                                                                 String http = p.getImage();
                                                                                     Glide.with(getApplicationContext()).load(Uri.parse(http)).into(imageView);
                                                                                 tparola.setText(p.getParola());
                                                                                 tnume.setText(p.getNume());
                                                                                 tprenume.setText(p.getPrenume());
                                                                                 temail.setText(p.getEmail());
                                                                                 tadresa.setText(p.getAdresa());
                                                                                 ttelefon.setText(p.getTelefon());

                                                                             }
                                                                         }
                                                                     });
//

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED  || checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE )
                != PackageManager.PERMISSION_GRANTED ){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST);
            requestPermissions(new String[]{Manifest.permission.CAMERA},PERMISSION_REQUEST);
        }
//        initComponents();
        editnume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Profil.this);


                alertDialog.setMessage("Vreti sa va schimbati numele?");
                final EditText input = new EditText(Profil.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                if ( input.getText() == null || input.getText().toString().trim().isEmpty() ) {
                                  input.setError("Completeaza campul!!");
                                }
                                else {

                                    DocumentReference docref= firestore.collection("users").document(userId);
                                    final Map<String,Object> user= new HashMap<>();
                                    user.put("Nume",input.getText().toString());
                                    docref.update(user);
                                    Toast.makeText(getApplicationContext(),
                                            "Nume modificat!!", Toast.LENGTH_SHORT).show();

                                       tnume.setText(input.getText());

                                }
                            }
                        });
                alertDialog.setNegativeButton("NU",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                alertDialog.show();
            }

        });


        editparola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Profil.this);


                alertDialog.setMessage("Vreti sa va schimbati parola?");
                final EditText input = new EditText(Profil.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);

                alertDialog.setPositiveButton("Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                if ( input.getText() == null || input.getText().toString().trim().isEmpty() ) {
                                    input.setError("Completeaza campul!!");
                                }
                                else {
                                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                                    if(user==null)
                                        return ;
                                    user.updatePassword(input.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()) {


                                                Toast.makeText(getApplicationContext(),
                                                        "Parola modificata!!", Toast.LENGTH_SHORT).show();
                                                tparola.setText(input.getText());
                                                DocumentReference docref= firestore.collection("users").document(userId);
                                                final Map<String,Object> user= new HashMap<>();
                                                user.put("Parola",input.getText().toString());
                                                docref.update(user);
                                            }

                                            }
                                    });



                                }
                            }
                        });
                alertDialog.setNegativeButton("NU",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                alertDialog.show();
            }

        });



        editadresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Profil.this);


                alertDialog.setMessage("Vreti sa va schimbati adresa?");
                final EditText input = new EditText(Profil.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                if ( input.getText() == null || input.getText().toString().trim().isEmpty() ) {
                                    input.setError("Completeaza campul!!");
                                }
                                else {

                                    DocumentReference docref= firestore.collection("users").document(userId);
                                    final Map<String,Object> user= new HashMap<>();
                                    user.put("adresa",input.getText().toString());
                                    docref.update(user);
                                    Toast.makeText(getApplicationContext(),
                                            "Adresa modificata!!", Toast.LENGTH_SHORT).show();

                                    tadresa.setText(input.getText());

                                }
                            }
                        });
                alertDialog.setNegativeButton("NU",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                alertDialog.show();
            }

        });

        edittelefon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Profil.this);


                alertDialog.setMessage("Vreti sa va numarul de telefon numele?");
                final EditText input = new EditText(Profil.this);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT);
                input.setLayoutParams(lp);
                alertDialog.setView(input);
                alertDialog.setPositiveButton("Da",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int which) {
                                if ( input.getText() == null || input.getText().toString().trim().isEmpty() ) {
                                    input.setError("Completeaza campul!!");
                                }
                                else {

                                    DocumentReference docref= firestore.collection("users").document(userId);
                                    final Map<String,Object> user= new HashMap<>();
                                    user.put("telefon",input.getText().toString());
                                    docref.update(user);
                                    Toast.makeText(getApplicationContext(),
                                            "Numar de telefon modificat!!", Toast.LENGTH_SHORT).show();

                                    ttelefon.setText(input.getText());

                                }
                            }
                        });
                alertDialog.setNegativeButton("NU",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });


                alertDialog.show();
            }

        });
edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                SelectImage();

                            }

});



                            }




    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }


    public void onActivityResult(int requestCode,
                                    int resultCode,
                                    Intent data) {


        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView.setImageBitmap(bitmap);
                uploadImage();
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }

    }


    private void uploadImage() {

        if (filePath != null) {


            // Defining the child of storageReference
            final StorageReference ref
                    = storageReference
                    .child(
                            "images"
                                    + UUID.randomUUID().toString());

            // adding listeners on upload
            // or failure of image
            ref.putFile(filePath)
                    .addOnSuccessListener(
                            new OnSuccessListener<UploadTask.TaskSnapshot>() {

                                @Override
                                public void onSuccess(
                                        UploadTask.TaskSnapshot taskSnapshot) {

                                    // Image uploaded successfully
                                    // Dismiss dialog
//                                    progressDialog.dismiss();

//                      Task<Uri>urlTask=taskSnapshot.getStorage().getDownloadUrl();
//
//
//                          Uri downloadUri=urlTask.getResult();
//                          DocumentReference docref= firestore.collection("users").document(userId);
//                          final Map<String,Object> user= new HashMap<>();
//                          user.put("imagine",downloadUri.toString());
//                          docref.update(user);

//                                    Toast.makeText(Profil.this,downloadUri.toString()
//                                            ,
//                                                    Toast.LENGTH_SHORT)
//                                            .show();

                                }
                            })
                    .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                        @Override
                        public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {

                            if (!task.isSuccessful()) {
                                throw task.getException();
                            }
                            return ref.getDownloadUrl();
                        }
                    }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()) {
                        Uri downUri = task.getResult();
                        Log.d("Final URL", "onComplete: Url: " + downUri.toString());

                        DocumentReference docref= firestore.collection("users").document(userId);
                          final Map<String,Object> user= new HashMap<>();
                          user.put("image",downUri.toString());
                          docref.update(user);

                    }

                }

            })




                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {


                            Toast
                                    .makeText(Profil.this,
                                            "Failed " + e.getMessage(),
                                            Toast.LENGTH_SHORT)
                                    .show();
                        }
                    });
        }
    }

}