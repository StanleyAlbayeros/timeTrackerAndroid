package reports.elements;

import reports.Formatting;

import java.util.ArrayList;


public class Text implements ElementInterface {

    private String content = "";
    private String separate = "+";
    private String horizontalLine = "----------------------------------------";
    private String verticalLine = "|";

    public Text() {
        content = " ";
    }

    public String getContent() {
        return content;
    }

    public void addContent(String newText) {
        assert (newText != null);
        content = content + newText;
    }

    public void addLineBreak() {
        String lineBreak = System.getProperty("line.separator");
        content = content + lineBreak;
    }

    /**
     * Adds a + sign lign to separate table elements.
     */
    public void addSeparationLine() {
        addLineBreak();
        for (int i = 0; i <= 80; i++) {
            content = content + separate;
        }
        addLineBreak();
    }

    /**
     * Adds "num" whitespaces after the current element, places a new string,
     * and places "num" whitespaces after the new string.
     *
     * @param newText new text.
     * @param num     number of whitespaces.
     */
    public void centerElement(String newText, int num) {
        assert (newText != null);
        for (int i = 0; i < num; i++) {
            content = content + " ";
        }
        content = content + newText;
        for (int i = 0; i < num; i++) {
            content = content + " ";
        }
    }

    /**
     * Adds a table title.
     *
     * @param newText  title.
     * @param centered center the title within the cell.
     */
    public void addTitle(String newText, boolean centered) {
        assert (newText != null);
        if (centered) {
            centerElement(newText, 30);
        } else {
            content = content + newText;
        }
        addSeparationLine();
    }

    /**
     * Adds a table subtitle.
     *
     * @param newText  subtitle.
     * @param centered center the subtitle within the cell.
     */
    public void addSubtitle(String newText, boolean centered) {
        assert (newText != null);
        if (centered) {
            centerElement(newText, 30);
        } else {
            content = content + newText;
        }
        addLineBreak();
    }

    /**
     * Adds a table.
     *
     * @param newTable the new table.
     */
    public void addTable(ArrayList<ArrayList<String>> newTable) {
        assert (newTable != null);

        int tableColumns = newTable.get(0).size();
        int tableRows = newTable.size();

        for (ArrayList<String> currentRow : newTable) {
            for (String currentColumm : currentRow) {
                content = content + horizontalLine;
            }
            addLineBreak();
            content = content + verticalLine;
            for (String currentColumm2 : currentRow) {
                centerElement(currentColumm2, ((40 - currentColumm2.length()) / 2));
            }
            content = content + verticalLine;
            addLineBreak();
        }
        for (int j = 0; j < tableColumns; j++) {
            content = content + horizontalLine;
        }
        addLineBreak();
    }

    @Override
    public void accept(Formatting formatting) {
        assert (formatting != null);
        formatting.visit(this);

    }

}
