package app.page;

import app.Admin;
import app.audio.Collections.Podcast;
import app.user.host.Announcement;
import fileio.input.CommandInput;

import java.util.ArrayList;

public class HostPage extends BasicPage {
    ArrayList<Podcast> podcasts;
    ArrayList<Announcement> announcements;

    public HostPage() {
        this.podcasts = new ArrayList<>();
        this.announcements = new ArrayList<>();
    }

    private boolean checkPodcastExists(String podcastName) {
        for (Podcast podcast : podcasts) {
            if (podcast.getName().equals(podcastName)) {
                return true;
            }
        }
        return false;
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

    @Override
    public String toString() {
        ArrayList<String> podcasts = new ArrayList<>();
        for (Podcast podcast : this.podcasts) {
            podcasts.add(podcast.toString());
        }
        ArrayList<String> announcements = new ArrayList<>();
        for (Announcement announcement : this.announcements) {
            announcements.add(announcement.toString());
        }
        return "Podcasts:\n\t" + podcasts + "\n\nAnnouncements:\n\t" + announcements;
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

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }
}
