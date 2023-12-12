package app.page;

public interface Visitor {
    /**
     * Visits a HomePage and returns a String.
     *
     * @param homePage the HomePage to visit
     * @return a String representing the result of the visit
     */
    String visit(HomePage homePage);
    /**
     * Visits a BasicPage and returns a String.
     *
     * @param basicPage the BasicPage to visit
     * @return the result of visiting the BasicPage as a String
     */
    String visit(BasicPage basicPage);
    /**
     * Visits an ArtistPage and returns a String.
     *
     * @param artistPage the ArtistPage to visit
     * @return a String representing the visit result
     */
    String visit(ArtistPage artistPage);
    /**
     * Visits a HostPage and returns a String.
     *
     * @param hostPage the HostPage to visit
     * @return the result as a String
     */
    String visit(HostPage hostPage);
    /**
     * Visits a LikedContent and returns a String representation.
     *
     * @param likedContent the LikedContent to visit
     * @return a String representation of the visited LikedContent
     */
    String visit(LikedContent likedContent);
}
