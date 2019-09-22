package socks.sage.marvin.bajet.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import socks.sage.marvin.bajet.R;
import socks.sage.marvin.bajet.utility.LoadUserDataManager;
import socks.sage.marvin.bajet.utility.ReadWriteManager;

public class WelcomeActivity extends AppCompatActivity {
    TextView progressText;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        progressText = findViewById(R.id.progress_tv);
        progressBar = findViewById(R.id.progress_bar);
        progressText.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        new LoadUserDataManager(this, this, progressText.getRootView()).execute(2);
//        toastMessage(this,2);
    }
}
