package com.example.scholars.udacitypracticalquiz1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.PersistableBundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String EMAIL_KEY = "EMAIL_KEY";
    private static final String USERNAME_KEY = "USERNAME_KEY";
    private static final String SUMMARY_KEY = "SUMMARY_KEY";
    private EditText emailText;
    private EditText usernameText;
    private EditText summaryText;
    private Button submitBtn;
    private SharedPreferences preferences;
    private String emailTextContent;
    private String usernameTextContent;
    private String summaryTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = (EditText) findViewById(R.id.user_email_edt_txt);
        usernameText = (EditText) findViewById(R.id.username_edt_txt);
        summaryText = (EditText) findViewById(R.id.summary_edt_txt);
        submitBtn = findViewById(R.id.submit_btn);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(EMAIL_KEY) && savedInstanceState.containsKey(USERNAME_KEY) && savedInstanceState.containsKey(SUMMARY_KEY)) {
                emailText.setText(savedInstanceState.getString(EMAIL_KEY));
                usernameText.setText(savedInstanceState.getString(USERNAME_KEY));
                summaryText.setText(savedInstanceState.getString(SUMMARY_KEY));
            }
        }
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!TextUtils.isEmpty(emailText.getText().toString()) && !TextUtils.isEmpty(usernameText.getText().toString()) && !TextUtils.isEmpty(summaryText.getText().toString())) {
                    emailTextContent = emailText.getText().toString();
                    usernameTextContent = usernameText.getText().toString();
                    summaryTextContent = summaryText.getText().toString();
                    initSharedPreferences();
                    emailText.getText().clear();
                    usernameText.getText().clear();
                    summaryText.getText().clear();

                    Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Please give valid input", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void initSharedPreferences() {
        preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USERNAME_KEY, usernameTextContent);
        editor.putString(EMAIL_KEY, emailTextContent);
        editor.putString(SUMMARY_KEY, summaryTextContent);
        editor.apply();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState);
        outState.putString(EMAIL_KEY, emailTextContent);
        outState.putString(USERNAME_KEY, usernameTextContent);
        outState.putString(SUMMARY_KEY, summaryTextContent);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_details) {
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }
}
