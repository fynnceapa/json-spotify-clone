package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class HomePage extends BasicPage implements Visitable {
    private ArrayList<Song> likedSongs;
    private ArrayList<Playlist> followedPlaylists;

    private static final int FIVE = 5;

    public HomePage(final User user) {
        super("Home");
        this.likedSongs = user.getLikedSongs();
        this.followedPlaylists = user.getFollowedPlaylists();
    }

    /**
     * Returns a formatted string containing the names of the top 5 liked songs
     * and followed playlists.
     *
     * @return a formatted string with the names of the top 5 liked songs and followed playlists
     */
    public String getString() {
        ArrayList<Song> sorted = new ArrayList<>(likedSongs);
        sorted.sort((o1, o2) -> o2.getLikes() - o1.getLikes());
        ArrayList<String> songs = new ArrayList<>();
        for (Song song : sorted) {
            songs.add(song.getName());
            if (songs.size() == FIVE) {
                break;
            }
        }
        ArrayList<Playlist> sortedP = new ArrayList<>(followedPlaylists);
        sortedP.sort((o1, o2) -> {
            int o1Likes = 0;
            int o2Likes = 0;
            for (Song song : o1.getSongs()) {
                o1Likes += song.getLikes();
            }
            for (Song song : o2.getSongs()) {
                o2Likes += song.getLikes();
            }
            return o2Likes - o1Likes;
        });
        ArrayList<String> playlists = new ArrayList<>();
        for (Playlist playlist : sortedP) {
            playlists.add(playlist.getName());
            if (playlists.size() == FIVE) {
                break;
            }
        }
        return "Liked songs:\n\t" + songs + "\n\nFollowed playlists:\n\t" + playlists;
    }

    /**
        * Returns a string representation of the object.
        *
        * @return a string representation of the object
        */
    @Override
    public String toString() {
        return getString();
    }

    /**
     * Accepts a visitor and returns the result of visiting this object.
     *
     * @param visitor the visitor object
     * @return the result of visiting this object
     */
    @Override
    public String accept(final Visitor visitor) {
        return visitor.visit(this);
    }
}
