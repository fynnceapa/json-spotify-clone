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

import java.util.ArrayList;

@Getter
public class Artist extends LibraryEntry {
    private String username;
    private String city;
    private Integer age;

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

    /**
     * Sets the username for the artist.
     *
     * @param username the username to be set
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Sets the city of the artist.
     *
     * @param city the city to set
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Sets the age of the artist.
     *
     * @param age the age of the artist
     */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /**
     * Checks if an album with the specified name exists in the artist's collection.
     *
     * @param albumName the name of the album to check
     * @return true if the album exists, false otherwise
     */
    public boolean checkAlbumExists(final String albumName) {
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new album to the artist's collection.
     *
     * @param command the command input containing the details of the album
     * @return a message indicating the successful addition of the album
     */
    public String addAlbum(final CommandInput command) {
        albums.add(new Album(command));
        return username + " has added new album successfully.";
    }

    /**
     * Retrieves the album with the specified name.
     *
     * @param albumName the name of the album to retrieve
     * @return the album with the specified name, or null if not found
     */
    public Album getAlbum(final String albumName) {
        for (Album album : albums) {
            if (album.getName().equals(albumName)) {
                return album;
            }
        }
        return null;
    }

    /**
     * Checks if an event with the specified name exists for the artist.
     *
     * @param eventName the name of the event to check
     * @return true if an event with the specified name exists, false otherwise
     */
    public boolean checkEventExists(final String eventName) {
        for (Event event : events) {
            if (event.getName().equals(eventName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new event for the artist.
     *
     * @param command the command input containing the event details
     * @return a string indicating the result of the operation
     */
    public String addEvent(final CommandInput command) {
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

    /**
     * Adds a new merchandise for the artist.
     *
     * @param command the command input containing the merchandise details
     * @return a string indicating the result of the operation
     */
    public String addMerch(final CommandInput command) {
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

    private boolean checkMerchExists(final String name) {
        for (Merch m : merch) {
            if (m.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkAlbumRemove(final String name) {
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

    /**
     * Removes an album with the given name from the artist's collection.
     *
     * @param name the name of the album to be removed
     * @return a message indicating the result of the removal operation
     */
    public String removeAlbum(final String name) {
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

    /**
     * Removes an event with the given name from the artist's list of events.
     *
     * @param name the name of the event to be removed
     * @return a message indicating whether the event was successfully removed or not
     */
    public String removeEvent(final String name) {
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

    /**
     * Returns the total number of likes received by the artist.
     * The likes are calculated by summing up the likes of all albums owned by the artist.
     *
     * @return the total number of likes received by the artist
     */
    public int getLikes() {
        ArrayList<Album> allAlbums = (ArrayList<Album>) Admin.getAlbums();
        int likes = 0;
        for (Album album : allAlbums) {
            if (album.getOwner().equals(username)) {
                likes += album.getLikes();
            }
        }
        return likes;
    }

    /**
     * Returns the ArtistPage associated with this Artist.
     *
     * @return the ArtistPage object
     */
    public ArtistPage getArtistPage() {
        return new ArtistPage(this);
    }
}
