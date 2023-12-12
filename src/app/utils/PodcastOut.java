package app.utils;

import app.audio.Collections.Podcast;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PodcastOut {
    private String name;
    private ArrayList<String> episodes;

    public PodcastOut(final Podcast podcast) {
        this.name = podcast.getName();
        ArrayList<String> epNames = new ArrayList<>();
        for (int i = 0; i < podcast.getEpisodes().size(); i++) {
            epNames.add(podcast.getEpisodes().get(i).getName());
        }
        this.episodes = epNames;
    }
}
