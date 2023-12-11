package app.page;

public interface Visitor {
    public String visit(HomePage homePage);
    public String visit(BasicPage basicPage);
    public String visit(ArtistPage artistPage);
    public String visit(HostPage hostPage);
    public String visit(LikedContent likedContent);
}
