package app.user.host;

import app.Admin;
import app.audio.Collections.Podcast;
import app.audio.LibraryEntry;
import app.page.HostPage;
import app.player.Player;
import app.player.PlayerSource;
import app.user.User;
import app.utils.PodcastOut;
import fileio.input.CommandInput;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class Host extends LibraryEntry {
    private String username;
    private String city;
    private Integer age;

    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;

    public Host(final String username, final Integer age, final String city) {
        super(username);
        this.username = username;
        this.city = city;
        this.age = age;
        this.podcasts = new ArrayList<>();
        this.announcements = new ArrayList<>();
    }

    /**
     * Sets the username for the host.
     *
     * @param username the new username for the host
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Sets the city of the host.
     *
     * @param city the city to set
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * Sets the age of the host.
     *
     * @param age the age of the host
     */
    public void setAge(final Integer age) {
        this.age = age;
    }

    /**
     * Adds a new podcast to the list of podcasts.
     *
     * @param command the command input containing the details of the podcast
     * @return a message indicating the successful addition of the podcast
     */
    public String addPodcast(final CommandInput command) {
        podcasts.add(new Podcast(command));
        return command.getUsername() + " has added new podcast successfully.";
    }

    private boolean checkAnnouncementExists(final String announcementName) {
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(announcementName)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Adds an announcement to the list of announcements.
     *
     * @param command the command input containing the details of the announcement
     * @return a message indicating the success or failure of adding the announcement
     */
    public String addAnnouncement(final CommandInput command) {
        if (checkAnnouncementExists(command.getName())) {
            return command.getUsername() + " has already added an announcement with this name.";
        }
        announcements.add(new Announcement(command));
        return command.getUsername() + " has successfully added new announcement.";
    }

    /**
     * Removes an announcement with the given name from the host's list of announcements.
     *
     * @param command the command input containing the name of the announcement to be removed
     * @return a string indicating the result of the removal operation
     */
    public String removeAnnouncement(final CommandInput command) {
        if (!checkAnnouncementExists(command.getName())) {
            return command.getUsername() + " has no announcement with the given name.";
        }
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(command.getName())) {
                announcements.remove(announcement);
                return command.getUsername() + " has successfully deleted the announcement.";
            }
        }
        return null;
    }

    /**
     * Retrieves a podcast with the specified name.
     *
     * @param name the name of the podcast to retrieve
     * @return the podcast with the specified name, or null if not found
     */
    public Podcast getPodcast(final String name) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                return podcast;
            }
        }
        return null;
    }

    /**
     * Retrieves the list of podcasts as PodcastOut objects.
     *
     * @return The list of PodcastOut objects representing the podcasts.
     */
    public ArrayList<PodcastOut> getPodcastsOutput() {
        ArrayList<PodcastOut> podcastsOut = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastsOut.add(new PodcastOut(podcast));
        }
        return podcastsOut;
    }

    private boolean checkPodcastRemove(final String name) {
        Podcast podcast = getPodcast(name);
        ArrayList<User> users = (ArrayList<User>) Admin.getUsers();
        for (User u : users) {
            Player player = u.getPlayer();
            if (player.getSource() == null || !player.getType().equals("podcast")) {
                continue;
            }
            PlayerSource source = player.getSource();
            if (source.getAudioCollection().getName().equals(podcast.getName())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks if a podcast with the given name exists.
     *
     * @param podcastName the name of the podcast to check
     * @return true if a podcast with the given name exists, false otherwise
     */
    public boolean checkPodcastExists(final String podcastName) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(podcastName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Removes a podcast with the given name from the user's collection.
     *
     * @param name the name of the podcast to be removed
     * @return a message indicating the result of the removal operation
     */
    public String removePodcast(final String name) {
        if (!checkPodcastExists(name)) {
            return username + " doesn't have a podcast with the given name.";
        }
        if (!checkPodcastRemove(name)) {
            return username + " can't delete this podcast.";
        }
        Podcast podcast = getPodcast(name);
        for (Podcast p : podcasts) {
            if (p.getName().equals(name)) {
                podcasts.remove(p);
                break;
            }
        }
        Admin.removePodcast(podcast);
        return username + " deleted the podcast successfully.";
    }

    /**
     * Returns a new instance of the HostPage class.
     *
     * @return a new instance of the HostPage class
     */
    public HostPage getHostPage() {
        return new HostPage(this);
    }

}
