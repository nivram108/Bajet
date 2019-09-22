package socks.sage.marvin.bajet.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import socks.sage.marvin.bajet.R;
import socks.sage.marvin.bajet.activity.LoginActivity;
import socks.sage.marvin.bajet.activity.MainActivity;

public class LoadUserDataManager extends AsyncTask<Integer, Integer, Void> {
    String LOGIN_ACTIVITY = "LOG_ACTIVITY";
    String MAIN_ACTIVITY = "MAIN_ACTIVITY";
    String mNextActivity;
    ProgressBar mProgressBar;
    TextView mProgressText;
    TextView mWelcomeText;
    Context mContext;
    Activity mActivity;
    String mUserName;
    int UPDATE_FRAME_PER_SECOND = 50;

    public LoadUserDataManager(Activity activity, Context context, View rootView) {
        mProgressBar = rootView.findViewById(R.id.progress_bar);
        mProgressText = rootView.findViewById(R.id.progress_tv);
        mWelcomeText = rootView.findViewById(R.id.welcome_back_tv);
        mContext = context;
        mUserName = "";
        ReadWriteManager readWriteManager = new ReadWriteManager();
        mActivity = activity;
        mUserName = readWriteManager.readFromFile(mContext);
        mNextActivity = "";
    }
    protected void onPreExecute(){
    }

    @Override
    protected Void doInBackground(Integer... param) {
        int waitingTime = param[0] * UPDATE_FRAME_PER_SECOND;

        ReadWriteManager readWriteManager = new ReadWriteManager();
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String userName = readWriteManager.readFromFile(mContext);
        Log.d("Nivram", "user name:" + userName);
        if(userName.equals("")) {
//         if(true) {
            mNextActivity = LOGIN_ACTIVITY;

            return null;
        } else {
            mNextActivity = MAIN_ACTIVITY;
            Log.d("Nivram", "user name:" + userName);

            while (waitingTime > 0) {
                try {
                    TimeUnit.MILLISECONDS.sleep(1000 / UPDATE_FRAME_PER_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                waitingTime--;
                publishProgress(100 - (waitingTime * 100 / (param[0] * UPDATE_FRAME_PER_SECOND)));
                if (waitingTime <= 0) break;
            }
            return null;
        }
    }

    protected void onProgressUpdate(Integer... values) {
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressText.setVisibility(View.VISIBLE);
        mWelcomeText.setText("Welcome! " + mUserName);
        mProgressBar.setVisibility(View.VISIBLE);
        int progress = values[0];
        mProgressBar.setProgress(progress);
        mProgressText.setText(Integer.toString(progress) + "%");
    }

    protected void onPostExecute(Void aVoid) {
        if(mNextActivity.equals(MAIN_ACTIVITY)) {
            Intent intent = new Intent(mContext, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            mActivity.finish();
        } else {
            Intent intent = new Intent(mContext, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
            mActivity.finish();
        }
    }
}
