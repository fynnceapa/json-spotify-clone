package app.page;

import app.audio.Collections.Album;
import app.user.artist.Artist;
import app.user.artist.Event;
import app.user.artist.Merch;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class ArtistPage extends BasicPage implements Visitable {
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merch;

    public ArtistPage(final Artist artist) {
        super(artist.getName());
        this.albums = artist.getAlbums();
        this.events = artist.getEvents();
        this.merch = artist.getMerch();
    }

    /**
     * Accepts a visitor and returns the result of visiting this ArtistPage.
     *
     * @param visitor the visitor to accept
     * @return the result of visiting this ArtistPage
     */
    public String accept(final Visitor visitor) {
        return visitor.visit(this);
    }

    /**
     * Returns a string representation of the ArtistPage object.
     * The string includes the names of the albums, merch, and events associated with the artist.
     *
     * @return A string representation of the ArtistPage object.
     */
    @Override
    public String toString() {
        ArrayList<String> albumNames = new ArrayList<>();
        ArrayList<String> merchNames = new ArrayList<>();
        ArrayList<String> eventNames = new ArrayList<>();
        for (Album a: this.albums) {
            albumNames.add(a.getName());
        }
        for (Merch m: this.merch) {
            merchNames.add(m.toString());
        }
        for (Event e: this.events) {
            eventNames.add(e.toString());
        }
        return "Albums:\n\t" + albumNames + "\n\nMerch:\n\t" + merchNames
                + "\n\nEvents:\n\t" + eventNames;
    }
}
