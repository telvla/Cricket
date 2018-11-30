package at.telvla.cricket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class PingService extends Service {

    public PingService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        /*NotificationSend check = new NotificationSend();
        check.CheckSend();*/

        CheckServerLatestNews ping_server = new CheckServerLatestNews();
        ping_server.StartPing();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
