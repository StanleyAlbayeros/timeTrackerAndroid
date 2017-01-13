package reports;

import java.util.ArrayList;

public class Taula {

    private int nfiles;

    private int getNfiles() {
        return nfiles;
    }

    private void setNfiles(int nfiles) {
        this.nfiles = nfiles;
    }

    private int ncolumnes;

    private int getNcolumnes() {
        return ncolumnes;
    }

    private void setNcolumnes(int ncolumnes) {
        this.ncolumnes = ncolumnes;
    }

    private ArrayList<ArrayList<String>> taula = null;

    private ArrayList<ArrayList<String>> getTaula() {
        return taula;
    }

    private void setTaula(ArrayList<ArrayList<String>> taula) {
        this.taula = taula;
    }

    public Taula(int nfiles, int ncolumnes) {
        setNfiles(nfiles);
        setNcolumnes(ncolumnes);
        ArrayList<ArrayList<String>> t = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < nfiles; i++) {
            ArrayList<String> row = new ArrayList<String>();
            for (int j = 0; j < ncolumnes; j++) {
                // row.add(new String());
                row.add(null);
            }
            t.add(row);
        }
        setTaula(t);
    }

    public void addRow() {
        int ncolumnes = getNcolumnes();
        ArrayList<String> fila = new ArrayList<String>();
        for (int j = 0; j < ncolumnes; j++) {
            // fila.add(new String());
            fila.add(null);
        }
        getTaula().add(fila);
        setNfiles(getNfiles() + 1);
    }

    public void addRow(ArrayList<String> llistaStrings) {
        getTaula().add(llistaStrings);
        setNfiles(getNfiles() + 1);
    }

    public void setPosicio(int fila, int columna, String str) { // numerem de 1 ... n i no de 0 ... n-1
        (getTaula().get(fila - 1)).set(columna - 1, str);
    }

    public String getPosicio(int fila, int columna) {
        return (getTaula().get(fila - 1)).get(columna - 1);
    }

    public void imprimeix() {
        System.out.println(this.getTaula());
    }


}
