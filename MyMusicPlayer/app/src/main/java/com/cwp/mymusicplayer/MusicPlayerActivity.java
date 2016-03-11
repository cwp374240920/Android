package com.cwp.mymusicplayer;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class MusicPlayerActivity extends AppCompatActivity {

    private MediaPlayer player;
    private TextView textView;
    private String duration;
    private String path,name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);

        Intent intent = getIntent();
        duration =intent.getStringExtra("duration");
        name = intent.getStringExtra("name");
        textView = (TextView) findViewById(R.id.currentName);
        textView.setText("正在播放："+name);
        player = new MediaPlayer();
        path =intent.getStringExtra("path");
        try {
            //show message:
            Log.i("!","SHOW MESSAGE!!!\n"+"--------------------------------------------------\n"+
                    "name :"+name+"\npath : "+path+"\nduration :"+duration+
            "\n---------------------------------------------------------");
            player.reset();
            player.setDataSource(path);
            player.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        player.start();
    }
}
