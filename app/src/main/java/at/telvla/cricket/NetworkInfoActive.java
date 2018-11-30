package at.telvla.cricket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkInfoActive {
    static  Integer flagactiveNetwork;
    public static Integer CheckNetwork(){
        Context context = MyApplication.getContext();
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();

        if (activeNetwork != null && activeNetwork.isConnected()) {
            flagactiveNetwork = 1;
        }else{
            flagactiveNetwork = 0;
        }
        return  flagactiveNetwork;
    }
}
