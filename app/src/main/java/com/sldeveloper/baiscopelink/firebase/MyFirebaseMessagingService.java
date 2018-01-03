package com.sldeveloper.baiscopelink.firebase;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.Toast;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sldeveloper.baiscopelink.MainActivity;
import com.sldeveloper.baiscopelink.R;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
//Displaying data in log
//It is optional
//It is optional
//It is optional

        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " +
                remoteMessage.getNotification().getBody());
//Calling method to generate notification
        sendNotification(remoteMessage.getNotification().getBody());


    }

    //This method to generate push notification
    private void sendNotification(String messageBody) {

//MainActivity Intent Registration
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);
//Take Notification Sound
        Uri defaultSoundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//Generate the Notification
        NotificationCompat.Builder notificationBuilder = (NotificationCompat.Builder) new
                NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.baiscope_app_ico)
                .setContentTitle("BaiscopeLink Notification")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager =
                (NotificationManager)
                        getSystemService(Context.NOTIFICATION_SERVICE);
//Create Push Notification
        notificationManager.notify(0, notificationBuilder.build());
    }

}
