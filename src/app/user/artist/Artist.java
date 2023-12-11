package app.user.artist;

import app.Admin;
import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Files.Song;
import app.audio.LibraryEntry;
import app.page.ArtistPage;
import app.player.Player;
import app.player.PlayerSource;
import app.user.User;
import fileio.input.CommandInput;
import lombok.Getter;
import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;

@Getter
public class Artist extends LibraryEntry {
    private String username;
    private String city;
    private Integer age;

    //private ArtistPage artistPage;

    private ArrayList<Album> albums;
    private ArrayList<Event> events;
    private ArrayList<Merch> merch;

    public Artist(final String username, final String city, final Integer age) {
        super(username);
        this.username = username;
        this.city = city;
        this.age = age;
        this.albums = new ArrayList<>();
        this.events = new ArrayList<>();
        this.merch = new ArrayList<>();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean checkAlbumExists(String albumName) {
        ArrayList<Album> albums = this.getAlbums();
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return true;
            }
        }
        return false;
    }

    public String addAlbum(CommandInput command) {
        albums.add(new Album(command));
        return username + " has added new album successfully.";
    }

    public Album getAlbum(String albumName) {
        ArrayList<Album> albums = this.getAlbums();
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        return null;
    }

    public boolean checkEventExists(String eventName) {
        ArrayList<Event> events = this.getEvents();
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return true;
            }
        }
        return false;
    }

    public String addEvent(CommandInput command) {
        if (checkEventExists(command.getName())) {
            return username + " has another event with the same name.";
        }
        Event event = new Event(command);
        if (!event.checkDate(command.getDate())) {
            return "Event for " + username + " does not have a valid date.";
        }
        events.add(event);
        return username + " has added new event successfully.";
    }

    public String addMerch(CommandInput command) {
        if (checkMerchExists(command.getName())) {
            return username + " has merchandise with the same name.";
        }
        if (command.getPrice() < 0) {
            return "Price for merchandise can not be negative.";
        }
        Merch m = new Merch(command);
        merch.add(m);
        return username + " has added new merchandise successfully.";
    }

    private boolean checkMerchExists(String name) {
        for (Merch m : merch) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAlbumRemove(String name) {
        Album album = getAlbum(name);
        ArrayList<User> users = (ArrayList<User>) Admin.getUsers();
        for (User user : users) {
            Player player = user.getPlayer();
            if (player.getSource() == null || player.getType().equals("podcast")) {
                continue;
            }
            PlayerSource source = player.getSource();
            if (source.getAudioCollection().getName().equals(album.getName())) {
                return false;
            }
            ArrayList<Song> albumSongs = album.getSongs();
            for (Song song : albumSongs) {
                if (source.getAudioFile().getName().equals(song.getName())) {
                    return false;
                }
            }
        }
        ArrayList<Playlist> playlists = (ArrayList<Playlist>) Admin.getPlaylists();
        ArrayList<Song> albumSongs = album.getSongs();
        for (Playlist playlist : playlists) {
            ArrayList<Song> playlistSongs = playlist.getSongs();
            for (Song song : albumSongs) {
                if (playlistSongs.contains(song)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String removeAlbum(String name) {
        if (!checkAlbumExists(name)) {
            return username + " doesn't have an album with the given name.";
        }
        if (!checkAlbumRemove(name)) {
            return username + " can't delete this album.";
        }
        Album album = getAlbum(name);
        for (Album a: this.albums) {
            if (a.getName().equals(name)) {
                this.albums.remove(a);
                break;
            }
        }
        Admin.removeAlbum(album);
        return username + " has removed the album successfully.";
    }

    public String removeEvent(String name) {
        if (!checkEventExists(name)) {
            return username + " doesn't have an event with the given name.";
        }
        for (Event e: this.events) {
            if (e.getName().equals(name)) {
                this.events.remove(e);
                break;
            }
        }
        return username + " deleted the event successfully.";
    }

    public int getLikes() {
        ArrayList<Album> albums = (ArrayList<Album>) Admin.getAlbums();
        int likes = 0;
        for (Album album : albums) {
            if (album.getOwner().equals(username)) {
                likes += album.getLikes();
            }
        }
        return likes;
    }

    public ArtistPage getArtistPage() {
        return new ArtistPage(this);
    }
}
