package com.example.scholars.udacitypracticalquiz1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {

    private SharedPreferences preferences;
    private TextView emailText;
    private TextView usernameText;
    private TextView summaryText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        preferences = PreferenceManager.getDefaultSharedPreferences(DetailsActivity.this);
        String name = preferences.getString("USERNAME_KEY", "Demo User");
        String email = preferences.getString("EMAIL_KEY", "demouser@demo.com");
        String summary = preferences.getString("SUMMARY_KEY","demo summary");

        emailText = (TextView)findViewById(R.id.profile_email_txt);
        usernameText = (TextView)findViewById(R.id.profile_name_txt);
        summaryText = (TextView)findViewById(R.id.profile_summary_txt);
        usernameText.setText(name);
        emailText.setText(email);
        summaryText.setText(summary);
    }

}
