package app;

import app.audio.Collections.Album;
import app.audio.Collections.PlaylistOutput;
import app.audio.Collections.Podcast;
import app.audio.Files.Song;
import app.player.PlayerStats;
import app.searchBar.Filters;
import app.user.artist.Artist;
import app.user.User;
import app.user.host.Host;
import app.utils.AlbumOut;
import app.utils.PodcastOut;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.input.CommandInput;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Command runner.
 */
public final class CommandRunner {
    /**
     * The Object mapper.
     */
    private static ObjectMapper objectMapper = new ObjectMapper();

    private CommandRunner() {
    }

    /**
     * Search object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode search(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        Filters filters = new Filters(commandInput.getFilters());
        String type = commandInput.getType();

        if (!user.isOnline()) {
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", commandInput.getCommand());
            objectNode.put("user", commandInput.getUsername());
            objectNode.put("timestamp", commandInput.getTimestamp());
            objectNode.put("message", commandInput.getUsername() + " is offline.");
            ArrayList<String> results = new ArrayList<>();
            objectNode.put("results", objectMapper.valueToTree(results));
            return objectNode;
        }

        ArrayList<String> results = user.search(filters, type);
        String message = "Search returned " + results.size() + " results";

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);
        objectNode.put("results", objectMapper.valueToTree(results));

        return objectNode;
    }

    /**
     * Select object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode select(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());

        String message = user.select(commandInput.getItemNumber());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Load object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode load(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.load();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Play pause object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode playPause(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.playPause();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Repeat object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode repeat(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.repeat();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Shuffle object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode shuffle(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        Integer seed = commandInput.getSeed();
        String message = user.shuffle(seed);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Forward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode forward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.forward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Backward object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode backward(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.backward();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Like object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode like(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.like();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Next object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode next(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.next();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Prev object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode prev(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.prev();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Create playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode createPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.createPlaylist(commandInput.getPlaylistName(),
                                             commandInput.getTimestamp());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Add remove in playlist object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode addRemoveInPlaylist(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.addRemoveInPlaylist(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Switch visibility object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode switchVisibility(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.switchPlaylistVisibility(commandInput.getPlaylistId());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Show playlists object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showPlaylists(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<PlaylistOutput> playlists = user.showPlaylists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Follow object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode follow(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String message = user.follow();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Status object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode status(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        PlayerStats stats = user.getPlayerStats();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("stats", objectMapper.valueToTree(stats));

        return objectNode;
    }

    /**
     * Show liked songs object node.
     *
     * @param commandInput the command input
     * @return the object node
     */
    public static ObjectNode showLikedSongs(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        ArrayList<String> songs = user.showPreferredSongs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets preferred genre.
     *
     * @param commandInput the command input
     * @return the preferred genre
     */
    public static ObjectNode getPreferredGenre(final CommandInput commandInput) {
        User user = Admin.getUser(commandInput.getUsername());
        String preferredGenre = user.getPreferredGenre();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("user", commandInput.getUsername());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(preferredGenre));

        return objectNode;
    }

    /**
     * Gets top 5 songs.
     *
     * @param commandInput the command input
     * @return the top 5 songs
     */
    public static ObjectNode getTop5Songs(final CommandInput commandInput) {
        List<String> songs = Admin.getTop5Songs();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(songs));

        return objectNode;
    }

    /**
     * Gets top 5 playlists.
     *
     * @param commandInput the command input
     * @return the top 5 playlists
     */
    public static ObjectNode getTop5Playlists(final CommandInput commandInput) {
        List<String> playlists = Admin.getTop5Playlists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", commandInput.getCommand());
        objectNode.put("timestamp", commandInput.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(playlists));

        return objectNode;
    }

    /**
     * Switches the connection status of a user and returns an ObjectNode containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode switchConnectionStatus(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        User user = Admin.getUser(command.getUsername());
        String message;
        if (user == null) {
            message = command.getUsername() + " is not a normal user.";
        } else {
            user.switchOnline();
            message = command.getUsername() + " has changed status successfully.";
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
        * Creates an ObjectNode with the specified command, timestamp, and online users.
        *
        * @param command The command input.
        * @return The ObjectNode containing the command, timestamp, and online users.
        */
    public static ObjectNode getOnlineUsers(final CommandInput command) {
        List<String> onlineUsers = Admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }

    /**
        * Adds a user using the provided command input.
        *
        * @param command the command input containing user information
        * @return the JSON object node representing the result of the operation
        */
    public static ObjectNode addUser(final CommandInput command) {
        String message = Admin.addUser(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Adds an album to the artist's list of albums and returns an ObjectNode containing
     * the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode addAlbum(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Artist artist = Admin.getArtist(command.getUsername());
        if (artist == null) {
            String message = command.getUsername() + " is not an artist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        if (artist.checkAlbumExists(command.getName())) {
            String message = artist.getUsername() + " has another album with the same name.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        for (int i = 0; i < command.getSongs().size(); i++) {
            for (int j = i + 1; j < command.getSongs().size(); j++) {
                String song1 = command.getSongs().get(i).getName();
                String song2 = command.getSongs().get(j).getName();
                if (song1.equals(song2)) {
                    String message = artist.getUsername() + " has the same song at least"
                    + " twice in this album.";
                    ObjectNode objectNode = objectMapper.createObjectNode();
                    objectNode.put("command", command.getCommand());
                    objectNode.put("user", command.getUsername());
                    objectNode.put("timestamp", command.getTimestamp());
                    objectNode.put("message", message);
                    return objectNode;
                }
            }
        }
        String message = artist.addAlbum(command);
        Album album = artist.getAlbum(command.getName());
        Admin.addAlbum(album);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
        * Returns an ObjectNode containing information about the albums of an artist.
        *
        * @param command the CommandInput object containing the username, command, and timestamp
        * @return an ObjectNode containing the command, user, timestamp, and albums information
        */
    public static ObjectNode showAlbums(final CommandInput command) {
        Artist artist = Admin.getArtist(command.getUsername());

        ArrayList<Album> albums = artist.getAlbums();
        ArrayList<AlbumOut> albumsOut = new ArrayList<>();
        for (Album album : albums) {
            albumsOut.add(new AlbumOut(album));
        }

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albumsOut));

        return objectNode;
    }

    /**
        * Creates and returns an ObjectNode containing information about the current page.
        *
        * @param command the CommandInput object representing the command
        * @return the ObjectNode containing the command, user, timestamp, and message
        */
    public static ObjectNode printCurrentPage(final CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        String message = user.printCurrentPage();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Adds an event to the artist's list of events and returns an ObjectNode containing
     * the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode addEvent(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Artist artist = Admin.getArtist(command.getUsername());
        if (artist == null) {
            String message = command.getUsername() + " is not an artist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = artist.addEvent(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Add merche to artist's list of merch and returns an ObjectNode containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode addMerch(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Artist artist = Admin.getArtist(command.getUsername());
        if (artist == null) {
            String message = command.getUsername() + " is not an artist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = artist.addMerch(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
        * Retrieves all users and returns them as an ObjectNode.
        *
        * @param command the CommandInput object containing the command and timestamp
        * @return an ObjectNode containing the command, timestamp, and the list of users
        */
    public static ObjectNode getAllUsers(final CommandInput command) {
        List<String> users = Admin.getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(users));

        return objectNode;
    }

    /**
     * Adds a podcast to the host's list of podcasts and returns an ObjectNode containing
     * the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode addPodcast(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Host host = Admin.getHost(command.getUsername());
        if (host == null) {
            String message = command.getUsername() + " is not a host.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = Admin.addPodcast(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Adds an announcement to the host's list of announcements and returns an ObjectNode
     * containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode addAnnouncement(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Host host = Admin.getHost(command.getUsername());
        if (host == null) {
            String message = command.getUsername() + " is not a host.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = host.addAnnouncement(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Removes an announcement from the host's list of announcements and returns an ObjectNode
     * containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode removeAnnouncement(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Host host = Admin.getHost(command.getUsername());
        if (host == null) {
            String message = command.getUsername() + " is not a host.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = host.removeAnnouncement(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
     * Shows the podcasts of a host and returns an ObjectNode containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and result.
     */
    public static ObjectNode showPodcasts(final CommandInput command) {
        Host host = Admin.getHost(command.getUsername());

        ArrayList<PodcastOut> podcasts = host.getPodcastsOutput();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(podcasts));

        return objectNode;
    }

    /**
     * Removes an album from the artist's list of albums and returns an ObjectNode
     * containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode removeAlbum(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Artist artist = Admin.getArtist(command.getUsername());
        if (artist == null) {
            String message = command.getUsername() + " is not an artist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = artist.removeAlbum(command.getName());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
        * Creates and returns an ObjectNode containing information about a page change command.
        *
        * @param command the CommandInput object representing the page change command
        * @return the ObjectNode containing the command, user, timestamp, and message
        */
    public static ObjectNode changePage(final CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        String message = user.changePage(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Deletes user and returns an ObjectNode containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode deleteUser(final CommandInput command) {
        String message = Admin.deleteUser(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Removes podcast from host's list of podcasts and returns an ObjectNode containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode removePodcast(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Host host = Admin.getHost(command.getUsername());
        if (host == null) {
            String message = command.getUsername() + " is not a host.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("user", command.getUsername());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }

        String message = host.removePodcast(command.getName());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    /**
     * Removes event from artist's list of events and returns an ObjectNode containing the result.
     *
     * @param command The command input containing the username, command, and timestamp.
     * @return An ObjectNode containing the command, user, timestamp, and message.
     */
    public static ObjectNode removeEvent(final CommandInput command) {
        if (!Admin.checkUsername(command.getUsername())) {
            String message = "The username " + command.getUsername() + " doesn't exist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        Artist artist = Admin.getArtist(command.getUsername());
        if (artist == null) {
            String message = command.getUsername() + " is not an artist.";
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("command", command.getCommand());
            objectNode.put("timestamp", command.getTimestamp());
            objectNode.put("message", message);
            return objectNode;
        }
        String message = artist.removeEvent(command.getName());

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;
    }

    /**
        * Retrieves the top 5 albums and creates an ObjectNode containing
        * the command, timestamp, and result.
        *
        * @param command The CommandInput object representing the command.
        * @return The ObjectNode containing the command, timestamp, and result.
        */
    public static ObjectNode getTop5Albums(final CommandInput command) {
        List<String> albums = Admin.getTop5Albums();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(albums));

        return objectNode;
    }

    /**
        * Returns an ObjectNode containing information about the top 5 artists.
        *
        * @param command the CommandInput object representing the command
        * @return an ObjectNode containing the command, timestamp, and result
        */
    public static ObjectNode getTop5Artists(final CommandInput command) {
        List<String> artists = Admin.getTop5Artists();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(artists));

        return objectNode;
    }
}
