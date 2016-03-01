package com.cwp.demo_gesturelock;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import com.cwp.demo_gesturelock.GestureLockView.GestureLockCallback;
import com.cwp.demo_gesturelock.GestureLockView.Result;

public class MainActivity extends Activity {

    private String key;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("key",MODE_PRIVATE);
        key = sp.getString("password","1235789");
        GestureLockView glv = (GestureLockView) findViewById(R.id.glv);
        glv.setCallback(new GestureLockCallback() {

            @Override
            public void onFinish(String pwdString, Result result) {
                boolean resu = key.equals(pwdString);
                result.setRight(resu);
                Toast.makeText(MainActivity.this, resu ? "密码正确" : "密码错误", Toast.LENGTH_LONG).show();
                if (resu) {
                    //TODO: 跳转
                    Intent intent = new Intent(MainActivity.this,WeatherReportActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
