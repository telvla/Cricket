package at.telvla.cricket;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Check lost news, shower push
 */
public class CheckNotification {
    Integer NOTIFY_ID = 1001;
    Context context;
    String Ticker = "News Cricket!";
    String Title;
    String Content;
    String Abst_content;

    public void SetString (String Ticker, String Title, String Abst_content, String Content){
        this.Ticker = Ticker;
        this.Title = Title;
        this.Abst_content = Abst_content;
        this.Content = Content;
    }

    void  ShowerNotification() {
        if (Title != null) {
            CurrentNotification notificationcurrent = new CurrentNotification();
            notificationcurrent.getServer();

            context = MyApplication.getContext();
            NotificationManager notifManager;

            final int NOTIFY_ID = 0; // ID of notification
            String id = "default_channel_id";
            String title = "Default Channel";
            Intent intent;
            PendingIntent pendingIntent;
            NotificationCompat.Builder builder;
            notifManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = notifManager.getNotificationChannel(id);
                if (mChannel == null) {
                    mChannel = new NotificationChannel(id, title, importance);
                    mChannel.enableVibration(true);
                    mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                    notifManager.createNotificationChannel(mChannel);
                }
                builder = new NotificationCompat.Builder(context, id);
                intent = new Intent(context, CurrentArticle.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentTitle(Title)                            // required
                        .setSmallIcon(R.drawable.heart)   // required
                        .setContentText(Abst_content)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            } else {
                builder = new NotificationCompat.Builder(context, id);
                intent = new Intent(context, CurrentArticle.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
                builder.setContentTitle(Title)                            // required
                        .setSmallIcon(R.drawable.heart)   // required
                        .setContentText(Abst_content)
                        .setDefaults(Notification.DEFAULT_ALL)
                        .setAutoCancel(true)
                        .setContentIntent(pendingIntent)
                        .setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400})
                        .setPriority(Notification.PRIORITY_HIGH);
            }
            Notification notification = builder.build();
            notifManager.notify(NOTIFY_ID, notification);
        }
    }
}
