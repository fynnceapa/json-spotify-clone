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

    //private HostPage hostPage = new HostPage();

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
        podcasts.add(new Podcast(command));
        return command.getUsername() + " has added new podcast successfully.";
    }

    private boolean checkAnnouncementExists(String announcementName) {
        for (Announcement announcement : announcements) {
            if (announcement.getName().equals(announcementName)) {
                return true;
            }
        }
        return false;
    }
    public String addAnnouncement(CommandInput command) {
        if (checkAnnouncementExists(command.getName())) {
            return command.getUsername() + " has already added an announcement with this name.";
        }
        announcements.add(new Announcement(command));
        return command.getUsername() + " has successfully added new announcement.";
    }

    public String removeAnnouncement(CommandInput command) {
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

    public Podcast getPodcast(String name) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(name)) {
                return podcast;
            }
        }
        return null;
    }

    public ArrayList<PodcastOut> getPodcastsOutput() {
        ArrayList<Podcast> podcasts = new ArrayList<>(this.podcasts);
        ArrayList<PodcastOut> podcastsOut = new ArrayList<>();
        for (Podcast podcast : podcasts) {
            podcastsOut.add(new PodcastOut(podcast));
        }
        return podcastsOut;
    }

    private boolean checkPodcastRemove(String name) {
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

    public boolean checkPodcastExists(String podcastName) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(podcastName)) {
                return true;
            }
        }
        return false;
    }

    public String removePodcast(String name) {
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

    public HostPage getHostPage() {
        return new HostPage(this);
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
