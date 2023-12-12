package app.page;

public class PageVisitor implements Visitor {

    /**
     * Visits a BasicPage object and returns a String.
     *
     * @param basicPage the BasicPage object to visit
     * @return the result of visiting the BasicPage object as a String
     */
    @Override
    public String visit(final BasicPage basicPage) {
        return null;
    }

    /**
        * Visits an ArtistPage and returns its string representation.
        *
        * @param artistPage the ArtistPage to visit
        * @return the string representation of the ArtistPage
        */
    @Override
    public String visit(final ArtistPage artistPage) {
        return artistPage.toString();
    }

    /**
        * Visits a HostPage and returns its string representation.
        *
        * @param hostPage the HostPage to visit
        * @return the string representation of the HostPage
        */
    @Override
    public String visit(final HostPage hostPage) {
        return hostPage.toString();
    }

    /**
        * Visits a LikedContent object and returns its string representation.
        *
        * @param likedContent the LikedContent object to visit
        * @return the string representation of the LikedContent object
        */
    @Override
    public String visit(final LikedContent likedContent) {
        return likedContent.toString();
    }

    /**
        * Visits a HomePage object and returns its string representation.
        *
        * @param homePage the HomePage object to visit
        * @return the string representation of the HomePage object
        */
    @Override
    public String visit(final HomePage homePage) {
        return homePage.toString();
    }
}
