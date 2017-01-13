package reports;

import android.annotation.SuppressLint;

import reports.PaginaWeb;
import reports.elements.Separator;
import reports.elements.Subtitle;
import reports.elements.Table;
import reports.elements.Text;
import reports.elements.Title;

import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class HtmlFormatting implements Formatting {

    private PrintWriter fileOut;
    private PaginaWeb reportContent;


    /**
     * Generates an html formatting object with specialized visitor methods to import a simple text
     * layout file to html.
     * <p>
     * param filename File's name.
     * throws FileNotFoundException The file could not be found or could not be created
     */
    @SuppressLint("Assert")
    public HtmlFormatting(String filename) throws FileNotFoundException {
        assert (reportFilenameCheck(filename)) : "Filename not valid";
        //fileOut = new PrintWriter(filename);
        reportContent = new PaginaWeb();
    }

    private boolean reportFilenameCheck(String filename) {
        boolean validFilename = false;
        if (filename != null) {
            validFilename = true;
        }
        return validFilename;
    }

    @SuppressLint("Assert")
    @Override
    public void visit(Separator separator) {
        assert (separator != null) : "Separator is null";
        reportContent.afegeixLiniaSeparacio();
    }

    @SuppressLint("Assert")
    @Override
    public void visit(Subtitle subtitle) {
        assert (subtitle != null) : "Subtitle is null";
        reportContent.afegeixHeader(subtitle.getContent(), 2, false);
    }

    @SuppressLint("Assert")
    @Override
    public void visit(Title title) {
        assert (title != null) : "Title is null";
        reportContent.afegeixHeader(title.getContent(), 1, true);
    }

    @SuppressLint("Assert")
    @Override
    public void visit(Table table) {
        assert (table != null) : "Table is null";
        reportContent.afegeixTaula(table.getTable(), true, true);
    }

    @SuppressLint("Assert")
    @Override
    public void visit(Text text) {
        assert (text != null) : "Text is null";
        reportContent.afegeixTextNormal(text.getContent());
    }

    @Override
    public String closeReport() {
        //reportContent.escriuPagina();
        String reportPlainText;
        reportPlainText = ((PaginaWeb) reportContent).pageToString();

        System.out.println(reportPlainText);
        return reportPlainText;
    }


}
