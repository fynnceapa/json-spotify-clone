package app.page;

import app.audio.Collections.Podcast;
import app.user.host.Announcement;
import app.user.host.Host;

import java.util.ArrayList;

public class HostPage extends BasicPage implements Visitable{
    ArrayList<Podcast> podcasts;
    ArrayList<Announcement> announcements;
    public HostPage(Host host) {
        super(host.getName());
        this.podcasts = host.getPodcasts();
        this.announcements = host.getAnnouncements();
    }

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

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
