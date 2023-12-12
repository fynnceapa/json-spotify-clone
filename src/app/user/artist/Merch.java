package app.user.artist;

import fileio.input.CommandInput;
import lombok.Getter;

@Getter
public class Merch {
    private String name;
    private String description;
    private Integer price;

    public Merch() {
    }

    public Merch(final String name, final String description, final Integer price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Merch(final CommandInput command) {
        this.name = command.getName();
        this.description = command.getDescription();
        this.price = command.getPrice();
    }

    /**
     * Returns a string representation of the Merch object.
     * The string representation includes the name, price, and description of the merch.
     *
     * @return a string representation of the Merch object
     */
    @Override
    public String toString() {
        return this.name + " - " + this.price + ":\n\t" + this.description;
    }
}
