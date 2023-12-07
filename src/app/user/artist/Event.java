package app.user.artist;

import fileio.input.CommandInput;
import lombok.Getter;

@Getter
public class Event {
    private String name;
    private String date;
    private String description;

    public Event(CommandInput command) {
        this.name = command.getName();
        this.date = command.getDate();
        this.description = command.getDescription();
    }

    public boolean checkDate(String date) {
        String[] dateSplit = date.split("-");
        if (dateSplit.length != 3) {
            return false;
        }
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        if (month == 2 && day > 28) {
            return false;
        }
        if (day > 31) {
            return false;
        }
        if (month > 12) {
            return false;
        }
        if (year < 1900 || year > 20213) {
            return false;
        }
        return true;
    }
    @Override
    public String toString() {
        return this.name + " - " + this.date + ":\n\t" + this.description;
    }
}
