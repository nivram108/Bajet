package socks.sage.marvin.bajet.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import socks.sage.marvin.bajet.R;
import socks.sage.marvin.bajet.utility.LoadUserDataManager;

public class BudgetSettingActivity extends AppCompatActivity {
    TextView progressText;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_setting);
//        toastMessage(this,2);
    }
}