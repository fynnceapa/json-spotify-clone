package app.user.artist;

import app.Admin;
import app.audio.Collections.Album;
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

    private ArtistPage artistPage;

    public Artist(final String username, final String city, final Integer age) {
        super(username);
        this.username = username;
        this.city = city;
        this.age = age;
        this.artistPage = new ArtistPage();
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
        ArrayList<Album> albums = artistPage.getAlbums();
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return true;
            }
        }
        return false;
    }

    public String addAlbum(CommandInput command) {
        ArtistPage artistPage = this.artistPage;
        artistPage.addAlbum(new Album(command));
        return username + " has added new album successfully.";
    }

    public Album getAlbum(String albumName) {
        ArrayList<Album> albums = artistPage.getAlbums();
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        return null;
    }

    public boolean checkEventExists(String eventName) {
        ArrayList<Event> events = artistPage.getEvents();
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
            return "Event for " + username + "does not have a valid date.";
        }
        artistPage.addEvent(event);
        return username + " has added new event successfully.";
    }
    public ArrayList<Album> getAlbums() {
        return artistPage.getAlbums();
    }

    public String addMerch(CommandInput command) {
        if (checkMerchExists(command.getName())) {
            return username + " has merchandise with the same name.";
        }
        if (command.getPrice() < 0) {
            return "Price for merchandise can not be negative.";
        }
        Merch merch = new Merch(command);
        artistPage.addMerch(merch);
        return username + " has added new merchandise successfully.";
    }

    private boolean checkMerchExists(String name) {
        ArrayList<Merch> merch = artistPage.getMerch();
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
        artistPage.removeAlbum(name);
        Admin.removeAlbum(album);
        return username + " has removed the album successfully.";
    }
}
