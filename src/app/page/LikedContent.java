package app.page;

import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.user.User;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class LikedContent extends BasicPage implements Visitable {
    private final ArrayList<Song> likedSongs;
    private final ArrayList<Playlist> followedPlaylists;

    public LikedContent(final User user) {
        super("LikedContent");
        this.likedSongs = user.getLikedSongs();
        this.followedPlaylists = user.getFollowedPlaylists();
    }

    /**
     * Accepts a visitor and returns the result of visiting this LikedContent object.
     *
     * @param visitor the visitor object to accept
     * @return the result of visiting this LikedContent object
     */
    @Override
    public String accept(final Visitor visitor) {
        return visitor.visit(this);
    }

    /**
     * Returns a string representation of the LikedContent object.
     * The string includes the list of liked songs and followed playlists.
     *
     * @return a string representation of the LikedContent object
     */
    @Override
    public String toString() {
        return "Liked songs:\n\t" + likedSongs + "\n\nFollowed playlists:\n\t" + followedPlaylists;
    }
}
