package reports.elements;

import reports.Formatting;

public class Separator implements ElementInterface {

    @Override
    public void accept(Formatting formatting) {
        assert (formatting != null);
        formatting.visit(this);
    }
}
