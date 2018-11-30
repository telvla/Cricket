package at.telvla.cricket;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ContactUs extends AppCompatActivity {
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

        setContentView(R.layout.activity_contact_us);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Contact us");

        Button button = (Button) findViewById(R.id.send);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText name_text = (EditText) findViewById(R.id.name);
                EditText name_mail = (EditText) findViewById(R.id.mail);
                EditText name_massege = (EditText) findViewById(R.id.sends);

                TextView name = (TextView) findViewById(R.id.name_cont);
                TextView mail = (TextView) findViewById(R.id.name_mail);
                TextView massege = (TextView) findViewById(R.id.name_massege);

                if (name_text.getText().length() == 0) {
                    name.setTextColor(Color.RED);
                } else {
                    name.setTextColor(Color.BLACK);
                }

                if (name_mail.getText().length() == 0) {
                    mail.setTextColor(Color.RED);
                } else {
                    mail.setTextColor(Color.BLACK);
                }

                if (name_massege.getText().length() == 0) {
                    massege.setTextColor(Color.RED);
                } else {
                    massege.setTextColor(Color.BLACK);
                }

                if (name_text.getText().length() == 0 || name_mail.getText().length() == 0 || name_massege.getText().length() == 0) {

                } else {
                    name_text.setText("");
                    name_mail.setText("");
                    name_massege.setText("");
                    name_text.setFocusableInTouchMode(true);
                    name_text.requestFocus();

                    AlertDialog.Builder builder = new AlertDialog.Builder(ContactUs.this);
                    builder.setTitle("Message!")
                            .setMessage("Thank you! Your message has been sent!")
                            .setIcon(R.drawable.iconvk)
                            .setCancelable(false)
                            .setNegativeButton("ОК",
                                    new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            dialog.cancel();
                                        }
                                    });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ContactUs.this, MainActivity.class);
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