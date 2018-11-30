package at.telvla.cricket;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CurrentNotification {
    ListView listView;
    String id = "1";
    ArrayList<Map<String, Object>> data;
    Retrofit CallServer;
    API api;
    static String Id;
    static String Title;
    static String Ticker;
    static String Abstract;
    static String content;
    static String img;
    Context context;

    public static String getId() {
        return Id;
    }
    public static String getTitle() { return Title; }
    public static String getTicker() {
        return Ticker;
    }
    public static String getAbstract() {
        return Abstract;
    }
    public static String getContent() { return content; }
    public static String getImg() { return img; }

    public void getServer() {

        context = MyApplication.getContext();
        File file = new File();
        id = file.File_Read(context, "lost_id_news_file");

        CallServer = ApiClient.getClient();
        api = CallServer.create(API.class);
        Call<RegistrationResponse> call = api.GetLastNews(id);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                RegistrationResponse token_body = response.body();

                Id = token_body.id;
                Title = token_body.title;
                Ticker = "Cricket.";
                Abstract = token_body.abstr;
                content = token_body.content;
                img = token_body.img;

                Log.v("check_sert", "server quest - " + token_body.id + "--" + token_body.title + "----" + Ticker + "-----" +  token_body.content);

            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            }
        });
    }
}
