package at.telvla.cricket;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class Test extends AppCompatActivity {

    private BroadcastReceiver mNetworkReceiver;
    static TextView tv_check_connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        WebView webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName("utf-8");
        webView.setPadding(0, 0, 0, 0);
        String WebBody = "<html><body style='padding: 0px;margin: 0px;'>" +
                    "<a href='http://lira-shop.com/'>test</a>" +
                    "</body></html>";
        webView.loadData(WebBody, "text/html", "UTF-8");

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Log.v("webview_test", "первый --" + view + url);
                if(url.equals("http://lira-shop.com/")) {
                    Log.v("webview_test", "work");
                    view.loadUrl(url);
                }
                return true;
            }
        });







    }
}