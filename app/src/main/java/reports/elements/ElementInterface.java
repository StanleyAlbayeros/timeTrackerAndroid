package reports.elements;

import reports.Formatting;

/**
 * Visitable interface.
 *
 * @author Vernon
 */
public interface ElementInterface {

    public abstract void accept(Formatting formatting);

}
