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

    public static ObjectNode switchConnectionStatus(CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        String message;
        if (user == null) {
            message = "The username " + command.getUsername() + " doesn't exist.";
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

    public static ObjectNode getOnlineUsers(CommandInput command) {
        List<String> onlineUsers = Admin.getOnlineUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(onlineUsers));

        return objectNode;
    }

    public static ObjectNode addUser(CommandInput command) {
        String message = Admin.addUser(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode addAlbum(CommandInput command) {
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
                if (command.getSongs().get(i).getName().equals(command.getSongs().get(j).getName())) {
                    String message = artist.getUsername() + " has the same song at least twice in this album.";
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

    public static ObjectNode showAlbums(CommandInput command) {
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

    public static ObjectNode printCurrentPage(CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        String message = user.printCurrentPage();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode addEvent(CommandInput command) {
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

    public static ObjectNode addMerch(CommandInput command) {
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

    public static ObjectNode getAllUsers(CommandInput command) {
        List<String> users = Admin.getAllUsers();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(users));

        return objectNode;
    }

    public static ObjectNode addPodcast(CommandInput command) {
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

    public static ObjectNode addAnnouncement(CommandInput command) {
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

    public static ObjectNode removeAnnouncement(CommandInput command) {
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

    public static ObjectNode showPodcasts(CommandInput command) {
        Host host = Admin.getHost(command.getUsername());

        ArrayList<PodcastOut> podcasts = host.getPodcastsOutput();

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("result", objectMapper.valueToTree(podcasts));

        return objectNode;
    }

    public static ObjectNode removeAlbum(CommandInput command) {
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

    public static ObjectNode changePage(CommandInput command) {
        User user = Admin.getUser(command.getUsername());
        String message = user.changePage(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode deleteUser(CommandInput command) {
        String message = Admin.deleteUser(command);

        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("command", command.getCommand());
        objectNode.put("user", command.getUsername());
        objectNode.put("timestamp", command.getTimestamp());
        objectNode.put("message", message);

        return objectNode;
    }

    public static ObjectNode removePodcast(CommandInput command) {
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

    public static ObjectNode removeEvent(CommandInput command) {
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
        objectNode.put("timestamp",command.getTimestamp());
        objectNode.put("message", message);
        return objectNode;

    }
}
