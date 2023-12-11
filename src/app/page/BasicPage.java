package app.page;

import lombok.Getter;

@Getter
public class BasicPage implements Visitable {
    private final String title;
    public BasicPage(String title) {
        this.title = title;
    }

    @Override
    public String accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
