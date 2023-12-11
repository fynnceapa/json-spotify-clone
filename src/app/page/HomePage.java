package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;

import java.util.ArrayList;

public class HomePage extends BasicPage implements Visitable {
    ArrayList<Song> likedSongs;
    ArrayList<Playlist> followedPlaylists;
    public HomePage(User user) {
        super("Home");
        this.likedSongs = user.getLikedSongs();
        this.followedPlaylists = user.getFollowedPlaylists();
    }

    public String getString() {
        ArrayList<Song> sorted = new ArrayList<>(likedSongs);
        sorted.sort((o1, o2) -> o2.getLikes() - o1.getLikes());
        ArrayList<String> songs = new ArrayList<>();
        for (Song song : sorted) {
            songs.add(song.getName());
            if (songs.size() == 5) {
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
            if (playlists.size() == 5) {
                break;
            }
        }
        return "Liked songs:\n\t" + songs + "\n\nFollowed playlists:\n\t" + playlists;
    }

    @Override
    public String toString() {
        return getString();
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
