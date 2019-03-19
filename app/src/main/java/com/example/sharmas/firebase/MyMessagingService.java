package com.example.sharmas.firebase;

import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import static android.content.ContentValues.TAG;

public class MyMessagingService extends FirebaseMessagingService {
    DatabaseHelper databaseHelper=new DatabaseHelper(MyMessagingService.this);

    private static final String TAG = "mayank: ";
    String user_id = "0";
    String date = "0";
    String hal_id = "0";
    String M_view = "0";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());
            user_id = remoteMessage.getData().get("user_id");
            date = remoteMessage.getData().get("date");
            hal_id = remoteMessage.getData().get("hal_id");
            M_view = remoteMessage.getData().get("M_view");

            //putting it in local database
            try {
                databaseHelper.saveDetails(user_id, date, hal_id, M_view);
            } catch (Exception e) {
                Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
}
