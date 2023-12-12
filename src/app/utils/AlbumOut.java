package app.utils;

import app.audio.Collections.Album;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class AlbumOut {
    private String name;
    private ArrayList<String> songs;

    public AlbumOut(final Album album) {
        this.name = album.getName();
        ArrayList<String> songNames = new ArrayList<String>();
        for (int i = 0; i < album.getSongs().size(); i++) {
            songNames.add(album.getSongs().get(i).getName());
        }
        this.songs = songNames;
    }

    /**
     * Sets the name of the album.
     *
     * @param name the name of the album
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the list of songs for the album.
     *
     * @param songs the list of songs to set
     */
    public void setSongs(final ArrayList<String> songs) {
        this.songs = songs;
    }
}
