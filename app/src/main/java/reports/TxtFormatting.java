package reports;

import reports.elements.Separator;
import reports.elements.Subtitle;
import reports.elements.Table;
import reports.elements.Text;
import reports.elements.Title;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class TxtFormatting implements Formatting {

    protected PrintWriter fileOut;

    protected Text reportContent = new Text();

    private boolean isCentered = true;

    /**
     * Class constructor. It also creates an output file.
     *
     * @param filename File's name.
     * @throws FileNotFoundException The file was not found or couldn't be created.
     */
    public TxtFormatting(String filename) throws FileNotFoundException {
        assert (reportFilenameCheck(filename)) : "Filename not valid";
        //fileOut = new PrintWriter(filename);
        isCentered = true;
    }

    private boolean reportFilenameCheck(String filename) {
        boolean pass = false;

        if (filename != null) {
            pass = true;
        }

        return pass;
    }

    @Override
    public void visit(Separator separator) {
        reportContent.addSeparationLine();
    }

    @Override
    public void visit(Subtitle subtitle) {
        reportContent.addSubtitle(subtitle.getContent(), isCentered);
    }

    @Override
    public void visit(Title title) {
        reportContent.addTitle(title.getContent(), isCentered);
    }

    @Override
    public void visit(Table table) {
        reportContent.addTable(table.getTable());
    }

    @Override
    public void visit(Text text) {
        // Empty method.
    }


    @Override
    public String closeReport() {
        String report = new String();
        report = reportContent.getContent().toString();
        return report;
    }

}
