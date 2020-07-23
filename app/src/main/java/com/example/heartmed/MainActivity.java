package com.example.heartmed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {

    public static final String PFRE_TIPUSER = "TIPUSER";
    public static final String TIP ="TIP" ;
    Button start;
    private int RESULT_LOAD_IMAGE=32;
    private int notificationid=1;
    public static final String NOTIFICATION_CHANNEL_ID = "10001" ;
    private final static String default_notification_channel_id = "default" ;
    final Calendar myCalendar = Calendar. getInstance () ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.btnstart);

         createNotificationChannel();

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, 2020);
                calendar.set(Calendar.MONTH, 3);
                calendar.set(Calendar.DAY_OF_MONTH, 5);
                calendar.set(Calendar.HOUR_OF_DAY, 23);
                calendar.set(Calendar.MINUTE,28);
                calendar.set(Calendar.SECOND, 0);
                calendar.set(Calendar.MILLISECOND, 0);

                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);



            }
        });

    }

    private void createNotificationChannel() {

            if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.O){
                 CharSequence name="ReminderChannel";
                 String description="Channel for...";
                 int importance =NotificationManager.IMPORTANCE_DEFAULT;
                 NotificationChannel channel =new NotificationChannel("notify",name,importance);
                 channel.setDescription(description);

                 NotificationManager notifman=getSystemService(NotificationManager.class);
                 notifman.createNotificationChannel(channel);
        }

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

        }
    }


}
