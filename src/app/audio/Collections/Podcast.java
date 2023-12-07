package app.audio.Collections;

import app.audio.Files.AudioFile;
import app.audio.Files.Episode;
import fileio.input.CommandInput;
import fileio.input.EpisodeInput;

import java.util.ArrayList;
import java.util.List;

public final class Podcast extends AudioCollection {
    private final List<Episode> episodes;

    public Podcast(final String name, final String owner, final List<Episode> episodes) {
        super(name, owner);
        this.episodes = episodes;
    }

    public Podcast(final CommandInput command) {
        super(command.getName(), command.getUsername());
        episodes = new ArrayList<>();
        for (EpisodeInput e : command.getEpisodes()) {
            episodes.add(new Episode(e.getName(), e.getDuration(), e.getDescription()));
        }
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    @Override
    public int getNumberOfTracks() {
        return episodes.size();
    }

    @Override
    public AudioFile getTrackByIndex(final int index) {
        return episodes.get(index);
    }

    @Override
    public String toString() {
        ArrayList<String> episodes = new ArrayList<>();
        for (Episode episode : this.episodes) {
            episodes.add(episode.toString());
        }
        return super.getName() + ":\n\t" + episodes + "\n";
    }

}
