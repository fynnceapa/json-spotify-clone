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

    private HostPage hostPage = new HostPage();
    public Host(final String username, final Integer age, final String city) {
        super(username);
        this.username = username;
        this.city = city;
        this.age = age;
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

    public String addPodcast(CommandInput command) {
        return hostPage.addPodcast(command);
    }

    public String addAnnouncement(CommandInput command) {
        return hostPage.addAnnouncement(command);
    }

    public String removeAnnouncement(CommandInput command) {
        return hostPage.removeAnnouncement(command);
    }

    public ArrayList<PodcastOut> getPodcastsOutput() {
        ArrayList<Podcast> podcasts = hostPage.getPodcasts();
        ArrayList<PodcastOut> podcastsOut = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastsOut.add(new PodcastOut(podcast));
        }
        return podcastsOut;
    }

    public ArrayList<Podcast> getPodcasts() {
        return hostPage.getPodcasts();
    }

    private boolean checkPodcastRemove(String name) {
        Podcast podcast = hostPage.getPodcast(name);
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

    public String removePodcast(String name) {
        HostPage Page = hostPage;
        if (!Page.checkPodcastExists(name)) {
            return username + " doesn't have a podcast with the given name.";
        }
        if (!checkPodcastRemove(name)) {
            return username + " can't delete this podcast.";
        }
        Podcast podcast = Page.getPodcast(name);
        Page.removePodcast(name);
        Admin.removePodcast(podcast);
        return username + " deleted the podcast successfully.";
    }

//    public boolean checkPodcastExists(String podcastName) {
//        return hostPage.checkPodcastExists(podcastName);
//    }
//
//    public String addPodcast(String podcastName, String podcastDescription) {
//        HostPage hostPage = this.hostPage;
//        hostPage.addPodcast(podcastName, podcastDescription);
//        return username + " has added new podcast successfully.";
//    }
//
//    public Podcast getPodcast(String podcastName) {
//        return hostPage.getPodcast(podcastName);
//    }
}
