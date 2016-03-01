package com.cwp.demo_gesturelock;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Selector extends AppCompatActivity {

    private Button setBtn,unlockBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector);

        setBtn = (Button) findViewById(R.id.setter);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Selector.this,SetPasswordActivity.class);
                startActivity(intent1);
            }
        });
        unlockBtn = (Button) findViewById(R.id.unlock);
        unlockBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Selector.this,MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}
