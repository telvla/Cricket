package at.telvla.cricket;

import android.content.Context;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CheckServerLatestNews {
    Retrofit CallServer;
    API api;
    Integer lost_id_news_server;
    Integer lost_id_news_file;
    String Test = "435";
    String get_file_content;
    Context context;
    CheckNotification shower = new CheckNotification();
    String id;

    /**
     *  Ping server, check last news
     */
    void StartPing(){

        context = MyApplication.getContext();
        File file = new File();
        id = file.File_Read(context, "lost_id_news_file");
        CallServer = ApiClient.getClient();
        api = CallServer.create(API.class);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask()

        {
            @Override
            public void run()
            {

                Call<RegistrationResponse> call = api.GetLastNews(id);
                call.enqueue(new Callback<RegistrationResponse>() {
                    @Override
                    public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {

                        RegistrationResponse token_body = response.body();
                        shower.SetString("", token_body.title, token_body.abstr, token_body.content);

                    }
                    @Override
                    public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                    }
                });
                lost_id_news_server = Integer.parseInt(Test);
                if (lost_id_news_server == null) {
                    lost_id_news_server = 0;
                }
                File file = new File();
                get_file_content = file.File_Read(context, "lost_id_news_file");
                if (get_file_content == null) {
                    lost_id_news_file = 1;
                } else {
                    lost_id_news_file = Integer.parseInt(get_file_content);
                }

               //if (1 == 1) { //////////  lost_id_news_server > lost_id_news_file
                    shower.ShowerNotification();
                    file.File_Entry(context, "lost_id_news_file", "" + lost_id_news_server);
                //}

            }
        };
        timer.schedule(timerTask, 0, 10000000);
    }
}
