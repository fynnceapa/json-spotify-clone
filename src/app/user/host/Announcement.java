package app.user.host;

import fileio.input.CommandInput;
import lombok.Getter;

@Getter
public class Announcement {
    private String name;
    private String description;
    public Announcement() {
    }
    public Announcement(final String name, final String description) {
        this.name = name;
        this.description = description;
    }
    public Announcement(final CommandInput cmd) {
        this.name = cmd.getName();
        this.description = cmd.getDescription();
    }

    @Override
    public String toString(){
    	return this.name + ":\n\t" + this.description + "\n";
    }
}
