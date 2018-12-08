package at.telvla.cricket;


import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllNews extends AppCompatActivity {

    ListView listView;
    String cursor_current, category;
    Integer int_cursor_current;
    ArrayList<Map<String, Object>> data;
    Retrofit CallServer;
    API api;
    File file;
    String file_dark_theme;
    String file_name = "file_dark_theme";
    List<NewsInfo> list;
    NewsAdapter adapters;
    private BroadcastReceiver mNetworkReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        file = new File();
        file_dark_theme = file.File_Read(getApplication().getApplicationContext(), file_name);

        if (file_dark_theme != null) {
            if (file_dark_theme.equals("dark theme")) {
                setTheme(R.style.NewsAppThemeDark);
            } else {
                setTheme(R.style.NewsAppThemeLight);
            }
        }

        startService(new Intent(this, PingService.class));
        setContentView(R.layout.content_all_news);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        listView = (ListView)findViewById(R.id.listView);
        Intent intentId = getIntent();
        category = intentId.getStringExtra("category");
        cursor_current = intentId.getStringExtra("cursor_current");

        mNetworkReceiver = new NetworkChangeReceiver();
        registerNetworkBroadcastForNougat();

        if(category == null) {
            category = "england";
        }

        if(cursor_current == null) {
            int_cursor_current = 0;
        }else{
            int_cursor_current = Integer.parseInt(cursor_current);
        }

        CallServer = ApiClient.getClient();
        api = CallServer.create(API.class);

        Call<List<NewsInfo>> call = api.GetAllNewsJson(1);
        call.enqueue(new Callback<List<NewsInfo>>() {
            @Override
            public void onResponse(Call<List<NewsInfo>> call, Response<List<NewsInfo>> response) {

                list = response.body();
                adapters = new NewsAdapter(getApplicationContext(), list);
                listView.setAdapter(adapters);

            }

            @Override
            public void onFailure(Call<List<NewsInfo>> call, Throwable t) {
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                //Log.v("addlist", "первый --" + firstVisibleItem);Log.v("addlist", "текущий ----" + visibleItemCount);Log.v("addlist", "последний -------" + totalItemCount);Log.v("addlist", "-------------------------------");

                int position = firstVisibleItem+visibleItemCount;
                int lim = totalItemCount;

                if (position >= lim && totalItemCount > 0) {

                    Call<List<NewsInfo>> call = api.GetAllNewsJson(1);
                    call.enqueue(new Callback<List<NewsInfo>>() {
                        @Override
                        public void onResponse(Call<List<NewsInfo>> call, Response<List<NewsInfo>> response) {

                            List<NewsInfo> list_add = response.body();
                            list.addAll(list_add);
                            adapters = new NewsAdapter(getApplicationContext(), list);
                            listView.setAdapter(adapters);
                            listView.setSelection(list.size() - 5);

                        }

                        @Override
                        public void onFailure(Call<List<NewsInfo>> call, Throwable t) {

                        }
                    });
                }
            }
        });

       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {

                String id_current = adapters.getNewsId(position);
                Intent intent = new Intent(AllNews.this, CurrentNews.class);
                intent.putExtra("id_current", id_current);
                intent.putExtra("category", category);
                intent.putExtra("id_position", id_current);
                startActivity(intent);
                finish();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AllNews.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public static void dialog (boolean value) {
        Log.v("keshav", "--------" + value);
        if (value == true) {
            /*Toast toast = Toast.makeText(MyApplication.getContext(), "Internet connection", Toast.LENGTH_LONG);
            toast.show();*/
        } else {
            Toast toast = Toast.makeText(MyApplication.getContext(), "Could not connect to internet", Toast.LENGTH_LONG);
            toast.show();
        }
    }

    private void registerNetworkBroadcastForNougat() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            registerReceiver(mNetworkReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        }
    }

    protected void unregisterNetworkChanges() {
        try {
            unregisterReceiver(mNetworkReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterNetworkChanges();
    }
}