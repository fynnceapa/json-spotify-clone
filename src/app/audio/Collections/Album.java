package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Song;
import fileio.input.CommandInput;
import fileio.input.SongInput;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class Album extends AudioCollection {
    private Integer releaseYear;
    private String description;
    private ArrayList<Song> songs;
    private Integer likes;

    public Album(final CommandInput command) {
        super(command.getName(), command.getUsername());
        this.releaseYear = command.getReleaseYear();
        this.description = command.getDescription();
        this.songs = new ArrayList<>();
        this.likes = 0;
        for (SongInput song : command.getSongs()) {
            addSong(song);
        }
    }

    private void addSong(final SongInput song) {
        songs.add(new Song(song.getName(), song.getDuration(), song.getAlbum(), song.getTags(),
                song.getLyrics(), song.getGenre(), song.getReleaseYear(), song.getArtist()));
    }

    /**
     * Returns the number of tracks in the album.
     *
     * @return the number of tracks in the album
     */
    @Override
    public int getNumberOfTracks() {
        return songs.size();
    }

    /**
     * Retrieves the audio file at the specified index.
     *
     * @param index the index of the audio file to retrieve
     * @return the audio file at the specified index
     */
    @Override
    public AudioFile getTrackByIndex(final int index) {
        return songs.get(index);
    }

    /**
     * Checks if the specified user is the owner of the album.
     *
     * @param user the user to check against the album's owner
     * @return true if the specified user is the owner of the album, false otherwise
     */
    @Override
    public boolean matchesOwner(final String user) {
        return this.getOwner().equals(user);
    }

    /**
     * Checks if the album name matches the given name.
     *
     * @param name The name to match against.
     * @return true if the album name starts with the given name (case-insensitive),
     * false otherwise.
     */
    @Override
    public boolean matchesName(final String name) {
        return getName().toLowerCase().startsWith(name.toLowerCase());
    }

    /**
     * Returns the total number of likes for all songs in the album.
     *
     * @return the total number of likes for all songs in the album
     */
    public Integer getLikes() {
        Integer totalLikes = 0;
        for (Song song : songs) {
            totalLikes += song.getLikes();
        }
        return totalLikes;
    }
}
