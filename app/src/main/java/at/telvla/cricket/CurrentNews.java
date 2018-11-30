package at.telvla.cricket;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CurrentNews extends AppCompatActivity{

    String NamePage, WebBody, Title, Description, Img, ImgBody, id_current, category, id_current_value;
    int int_id_current, count_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_news);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Retrofit CallServer = ApiClient.getClient();
        API api = CallServer.create(API.class);

        Intent intentId = getIntent();
        id_current = intentId.getStringExtra("id_current");
        int_id_current = Integer.parseInt(id_current);
        category = intentId.getStringExtra("category");
        id_current_value = intentId.getStringExtra("id_position");

        if(category == null) {
            category = "england";
        }

        if(id_current_value == null) {
            id_current_value = "0";
        }

        if(id_current == null){
            id_current = "1";
        }

        id_current = "" + int_id_current;

        Log.v("bags", "id_current - " + id_current);
        Log.v("bags", "category - " + category);

        Call<RegistrationResponse> call = api.GetCurrentNews(id_current, category);

        call.enqueue(new Callback<RegistrationResponse>() {
            @Override
            public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                RegistrationResponse token_body = response.body();

                Title = token_body.title;
                Description = token_body.description;
                Img = token_body.img;
                ImgBody = token_body.img_description;
                count_title = Title.length();

                if(count_title > 20){
                    setTitle(Title.substring(0, 20) + "...");
                }else{
                    setTitle(Title);
                }

                if(Img.trim().length() == 0){
                    Img = "http://bukmekerskie-kontory.bet/static/cricket/3.jpg";
                }

                if(ImgBody.trim().length() == 0){
                    ImgBody = "http://news.images.itv.com/image/file/375961/mobile_article_img.jpg";
                }

                WebView mWebView = (WebView) findViewById(R.id.webView);
                WebSettings settings = mWebView.getSettings();
                settings.setDefaultTextEncodingName("utf-8");
                mWebView.getSettings().setJavaScriptEnabled(true);
                mWebView.setPadding(0, 0, 0, 0);

                if (NamePage == null) {

                    WebBody = "<html><body style='padding: 0px;margin: 0px;'>" +
                            "<img src='" + Img + "' width='100%' style='padding: 0px;margin: 0px;'>" +
                            "<p style='font-size:16px;padding-left:10px;padding-right:10px;color:#205f01;'><b>" + Title + "</b></p>" +
                            "<div style='margin-left:10px;margin-right:10px;'><p style='text-align: justify;'>" + Description + "</p></div>" +
                            "<img src='" + ImgBody + "' width='100%' style='padding: 0px;margin: 0px;'>" +
                            "</body></html>";

                    mWebView.loadData(WebBody, "text/html", "UTF-8");
                }
            }

            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //  getMenuInflater().inflate(R.menu.menu_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(CurrentNews.this, AllNews.class);
        String id_current = "" + id_current_value;
        intent.putExtra("cursor_current", id_current);
        startActivity(intent);
        finish();

    }
}
