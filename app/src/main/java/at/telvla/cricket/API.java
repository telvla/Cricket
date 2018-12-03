package at.telvla.cricket;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 *  Get metods APT
 */
public interface API {
    @POST("/parser.html")
    Call<RegistrationResponse> GetAllNews (@Query("limit") String title, @Query("category") String abstract_txt);
    @POST("/parser.html")
    Call<RegistrationResponse> GetCurrentNews (@Query("id_current") String id_current, @Query("category") String categories);




    @POST("/parser.html")
    Call<List<NewsInfo>> GetAllNewsJson (@Query("all_news_json") Integer all_news_json);
    @POST("/parser.html")
    Call<RegistrationResponse> GetLastNews (@Query("push") String id_current);
    @POST("/parser.html")
    Call<RegistrationResponse> PushContactUs (@Query("name") String name, @Query("email") String email, @Query("message") String massege);
}
