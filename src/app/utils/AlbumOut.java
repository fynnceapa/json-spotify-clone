package app.utils;

import app.audio.Collections.Album;

import java.util.ArrayList;

public class AlbumOut {
    private String name;
    private ArrayList<String> songs;

    public AlbumOut(final Album album) {
        this.name = album.getName();
        ArrayList<String> songs = new ArrayList<String>();
        for (int i = 0; i < album.getSongs().size(); i++) {
            songs.add(album.getSongs().get(i).getName());
        }
        this.songs = songs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<String> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<String> songs) {
        this.songs = songs;
    }
}
