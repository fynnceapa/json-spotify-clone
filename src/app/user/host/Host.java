package app.user.host;

import app.audio.Collections.Podcast;
import app.audio.LibraryEntry;
import app.page.HostPage;
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
