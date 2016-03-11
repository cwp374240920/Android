package com.cwp.mymusicplayer;

/**
 * Created by 曹伟鹏 on 2015/11/27.
 */
public class Music {
    private String Music_Name = null;
    private String Artist = null;
    private String Music_Path = null;
    private int Music_duration = 0;
    private String Album = null;

    public Music(){}
    public Music(String music_Name , String artist){
        this.Music_Name = music_Name;
        this.Artist = artist;
    }

    public void setAlbum(String album) {Album = album;}

    public void setArtist(String artist) {
        Artist = artist;
    }

    public void setMusic_duration(int music_duration) {
        Music_duration = music_duration;
    }

    public void setMusic_Name(String music_Name) {
        Music_Name = music_Name;
    }

    public void setMusic_Path(String music_Path) {
        Music_Path = music_Path;
    }

    public String getAlbum() {
        return Album;
    }

    public String getArtist() {
        return Artist;
    }

    public int getMusic_duration() {
        return Music_duration;
    }

    public String getMusic_Name() {
        return Music_Name;
    }

    public String getMusic_Path() {
        return Music_Path;
    }
}
