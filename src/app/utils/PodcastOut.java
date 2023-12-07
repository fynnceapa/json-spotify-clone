package app.utils;

import app.audio.Collections.Podcast;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class PodcastOut {
    private String name;
    private ArrayList<String> episodes;

    public PodcastOut(Podcast podcast) {
        this.name = podcast.getName();
        ArrayList<String> episodes = new ArrayList<>();
        for (int i = 0; i < podcast.getEpisodes().size(); i++) {
            episodes.add(podcast.getEpisodes().get(i).getName());
        }
        this.episodes = episodes;
    }
}
