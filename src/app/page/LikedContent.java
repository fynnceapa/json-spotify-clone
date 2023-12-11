package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;

import java.util.ArrayList;

public class LikedContent extends BasicPage implements Visitable {
    private final ArrayList<Song> likedSongs;
    private final ArrayList<Playlist> followedPlaylists;
    public LikedContent (User user) {
        super("LikedContent");
        this.likedSongs = user.getLikedSongs();
        this.followedPlaylists = user.getFollowedPlaylists();
    }
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
    @Override
    public String toString() {
        return "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t" + followedPlaylists;
    }
}
