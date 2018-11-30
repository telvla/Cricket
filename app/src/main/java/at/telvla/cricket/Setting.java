package at.telvla.cricket;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Setting extends AppCompatActivity {
    TextView checkBoxColorText, text_dark_theme, text_size, color_value, text_check_push;
    CheckBox enableBox;
    Switch dark_theme, switch_text_size, switch_color, switch_push_active;

    String file_name = "file_dark_theme";
    String file_dark_theme;
    File file;

    String file_naem_size = "text_size";
    String file_text_size;
    File file_size;

    String file_color_value = "color_value";
    String file_color_v;
    File file_color;

    String file_checkBoxColor = "checkBoxColor";
    String file_checkBox;
    File file_check;

    String file_check_push = "CheckPushActive";
    String check_push_active;
    File file_ch_push;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        file = new File();
        file_dark_theme = file.File_Read(getApplication().getApplicationContext(), file_name);
        file_size = new File();
        file_text_size = file_size.File_Read(getApplication().getApplicationContext(), file_naem_size);
        file_color = new File();
        file_color_v = file_color.File_Read(getApplication().getApplicationContext(), file_color_value);
        file_check = new File();
        file_checkBox = file_check.File_Read(getApplication().getApplicationContext(), file_checkBoxColor);
        file_ch_push = new File();
        check_push_active = file_ch_push.File_Read(getApplication().getApplicationContext(), file_check_push);

        if (file_dark_theme != null) {
            if (file_dark_theme.equals("dark theme")) {
                setTheme(R.style.NewsAppThemeDark);
            } else {
                setTheme(R.style.NewsAppThemeLight);
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        enableBox = (CheckBox) findViewById(R.id.checkBoxColor);
        checkBoxColorText = (TextView) findViewById(R.id.checkBoxColorText);
        /**/
        dark_theme = (Switch)  findViewById(R.id.dark_theme);
        text_dark_theme = (TextView) findViewById(R.id.text_dark_theme);
        /**/
        switch_text_size = (Switch)  findViewById(R.id.switch_text_size);
        text_size = (TextView) findViewById(R.id.text_size);
        /**/
        switch_color = (Switch)  findViewById(R.id.switch_color);
        color_value = (TextView) findViewById(R.id.color_value);
        /**/
        switch_push_active = (Switch)  findViewById(R.id.check_push);
        text_check_push = (TextView) findViewById(R.id.text_check_push);

        /**/
        if (check_push_active != null) {
            if (check_push_active.equals("check push")) {
                switch_push_active.setChecked(true);
                text_check_push.setText("Check paush: active");
            } else {
                text_check_push.setText("Check paush: no active");
            }
        }

        if (file_dark_theme != null) {
            if (file_dark_theme.equals("dark theme")) {
                dark_theme.setChecked(true);
                text_dark_theme.setText("Dark theme: active");
            } else {
                text_dark_theme.setText("Dark theme: no active");
            }
        }

        if (file_text_size != null) {
            if (file_text_size.equals("small")) {
                switch_text_size.setChecked(true);
                text_size.setText("Size theme: small");
            } else {
                text_size.setText("Size theme: big");
            }
        }

        if (file_color_v != null) {
            if (file_color_v.equals("green")) {
                switch_color.setChecked(true);
                color_value.setText("Color theme: green");
            } else {
                color_value.setText("Color theme: red");
            }
        }

        if (file_checkBox != null) {
            if (file_checkBox.equals("active")) {
                checkBoxColorText.setText("Dark theme: active");
                enableBox.setChecked(true);
            } else {
                checkBoxColorText.setText("Dark theme: no active");
            }
        }

        /**/
        switch_color.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    color_value.setText("Color theme: green");
                    file_color = new File();
                    file_color.File_Entry(getApplication().getApplicationContext(), file_color_value, "green");
                } else {
                    color_value.setText("Color theme: red");
                    file_color = new File();
                    file_color.File_Entry(getApplication().getApplicationContext(), file_color_value, "red");
                }
            }
        });

        /**/
        switch_text_size.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    text_size.setText("Size theme: small");
                    file_size = new File();
                    file_size.File_Entry(getApplication().getApplicationContext(), file_naem_size, "small");
                } else {
                    text_size.setText("Size theme: big");
                    file_size = new File();
                    file_size.File_Entry(getApplication().getApplicationContext(), file_naem_size, "big");
                }
            }
        });

        /**/
        dark_theme.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    text_dark_theme.setText("Dark theme: active");
                    file = new File();
                    file.File_Entry(getApplication().getApplicationContext(), file_name, "dark theme");
                } else {
                    text_dark_theme.setText("Dark theme: no active");
                    file = new File();
                    file.File_Entry(getApplication().getApplicationContext(), file_name, "light theme");
                }

                Intent mIntent = getIntent();
                finish();
                startActivity(mIntent);
            }
        });

        /**/
        enableBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    checkBoxColorText.setText("Dark theme: active");
                    file_check = new File();
                    file_check.File_Entry(getApplication().getApplicationContext(), file_checkBoxColor, "active");
                } else {
                    checkBoxColorText.setText("Dark theme: no active");
                    file_check = new File();
                    file_check.File_Entry(getApplication().getApplicationContext(), file_checkBoxColor, "no active");
                }
            }
        });

        /**/
        switch_push_active.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    text_check_push.setText("Check push: active");
                    file_ch_push = new File();
                    file_ch_push.File_Entry(getApplication().getApplicationContext(), file_check_push, "check push");
                } else {
                    text_check_push.setText("Check push: no active");
                    file_ch_push = new File();
                    file_ch_push.File_Entry(getApplication().getApplicationContext(), file_check_push, "check no push");
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Setting.this, MainActivity.class);
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