package app.page;

public class PageVisitor implements Visitor {

    @Override
    public String visit(BasicPage basicPage) {
        return null;
    }

    @Override
    public String visit(ArtistPage artistPage) {
        return artistPage.toString();
    }

    @Override
    public String visit(HostPage hostPage) {
        return hostPage.toString();
    }

    @Override
    public String visit(LikedContent likedContent) {
        return likedContent.toString();
    }

    @Override
    public String visit(HomePage homePage) {
        return homePage.toString();
    }
}
