package reports.elements;

import reports.Formatting;

import java.util.ArrayList;


public class Table implements ElementInterface {

    private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();

    public Table() {
    }

    public ArrayList<ArrayList<String>> getTable() {
        return table;
    }

    public Table(ArrayList<ArrayList<String>> table) {
        assert (table != null);
        this.table = table;
    }

    public void setTable(ArrayList<ArrayList<String>> table) {
        assert (table != null);
        this.table = table;
    }


    public void addTableRow(ArrayList<String> row) {
        assert (row != null);
        table.add(row);
    }

    @Override
    public void accept(Formatting formatting) {
        assert (formatting != null);
        formatting.visit(this);
    }

}
