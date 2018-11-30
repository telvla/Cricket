package at.telvla.cricket;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    private CharSequence mTitle;
    Integer NOTIFY_ID = 1111;
    String mFileName = "file_id";
    Integer id_int_file, id_server = 1;
    String NamePage, Title, Abstr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Cricket");
        //toolbar.setSubtitle("Sub title");
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        WebView mWebView = (WebView) findViewById(R.id.webView);
        WebSettings settings = mWebView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setPadding(0, 0, 0, 0);

        Intent intentId = getIntent();
        NamePage = intentId.getStringExtra("NamePage");

        if (NamePage == null) {
            mWebView.loadUrl("file:////android_asset/Cricket.html");
        }else{
            mWebView.loadUrl("file:////android_asset/" + NamePage + ".html");
        }





        String id_file = new File().File_Read(getApplicationContext(), mFileName);

        if(id_file  == null){
            id_int_file = 0;
        }else{
            id_int_file = Integer.parseInt(id_file);
        }


       /* Log.v("kkk", id_server + " ------ " + id_int_file);

        if(id_server < id_int_file) {

            Retrofit CallServer = ApiClient.getClient();
            API api = CallServer.create(API.class);

            Call<RegistrationResponse> call = api.GetCurrentNews( "", "");

            call.enqueue(new Callback<RegistrationResponse>() {
                @Override
                public void onResponse(Call<RegistrationResponse> call, Response<RegistrationResponse> response) {
                    RegistrationResponse token_body = response.body();

                    Title = token_body.title;
                    Abstr = token_body.abstr;

                    Intent notificationIntent = new Intent(getApplicationContext(), MainActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(),
                            0, notificationIntent,
                            PendingIntent.FLAG_CANCEL_CURRENT);
                    Resources res = getApplicationContext().getResources();
                    Notification.Builder builder = new Notification.Builder(getApplicationContext());
                    builder.setContentIntent(contentIntent)
                            .setSmallIcon(R.drawable.heart)
                            .setLargeIcon(BitmapFactory.decodeResource(res, R.drawable.iconvk))
                            .setTicker("News Cricket!")
                            .setWhen(System.currentTimeMillis())
                            .setAutoCancel(true)
                            .setContentTitle(Title)
                            .setContentText(Abstr);
                    Notification notification = builder.build();
                    NotificationManager notificationManager = (NotificationManager)
                            getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
                    notificationManager.notify(NOTIFY_ID, notification);

                }

                @Override
                public void onFailure(Call<RegistrationResponse> call, Throwable t) {

                }
            });
        }*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
          return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

         if (id == R.id.england) {
            Intent intent = new Intent (MainActivity.this, AllNews.class);
            String NamePage = "england";
            intent.putExtra("category", NamePage);
            startActivity(intent);
            finish();
        /*}else if(id == R.id.australia){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "australia";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.bangladesh){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "bangladesh";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.india){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "india";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.newzealand){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "newzealand";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.pakistan){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "pakistan";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.southafrica){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "southafrica";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.srilanka){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "srilanka";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();
         }else if(id == R.id.westindies){
             Intent intent = new Intent (MainActivity.this, AllNews.class);
             String NamePage = "westindies";
             intent.putExtra("category", NamePage);
             startActivity(intent);
             finish();

*/

         }else if(id == R.id.settings){
             Intent intent = new Intent (MainActivity.this, Setting.class);
             startActivity(intent);
             finish();
         }else if(id == R.id.contact_us){
             Intent intent = new Intent (MainActivity.this, ContactUs.class);
             startActivity(intent);
             finish();
         }







        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
