package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.user.artist.Artist;
import fileio.input.CommandInput;
import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class Album extends AudioCollection{
    private Integer releaseYear;
    private String description;
    private ArrayList<Song> songs;
    private Integer likes;

    public Album(CommandInput command) {
        super(command.getName(), command.getUsername());
        this.releaseYear = command.getReleaseYear();
        this.description = command.getDescription();
        this.songs = new ArrayList<>();
        this.likes = 0;
        for (SongInput song : command.getSongs()) {
            addSong(song);
        }
    }

    private void addSong(SongInput song) {
        songs.add(new Song(song.getName(), song.getDuration(), song.getAlbum(), song.getTags(),
                song.getLyrics(), song.getGenre(), song.getReleaseYear(), song.getArtist()));
    }

    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    @Override
    public AudioFile getTrackByIndex(int index) {
        return songs.get(index);
    }

    @Override
    public boolean matchesOwner(final String user) {
        return this.getOwner().equals(user);
    }
    @Override
    public boolean matchesName(final String name) {
        return getName().toLowerCase().startsWith(name.toLowerCase());
    }

    public Integer getLikes() {
        Integer totalLikes = 0;
        for (Song song : songs) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }
}
