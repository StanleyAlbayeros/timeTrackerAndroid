package reports;

import nucli.Project;
import reports.elements.ElementInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public abstract class Report {

    protected Project project;
    protected ArrayList<ElementInterface> reportElements;

    /**
     * Constructor for the report class.
     *
     * @param project Project used to generate the report.
     */
    public Report(Project project) {
        assert (project != null);
        this.project = project;
        reportElements = new ArrayList<ElementInterface>();

    }


    /**
     * Accepts the visitor's methods and writes the report to the chosen filetype.
     *
     * @param formatting desired filetype.
     */
    public String writeReport(Formatting formatting) {
        for (ElementInterface currentElement : reportElements) {
            currentElement.accept(formatting);
        }
        return formatting.closeReport();
    }
}
