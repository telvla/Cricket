package at.telvla.cricket;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class CurrentArticle extends AppCompatActivity {
    Context context;
    String url;
    ImageView img_current;
    TextView title;
    TextView content;
    Display display;
    Point size;
    int width_display;

    File file;
    String file_dark_theme;
    String file_name = "file_dark_theme";

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

        setContentView(R.layout.activity_current_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        display = getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        width_display = size.x;

        CurrentNotification notificationcurrent = new CurrentNotification();

        img_current = (ImageView) findViewById(R.id.img_current);
        title = (TextView) findViewById(R.id.title);
        content = (TextView) findViewById(R.id.content);

        url = notificationcurrent.getImg();

        if (url == null) {
            url = "file:///android_asset/cricket_img_1.jpg";
        }

        Picasso.with(context)
                .load(url)
                .resize(width_display, 350)
                .centerCrop()
                .error(R.drawable.drawer_shadow)
                .into(img_current);

        setTitle(notificationcurrent.getTitle().substring(0, 20) + "...");
        title.setText(notificationcurrent.getTitle());
        content.setText(notificationcurrent.getContent());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CurrentArticle.this, MainActivity.class);
        startActivity(intent);
        finish();
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
}