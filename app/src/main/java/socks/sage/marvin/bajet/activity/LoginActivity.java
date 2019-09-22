package socks.sage.marvin.bajet.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import socks.sage.marvin.bajet.R;
import socks.sage.marvin.bajet.utility.ReadWriteManager;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button directlyStart = findViewById(R.id.direct_start_btn);
        final Context context = this;
        directlyStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setEnterUserNameDialog();
            }
        });
    }

    public void setUserNameData(String userName) {
        ReadWriteManager readWriteManager = new ReadWriteManager();
        readWriteManager.writeToFile(userName, this);
    }

    public void setEnterUserNameDialog() {
        final Context context = this;
        final Activity loginActivity = this;
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_enter_login_name);
        final EditText enterNameEt = dialog.findViewById(R.id.enter_name_et);
        enterNameEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                Log.d("NIVRAM", "Key Event : " + Integer.toString(actionId));
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    Log.d("NIVRAM", "Key Event : " + Integer.toString(actionId));
                    String name = enterNameEt.getText().toString();
                    if(!name.equals("")) return handled;
                    setUserNameData(name);
                    dialog.dismiss();
                    startMain();
                    handled = true;
                }
                return handled;
            }
        }); // end of set EnterKey Listener
        ImageButton sendBtn = dialog.findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String name = enterNameEt.getText().toString();
                if (!name.equals("")) {
                    setUserNameData(name);
                    dialog.dismiss();
                    startMain();
                }
            }
        });
        dialog.show();
    }

    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
