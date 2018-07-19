package com.alexperezbuildthatapp.ideatreejackson;

public class MusicList {
    private String music_Titile;
    private String music_Album;
    private String music_Artist;
    private String music_Photo;
    private String duration;
    private String previewURL;

    public MusicList(String music_Title, String previewURL, String music_Artist, String music_Photo, String duration){
        this.music_Titile = music_Title;
        this.previewURL = previewURL;
        this.music_Artist = music_Artist;
        this.music_Photo = music_Photo;
        this.duration = duration;
    }

    public String getMusic_Titile() {
        return music_Titile;
    }

    public void setMusic_Titile(String music_Titile) {
        this.music_Titile = music_Titile;
    }

    public String getMusic_Album() {
        return music_Album;
    }

    public void setMusic_Album(String music_Album) {
        this.music_Album = music_Album;
    }

    public String getMusic_Artist() {
        return music_Artist;
    }

    public void setMusic_Artist(String music_Artist) {
        this.music_Artist = music_Artist;
    }

    public String getMusic_Photo() {
        return music_Photo;
    }

    public void setMusic_Photo(String music_Photo) {
        this.music_Photo = music_Photo;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public void setPreviewURL(String previewURL) {
        this.previewURL = previewURL;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
