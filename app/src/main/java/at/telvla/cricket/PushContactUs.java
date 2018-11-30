package at.telvla.cricket;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class PushContactUs {
    String name;
    String email;
    String massege;
    Retrofit CallServer;
    API api;

    public void SetDate (String name, String email, String massege){
        this.name = name;
        this.email = email;
        this.massege = massege;
    }

    public void Push (){
        CallServer = ApiClient.getClient();
        api = CallServer.create(API.class);

        Call<RegistrationResponse> call = api.PushContactUs(name, email, massege);
        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {
            }
        });
    }
}
