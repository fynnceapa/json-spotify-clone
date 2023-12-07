package app;

import app.audio.Collections.Album;
import app.audio.Collections.Playlist;
import app.audio.Collections.Podcast;
import app.audio.Files.Episode;
import app.audio.Files.Song;
import app.player.Player;
import app.user.artist.Artist;
import app.user.User;
import app.user.host.Host;
import fileio.input.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The type Admin.
 */
public final class Admin {
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

    public static List<String> getOnlineUsers() {
        List<String> onlineUsers = new ArrayList<>();
        for (User user : users) {
            if (user.isOnline()) {
                onlineUsers.add(user.getUsername());
            }
        }
        return onlineUsers;
    }

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

    public static String addUser(CommandInput command) {
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

    public static Artist getArtist(final String username) {
        for (Artist artist : artists) {
            if (artist.getUsername().equals(username)) {
                return artist;
            }
        }
        return null;
    }
    public static void addSongs(ArrayList<Song> s) {
        songs.addAll(s);
    }

    public static List<Artist> getArtists() {
        return artists;
    }
    public static List<Album> getAlbums() {
        return albums;
    }

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

    public static Host getHost(String username) {
        for (Host host : hosts) {
            if (host.getUsername().equals(username)) {
                return host;
            }
        }
        return null;
    }

    public static List<Host> getHosts() {
        return hosts;
    }

    private static boolean checkPodcastExists(String podcastName, String podcastOwner) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(podcastName) && podcast.getOwner().equals(podcastOwner)) {
                return true;
            }
        }
        return false;
    }

    public static String addPodcast(CommandInput command) {
        if (checkPodcastExists(command.getName(), command.getUsername())) {
            return command.getUsername() + " has another podcast with the same name.";
        }
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(command.getName())) {
                for (int i = 0; i < podcast.getEpisodes().size(); i++) {
                    if (podcast.getEpisodes().get(i).getName().equals(command.getEpisodes().get(0).getName())) {
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

    public static void addAlbum(Album album) {
        albums.add(album);
        ArrayList<Song> songs = album.getSongs();
        addSongs(songs);
    }
}
