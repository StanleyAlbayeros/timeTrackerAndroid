package reports;

import reports.elements.Separator;
import reports.elements.Subtitle;
import reports.elements.Table;
import reports.elements.Text;
import reports.elements.Title;

/**
 * Visitor interface.
 *
 * @author Vernon
 */
public interface Formatting {

    String closeReport();

    void visit(Separator separator);

    void visit(Subtitle subtitle);

    void visit(Title title);

    void visit(Table table);

    void visit(Text text);

}
