package com.cwp.mymusicplayer;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 曹伟鹏 on 2015/11/27.
 */
public class MusicAdapter extends BaseAdapter {

    private ArrayList<Music> list = new ArrayList<>();
    Context context;

    static class ViewHolder{
        TextView nameTv;
        TextView artistTv;
    }

    public MusicAdapter(Context context){
        super();
        this.context = context;
    }

    public ArrayList<Music> getList() {
        return list;
    }

    public void add(Music music){
        list.add(music);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Music getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
            holder= new ViewHolder();
            holder.artistTv = (TextView) convertView.findViewById(R.id.Artist);
            holder.nameTv = (TextView) convertView.findViewById(R.id.MusicName);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
//        ImageView imageView = (ImageView) convertView.findViewById(R.id.AlbumImg);
//        TextView nameTv = (TextView) convertView.findViewById(R.id.MusicName);
//        nameTv.setMovementMethod(new ScrollingMovementMethod());
//        TextView artistTv = (TextView) convertView.findViewById(R.id.Artist);
        for (int i=0;i<list.size();i++) {
            holder.nameTv.setText(list.get(position).getMusic_Name());
            holder.artistTv.setText(list.get(position).getArtist());
        }
        notifyDataSetChanged();
        return convertView;
    }
}
