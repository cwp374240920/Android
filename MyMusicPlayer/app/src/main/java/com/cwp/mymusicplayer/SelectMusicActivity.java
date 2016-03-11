package com.cwp.mymusicplayer;

import android.app.AlertDialog;
import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class SelectMusicActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listView;
    private  Button button1,button2,button3;
    private MusicAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_music);
        //initial the data&view
        initData();
        initView();
    }

    private void initData() {
        adapter = new MusicAdapter(this);
        //get music
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        if (cursor != null) {
            while (cursor.moveToNext()){
                Music music = new Music();
                music.setMusic_Name(cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME)));
                music.setAlbum(cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM)));
                music.setArtist(cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
                music.setMusic_Path(cursor.getString(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
                music.setMusic_duration(cursor.getInt(
                        cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
                adapter.add(music);
            }
            cursor.close();
        }
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list_Music);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("!", String.valueOf(position));
                String path = adapter.getList().get(position).getMusic_Path();
                Intent intent = new Intent(SelectMusicActivity.this,MusicPlayerActivity.class);
                intent.putExtra("path",path);
                intent.putExtra("name",adapter.getList().get(position).getMusic_Name());
                intent.putExtra("duration",adapter.getList().get(position).getMusic_duration());
                startActivity(intent);
            }
        });
        button1 = (Button) findViewById(R.id.direction1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.direction2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.direction3);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.direction1:showDialog();break;
            case R.id.direction2:showDialog();break;
            case R.id.direction3:showDialog();break;
            default:break;
        }
    }
    public void showDialog(){
        AlertDialog.Builder alert =new AlertDialog.Builder(this);
        alert.setTitle("Attention Please!");
        alert.setMessage("嘿嘿嘿呼呼嘿");
        alert.setPositiveButton("Confirm", null);
        alert.show();
    }
}
