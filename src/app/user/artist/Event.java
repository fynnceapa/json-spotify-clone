package app.user.artist;

import fileio.input.CommandInput;
import lombok.Getter;

@Getter
public class Event {
    private String name;
    private String date;
    private String description;

    private static final int THREE = 3;
    private static final int TWO = 2;
    private static final int TWENTY_EIGHT = 28;
    private static final int THIRTY_ONE = 31;
    private static final int TWELVE = 12;
    private static final int NINETEEN_HUNDRED = 1900;
    private static final int TWENTY_TWENTY_THREE = 2023;


    public Event(final CommandInput command) {
        this.name = command.getName();
        this.date = command.getDate();
        this.description = command.getDescription();
    }

    /**
     * Checks if a given date is valid.
     *
     * @param d the date to be checked in the format "dd-mm-yyyy"
     * @return true if the date is valid, false otherwise
     */
    public boolean checkDate(final String d) {
        String[] dateSplit = d.split("-");
        if (dateSplit.length != THREE) {
            return false;
        }
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        if (month == TWO && day > TWENTY_EIGHT) {
            return false;
        }
        if (day > THIRTY_ONE) {
            return false;
        }
        if (month > TWELVE) {
            return false;
        }
        if (year < NINETEEN_HUNDRED || year > TWENTY_TWENTY_THREE) {
            return false;
        }
        return true;
    }

    /**
        * Returns a string representation of the Event object.
        *
        * @return a string containing the name, date, and description of the event.
        */
    @Override
    public String toString() {
        return this.name + " - " + this.date + ":\n\t" + this.description;
    }
}
