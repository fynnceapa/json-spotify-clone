package app.page;

import app.audio.Collections.Album;
import app.user.artist.Event;
import app.user.artist.Merch;
import lombok.Getter;

import java.util.ArrayList;
@Getter
public class ArtistPage extends BasicPage{
    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merch;

    public ArtistPage() {
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.merch = new ArrayList<>();
    }

    public void addAlbum(Album album) {
        albums.add(album);
    }

    public void addEvent(Event event) {
        events.add(event);
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

    public void addMerch(Merch merch) {
        this.merch.add(merch);
    }

    public void removeAlbum(String name) {
        for (Album a: this.albums) {
            if (a.getName().equals(name)) {
                this.albums.remove(a);
                break;
            }
        }
    }
}
