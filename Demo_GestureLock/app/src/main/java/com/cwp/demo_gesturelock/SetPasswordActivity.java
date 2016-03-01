package com.cwp.demo_gesturelock;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SetPasswordActivity extends AppCompatActivity {

    private String key = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        GestureLockView glv = (GestureLockView) findViewById(R.id.container);
        glv.setCallback(new GestureLockView.GestureLockCallback() {
            @Override
            public void onFinish(String pwdString, GestureLockView.Result result) {
                key = pwdString;
                SharedPreferences sp = getSharedPreferences("key", Context.MODE_PRIVATE);
                SharedPreferences.Editor ed =sp.edit();
                ed.putString("password",key);
                ed.apply();
                Toast.makeText(SetPasswordActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
