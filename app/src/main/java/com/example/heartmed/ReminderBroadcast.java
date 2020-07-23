package com.example.heartmed;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;


public class ReminderBroadcast  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationCompat.Builder builder=new NotificationCompat.Builder(context,"notify")
                .setSmallIcon(R.drawable.loggo)
                .setContentTitle("Alerta programare ")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Va reamintim ca maine aveti programare la clinica HeartMed!!"))
                .setContentText("Va reamintim ca maine aveti programare la clinica HeartMed!!")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat noMan= NotificationManagerCompat.from(context);

        noMan.notify(200,builder.build());
    }
}
