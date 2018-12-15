package at.telvla.cricket;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CurrentNews extends AppCompatActivity{

    String Title, Description, Img, ImgBody, id_current, category, id_current_value;
    int int_id_current, count_title;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    TextView TitleView;
    TextView DiscriprionView;

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
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
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
                setTitle(Title.substring(0, 20) + "...");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    setTitle(Html.fromHtml(Title.substring(0, 20) + "...", Html.FROM_HTML_MODE_COMPACT));
                } else {
                    setTitle(Html.fromHtml(Title.substring(0, 20) + "..."));
                }

                if(count_title > 20){
                }else{
                    setTitle(Title);
                }
                if(Img.trim().length() == 0){
                    Img = "http://bukmekerskie-kontory.bet/static/cricket/3.jpg";
                }
                if(ImgBody.trim().length() == 0){
                    ImgBody = "http://news.images.itv.com/image/file/375961/mobile_article_img.jpg";
                }

                TitleView = (TextView) findViewById(R.id.title);
                DiscriprionView = (TextView) findViewById(R.id.disc);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    TitleView.setText(Html.fromHtml(Title, Html.FROM_HTML_MODE_COMPACT));
                    DiscriprionView.setText(Html.fromHtml(Description, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    TitleView.setText(Html.fromHtml(Title));
                    DiscriprionView.setText(Html.fromHtml(Description));
                }

            }
            @Override
            public void onFailure(Call<RegistrationResponse> call, Throwable t) {

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_slaider_img, container, false);
            Integer id_img = getArguments().getInt(ARG_SECTION_NUMBER);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.icon);

            try {

                Integer width;
                String link_img;
                List<String> mImageId = new ArrayList<String>();
                mImageId.add("");
                mImageId.add("https://s3.eu-central-1.amazonaws.com/krasota-style/img/blog1/articles/content/ZAQFmQGZ5CiTNhUWAxO7qPEeA-_Yhy2J-5BRyDCsd.jpg");
                mImageId.add("http://cdn.appaix.com/2016/0128/live-cricket-2014-11_1.jpg");
                mImageId.add("https://9968c6ef49dc043599a5-e151928c3d69a5a4a2d07a8bf3efa90a.ssl.cf2.rackcdn.com/84263-7.jpg");

                DisplayMetrics metrics = new DisplayMetrics();
                WindowManager windowManager = (WindowManager) getContext()
                        .getSystemService(Context.WINDOW_SERVICE);
                windowManager.getDefaultDisplay().getMetrics(metrics);
                width = metrics.widthPixels;

                if (mImageId.get(id_img).length() == 0) {
                    link_img = "file:///android_asset/cricket_img_1.jpg";
                } else {
                    link_img = mImageId.get(id_img);
                }

                Picasso.with(getContext())
                        .load(link_img)
                        .resize(width, 800)
                        .centerCrop()
                        .error(R.drawable.final_0)
                        .into(imageView);

            } catch (NullPointerException e) {
            }
            return rootView;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position + 1);
        }
        @Override
        public int getCount() {
            return 3;
        }
    }
}