package admin.ats.com.focaloidproject.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import admin.ats.com.focaloidproject.Services.Notification_service;

/**
 * Created by Anju on 26-04-2017.
 */

public class AlarmBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String n=intent.getStringExtra("Title");

        Toast.makeText(context,"From BRD"+ intent.getStringExtra("Title"), Toast.LENGTH_LONG).show();
        //  String notftn_title=intent.getStringExtra("Title");

        Intent i_service = new Intent(context, Notification_service.class);
        // i_service.putExtra("Title",notftn_title);
        context.startService(i_service);
    }
}
