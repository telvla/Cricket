package at.telvla.cricket;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.util.Log;

public class NotificationSend {

        void CheckSend(){
            Log.v("service", "check");
        }



        /*String str = String.valueOf(code_status);

        Num = new File_RQ().File_Read(context, mFileName);

        Intent notificationIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                0, notificationIntent,
                PendingIntent.FLAG_CANCEL_CURRENT);

        Resources res = context.getResources();
        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.heart)
                // большая картинка
                .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.iconvk))
                //.setTicker(res.getString(R.string.warning)) // текст в строке состояния
                .setTicker("Статус изменен!")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                //.setContentTitle(res.getString(R.string.notifytitle)) // Заголовок уведомления
                .setContentTitle("Виджет СТАТУС СВОБОДЕН")
                //.setContentText(res.getString(R.string.notifytext))
                .setContentText("Статус: " + name_status); // Текст уведомления
        // Notification notification = builder.getNotification(); // до API 16
        Notification notification = builder.build();

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFY_ID, notification);
    */


}
