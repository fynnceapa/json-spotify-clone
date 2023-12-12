package app.page;

import app.audio.Collections.Podcast;
import app.user.host.Announcement;
import app.user.host.Host;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class HostPage extends BasicPage implements Visitable {
    private ArrayList<Podcast> podcasts;
    private ArrayList<Announcement> announcements;
    public HostPage(final Host host) {
        super(host.getName());
        this.podcasts = host.getPodcasts();
        this.announcements = host.getAnnouncements();
    }

    /**
     * Returns a string representation of the HostPage object.
     * The string includes the names of all the podcasts and announcements in the HostPage.
     *
     * @return a string representation of the HostPage object
     */
    @Override
    public String toString() {
        ArrayList<String> podcastsNames = new ArrayList<>();
        for (Podcast p : this.podcasts) {
            podcastsNames.add(p.toString());
        }
        ArrayList<String> announcementsNames = new ArrayList<>();
        for (Announcement a : this.announcements) {
            announcementsNames.add(a.toString());
        }
        return "Podcasts:\n\t" + podcastsNames + "\n\nAnnouncements:\n\t" + announcementsNames;
    }

    /**
     * Accepts a visitor and returns the result of visiting this HostPage.
     *
     * @param visitor the visitor to accept
     * @return the result of visiting this HostPage
     */
    @Override
    public String accept(final Visitor visitor) {
        return visitor.visit(this);
    }
}
