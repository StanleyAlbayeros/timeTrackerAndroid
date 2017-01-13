package reports.elements;

import reports.Formatting;

public class Title implements ElementInterface {

    private String content = "";

    public Title(String content) {
        assert (content != null);
        this.content = content;
    }

    public final String getContent() {
        return content;
    }

    public final void setContent(String content) {
        this.content = content;
    }

    @Override
    public void accept(Formatting formatting) {
        formatting.visit(this);
    }

}
