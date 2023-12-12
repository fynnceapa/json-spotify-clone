package app.page;

import lombok.Getter;

@Getter
public class BasicPage implements Visitable {
    private final String title;
    public BasicPage(final String title) {
        this.title = title;
    }

    /**
     * Accepts a visitor and returns the result of visiting this BasicPage.
     *
     * @param visitor the visitor to accept
     * @return the result of visiting this BasicPage
     */
    @Override
    public String accept(final Visitor visitor) {
        return visitor.visit(this);
    }
}
