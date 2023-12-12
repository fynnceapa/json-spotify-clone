package app.page;

public interface Visitable {
    /**
     * Accepts a visitor and returns a string representation of the visit result.
     *
     * @param visitor the visitor to accept
     * @return a string representation of the visit result
     */
    String accept(Visitor visitor);
}
