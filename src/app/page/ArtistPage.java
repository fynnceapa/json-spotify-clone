package app.page;

import app.audio.Collections.Album;
import app.user.artist.Artist;
import app.user.artist.Event;
import app.user.artist.Merch;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class ArtistPage extends BasicPage implements Visitable{
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merch;

    public ArtistPage(Artist artist) {
        super(artist.getName());
        this.albums = artist.getAlbums();
        this.events = artist.getEvents();
        this.merch = artist.getMerch();
    }

    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
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
        return "Albums:\n\t" + albumNames + "\n\nMerch:\n\t" + merchNames + "\n\nEvents:\n\t" + eventNames;
    }
}
