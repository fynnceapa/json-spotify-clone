package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.Player;
import app.player.PlayerSource;
import app.user.artist.Artist;
import app.user.User;
import app.user.host.Host;
import app.utils.Enums;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;
import fileio.input.PodcastInput;
import fileio.input.SongInput;
import fileio.input.UserInput;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
    @Getter
    private static List<User> users = new ArrayList<>();
    private static List<Song> songs = new ArrayList<>();
    private static List<Podcast> podcasts = new ArrayList<>();
    private static List<Artist> artists = new ArrayList<>();
    private static List<Album> albums = new ArrayList<>();
    private static List<Host> hosts = new ArrayList<>();
    private static int timestamp = 0;
    private static final int LIMIT = 5;

    private Admin() {
    }

    /**
     * Sets users.
     *
     * @param userInputList the user input list
     */
    public static void setUsers(final List<UserInput> userInputList) {
        users = new ArrayList<>();
        for (UserInput userInput : userInputList) {
            users.add(new User(userInput.getUsername(), userInput.getAge(), userInput.getCity()));
        }
    }

    /**
     * Sets songs.
     *
     * @param songInputList the song input list
     */
    public static void setSongs(final List<SongInput> songInputList) {
        songs = new ArrayList<>();
        for (SongInput songInput : songInputList) {
            songs.add(new Song(songInput.getName(), songInput.getDuration(), songInput.getAlbum(),
                    songInput.getTags(), songInput.getLyrics(), songInput.getGenre(),
                    songInput.getReleaseYear(), songInput.getArtist()));
        }
    }


    /**
     * Sets podcasts.
     *
     * @param podcastInputList the podcast input list
     */
    public static void setPodcasts(final List<PodcastInput> podcastInputList) {
        podcasts = new ArrayList<>();
        for (PodcastInput podcastInput : podcastInputList) {
            List<Episode> episodes = new ArrayList<>();
            for (EpisodeInput episodeInput : podcastInput.getEpisodes()) {
                episodes.add(new Episode(episodeInput.getName(),
                                         episodeInput.getDuration(),
                                         episodeInput.getDescription()));
            }
            podcasts.add(new Podcast(podcastInput.getName(), podcastInput.getOwner(), episodes));
        }
    }

    /**
     * Gets songs.
     *
     * @return the songs
     */
    public static List<Song> getSongs() {
        return new ArrayList<>(songs);
    }

    /**
     * Gets podcasts.
     *
     * @return the podcasts
     */
    public static List<Podcast> getPodcasts() {
        return new ArrayList<>(podcasts);
    }

    /**
     * Gets playlists.
     *
     * @return the playlists
     */
    public static List<Playlist> getPlaylists() {
        List<Playlist> playlists = new ArrayList<>();
        for (User user : users) {
            playlists.addAll(user.getPlaylists());
        }
        return playlists;
    }

    /**
     * Gets user.
     *
     * @param username the username
     * @return the user
     */
    public static User getUser(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Update timestamp.
     *
     * @param newTimestamp the new timestamp
     */
    public static void updateTimestamp(final int newTimestamp) {
        int elapsed = newTimestamp - timestamp;
        timestamp = newTimestamp;
        if (elapsed == 0) {
            return;
        }

        for (User user : users) {
            if (user.isOnline()) {
                user.simulateTime(elapsed);
            }
        }
    }

    /**
     * Gets top 5 songs.
     *
     * @return the top 5 songs
     */
    public static List<String> getTop5Songs() {
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getLikes).reversed());
        List<String> topSongs = new ArrayList<>();
        int count = 0;
        for (Song song : sortedSongs) {
            if (count >= LIMIT) {
                break;
            }
            topSongs.add(song.getName());
            count++;
        }
        return topSongs;
    }

    /**
     * Gets top 5 playlists.
     *
     * @return the top 5 playlists
     */
    public static List<String> getTop5Playlists() {
        List<Playlist> sortedPlaylists = new ArrayList<>(getPlaylists());
        sortedPlaylists.sort(Comparator.comparingInt(Playlist::getFollowers)
                .reversed()
                .thenComparing(Playlist::getTimestamp, Comparator.naturalOrder()));
        List<String> topPlaylists = new ArrayList<>();
        int count = 0;
        for (Playlist playlist : sortedPlaylists) {
            if (count >= LIMIT) {
                break;
            }
            topPlaylists.add(playlist.getName());
            count++;
        }
        return topPlaylists;
    }

    /**
     * Reset.
     */
    public static void reset() {
        users = new ArrayList<>();
        songs = new ArrayList<>();
        podcasts = new ArrayList<>();
        artists = new ArrayList<>();
        albums = new ArrayList<>();
        hosts = new ArrayList<>();
        timestamp = 0;
    }

    /**
     * Retrieves a list of online users.
     *
     * @return A list of usernames of online users.
     */
    public static List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                onlineUsers.add(user.getUsername());
            }
        }
        return onlineUsers;
    }

    /**
     * Checks if a given username exists in the system.
     *
     * @param username the username to be checked
     * @return true if the username exists, false otherwise
     */
    public static boolean checkUsername(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return true;
            }
        }
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a new user to the system based on the provided command input.
     *
     * @param command the command input containing the user details
     * @return a message indicating the success or failure of the user addition
     */
    public static String addUser(final CommandInput command) {
        if (checkUsername(command.getUsername())) {
            return "The username " + command.getUsername() + " is already taken.";
        }
        String type = command.getType();
        switch (type) {
            case "user" -> {
                User newUser = new User(command.getUsername(), command.getAge(), command.getCity());
                users.add(newUser);
                return "The username " + newUser.getUsername() + " has been added successfully.";
            }
            case "artist" -> {
                Artist newArtist = new Artist(command.getUsername(), command.getCity(),
                                              command.getAge());
                artists.add(newArtist);
                return "The username " + newArtist.getUsername() + " has been added successfully.";
            }
            case "host" -> {
                Host newHost = new Host(command.getUsername(), command.getAge(), command.getCity());
                hosts.add(newHost);
                return "The username " + newHost.getUsername() + " has been added successfully.";
            }
            default -> {
                return "Invalid type for adding a new user.";
            }
        }
    }

    /**
     * Retrieves an Artist object based on the provided username.
     *
     * @param username the username of the artist to retrieve
     * @return the Artist object with the matching username, or null if not found
     */
    public static Artist getArtist(final String username) {
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }

    /**
     * Adds a list of songs to the existing collection of songs.
     *
     * @param s the list of songs to be added
     */
    public static void addSongs(final ArrayList<Song> s) {
        songs.addAll(s);
    }

    /**
     * Returns a list of artists.
     *
     * @return the list of artists
     */
    public static List<Artist> getArtists() {
        return artists;
    }

    /**
     * Returns the list of albums.
     *
     * @return the list of albums
     */
    public static List<Album> getAlbums() {
        return albums;
    }

    /**
     * Retrieves a list of all usernames from the users, artists, and hosts collections.
     *
     * @return A list of all usernames.
     */
    public static List<String> getAllUsers() {
        List<String> allUsers = new ArrayList<>();
        for (User user : users) {
            allUsers.add(user.getUsername());
        }
        for (Artist artist : artists) {
            allUsers.add(artist.getUsername());
        }
        for (Host host : hosts) {
            allUsers.add(host.getUsername());
        }
        return allUsers;
    }

    /**
     * Retrieves the Host object associated with the given username.
     *
     * @param username the username of the Host to retrieve
     * @return the Host object if found, or null if not found
     */
    public static Host getHost(final String username) {
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return host;
            }
        }
        return null;
    }

    /**
     * Returns the list of hosts.
     *
     * @return the list of hosts
     */
    public static List<Host> getHosts() {
        return hosts;
    }

    private static boolean checkPodcastExists(final String podcastName, final String podcastOwner) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(podcastName) && podcast.getOwner().equals(podcastOwner)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a podcast based on the given command input.
     *
     * @param command the command input containing the podcast details
     * @return a string indicating the result of the operation
     */
    public static String addPodcast(final CommandInput command) {
        if (checkPodcastExists(command.getName(), command.getUsername())) {
            return command.getUsername() + " has another podcast with the same name.";
        }
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(command.getName())) {
                for (int i = 0; i < podcast.getEpisodes().size(); i++) {
                    String ep = command.getEpisodes().get(0).getName();
                    if (podcast.getEpisodes().get(i).getName().equals(ep)) {
                        return command.getUsername() + " has the same episode in this podcast.";
                    }
                }
            }
        }
        podcasts.add(new Podcast(command));
        Host host = getHost(command.getUsername());
        host.addPodcast(command);
        return command.getUsername() + " has added new podcast successfully.";
    }

    /**
     * Adds an album to the collection of albums.
     * Also adds the songs from the album to the collection of songs.
     *
     * @param album The album to be added.
     */
    public static void addAlbum(final Album album) {
        albums.add(album);
        ArrayList<Song> albumSongs = album.getSongs();
        addSongs(albumSongs);
    }

    /**
     * Removes the specified album from the collection of albums.
     * Also removes all the songs from the album.
     *
     * @param album the album to be removed
     */
    public static void removeAlbum(final Album album) {
        albums.remove(album);
        ArrayList<Song> albumSongs = album.getSongs();
        removeSongs(albumSongs);
    }

    /**
     * Removes the specified podcast from the list of podcasts.
     *
     * @param podcast the podcast to be removed
     */
    public static void removePodcast(final Podcast podcast) {
        podcasts.remove(podcast);
    }

    private static void removeSongs(final ArrayList<Song> s) {
        for (User u : users) {
            ArrayList<Song> likedSongs = u.getLikedSongs();
            likedSongs.removeAll(s);
        }
        Admin.songs.removeAll(s);
    }

    private static boolean checkDelete(final String name) {
        for (User u : users) {
            Player player = u.getPlayer();
            PlayerSource source = player.getSource();
            if (source == null) {
                continue;
            }
            if (source.getType() == Enums.PlayerSourceType.ALBUM) {
                Album album = (Album) source.getAudioCollection();
                if (album.getOwner().equals(name)) {
                    return false;
                }
            }
            if (source.getType() == Enums.PlayerSourceType.LIBRARY) {
                Song song = (Song) source.getAudioFile();
                if (song.getArtist().equals(name)) {
                    return false;
                }
            }
            if (source.getType() == Enums.PlayerSourceType.PLAYLIST) {
                Playlist playlist = (Playlist) source.getAudioCollection();
                if (playlist.getOwner().equals(name)) {
                    return false;
                }
            }
            if (source.getType() == Enums.PlayerSourceType.PODCAST) {
                Podcast podcast = (Podcast) source.getAudioCollection();
                if (podcast.getOwner().equals(name)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static String findUserType(final String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return "user";
            }
        }
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return "artist";
            }
        }
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return "host";
            }
        }
        return null;
    }

    /**
     * Deletes a user based on the given command input.
     *
     * @param command the command input containing the username of the user to be deleted
     * @return a message indicating the success or failure of the deletion operation
     */
    public static String deleteUser(final CommandInput command) {
        String name = command.getUsername();
        if (!checkUsername(name)) {
            return "The username " + command.getUsername() + " doesn't exist.";
        }
        if (!checkDelete(name)) {
            return command.getUsername() + " can't be deleted.";
        }
        String type = findUserType(command.getUsername());
        switch (type) {
            case "user" -> {
                User user = getUser(command.getUsername());
                ArrayList<Song> likedSongs = user.getLikedSongs();
                ArrayList<Playlist> followedPlaylists = user.getFollowedPlaylists();
                for (Song song : likedSongs) {
                    song.dislike();
                }
                for (Playlist playlist : followedPlaylists) {
                    playlist.decreaseFollowers();
                }
                users.remove(user);
                for (User u : users) {
                    ArrayList<Playlist> followed = u.getFollowedPlaylists();
                    followed.removeIf(playlist -> playlist.getOwner().equals(name));
                }
            }
            case "artist" -> {
                Artist artist = getArtist(name);
                ArrayList<Album> artistAlbums = artist.getAlbums();
                for (User u : users) {
                    if (u.getCurrentPage().getTitle().equals(artist.getUsername())) {
                        return name + " can't be deleted.";
                    }
                    ArrayList<Playlist> playlists = u.getPlaylists();
                    for (Playlist p : playlists) {
                        ArrayList<Song> songArrayList = p.getSongs();
                        for (Song song : songArrayList) {
                            if (song.getArtist().equals(artist.getUsername())) {
                                return name + " can't be deleted.";
                            }
                        }
                    }
                }
                if (artistAlbums != null) {
                    for (Album album : artistAlbums) {
                        removeAlbum(album);
                    }
                }
                artists.remove(artist);
            }
            case "host" -> {
                Host host = getHost(name);
                ArrayList<Podcast> hostPodcasts = host.getPodcasts();
                for (User u : users) {
                    if (u.getCurrentPage().getTitle().equals(host.getUsername())) {
                        return name + " can't be deleted.";
                    }
                }
                if (hostPodcasts != null) {
                    for (Podcast podcast : hostPodcasts) {
                        removePodcast(podcast);
                    }
                }
                hosts.remove(host);
            }
            default -> {
                return "Invalid type for deleting a user.";
            }
        }

        return name + " was successfully deleted.";
    }

    /**
     * Returns a list of the top 5 albums based on the number of likes and alphabetical order.
     *
     * @return a list of the top 5 albums
     */
    public static List<String> getTop5Albums() {
        List<Album> sortedAlbums = new ArrayList<>(albums);
        sortedAlbums.sort(Comparator.comparingInt(Album::getLikes)
                .reversed()
                .thenComparing(Album::getName, Comparator.naturalOrder()));
        List<String> topAlbums = new ArrayList<>();
        int count = 0;
        for (Album album : sortedAlbums) {
            if (count >= LIMIT) {
                break;
            }
            topAlbums.add(album.getName());
            count++;
        }
        return topAlbums;
    }

    /**
     * Retrieves the top 5 artists based on their number of likes and username.
     *
     * @return A list of the top 5 artists.
     */
    public static List<String> getTop5Artists() {
        List<Artist> sortedArtists = new ArrayList<>(artists);
        sortedArtists.sort(Comparator.comparingInt(Artist::getLikes)
                .reversed()
                .thenComparing(Artist::getUsername, Comparator.naturalOrder()));
        List<String> topArtists = new ArrayList<>();
        int count = 0;
        for (Artist artist : sortedArtists) {
            if (count >= LIMIT) {
                break;
            }
            topArtists.add(artist.getUsername());
            count++;
        }
        return topArtists;
    }
}
