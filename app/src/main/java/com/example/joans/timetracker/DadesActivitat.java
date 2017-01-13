package com.example.joans.timetracker;

import java.io.Serializable;
import java.util.Date;

import nucli.Activity;
import nucli.BasicTask;
import nucli.Project;
import nucli.Task;


/**
 * Conté les dades d'una activitat (projecte o tasca) que poden ser mostrades
 * per la interfase d'usuari. <code>GestorArbreActivitats</code> en fa una
 * llista amb les dades de les activitats filles del projecte actual, i l'envia
 * a la Activity <code>LlistaActivitatsActivity</code> per que la mostri.
 * <p>
 * Com que és una classe sense funcionalitat, només és una estructura de dades,
 * així que faig els seus atributs públics per simplificar el codi.
 * <p>
 * Aquesta classe simplifica el passar les dades de projectes i tasques a la
 * Activity corresponent que les visualitza. Si passéssim directament la llista
 * d'activitats filles, com que es fa per serialització, s'acaba enviat tot
 * l'arbre, ja que els fills referencien als pares. El problema es tal, que amb
 * un arbre mitjà es perd tota la "responsiveness".
 *
 * @author joans
 * @version 6 febrer 2012
 */
public class DadesActivitat implements Serializable {
    /**
     * Ho demana el Checkstyle, però no he mirat per a què deu servir.
     */
    private static final long serialVersionUID = 1L;
    private final String projectEmoji;
    private final String taskEmoji;

    // TODO : passar tots els atributs a private i fer els corresponents
    // getters.

    private String activityEmoji;
    private String activeEmoji;

    public int getCurrentDrawableID() {
        return currentDrawableID;
    }

    public void setCurrentDrawableID(int currentDrawableID) {
        this.currentDrawableID = currentDrawableID;
    }

    private int currentDrawableID;

    public Boolean isExpanded() {
        return isExpanded;
    }

    public void toggleExpanded() {
        isExpanded = !isExpanded;
    }

    private Boolean isExpanded;

    /**
     * @see Activity
     */
    private Date dataInicial;

    /**
     * @see Activity
     */
    private Date dataFinal;

    /**
     * @see Activity
     */
    private long durada; // en segons

    /**
     * @see Activity
     */
    private String nom;

    /**
     * @see Activity
     */
    private String descripcio;

    /**
     * Hores de durada.
     */
    private long hores;

    /**
     * Minuts de durada.
     */
    private long minuts;

    /**
     * Segons de durada.
     */
    private long segons;

    /**
     * Per tal d'identificar el tipus d'activitat en la interfase d'usuari.
     */
    private boolean isProjecte;

    /**
     * Per tal d'identificar el tipus d'activitat en la interfase d'usuari.
     */
    private boolean isBasicTask;

    /**
     * La interfase d'usuari ho necessita saber per denotar-ho i també per
     * adequar la interacció (per exemple, no hauria de deixar cronometrar una
     * tasca que ja ho està sent).
     */
    private boolean isCronometreEngegat = false; // nomes te sentit per tasques

    /**
     * Extreu les dades de la activitat passada per paràmetre i les copia als
     * atributs propis.
     *
     * @param activity Task o projecte.
     */
    public DadesActivitat(final Activity activity) {
        /**
         * Factor de conversió
         */
        final long segonsPerHora = 3600;

        /**
         * Factor de conversió
         */
        final long segonsPerMinut = 60;

        dataInicial = activity.getStartDate();
        dataFinal = activity.getEndDate();
        durada = activity.getLength();
        nom = activity.getName();
        descripcio = activity.getDescription();
        hores = (long) (durada / segonsPerHora);
        minuts = (long) ((durada - hores * segonsPerHora) / segonsPerMinut);
        segons = (long) (durada - segonsPerHora * hores
                - segonsPerMinut * minuts);
        isExpanded = false;

        int activeEmojiUnicode = 0x1F3C3;
        activeEmoji = new String(Character.toChars(activeEmojiUnicode));
        int projectEmojiUnicode = 0x1F535;
        projectEmoji = new String(Character.toChars(projectEmojiUnicode));
        int taskEmojiUnicode = 0x1F537;
        taskEmoji = new String(Character.toChars(taskEmojiUnicode));
        currentDrawableID = android.R.drawable.arrow_down_float;

        if (activity instanceof Project) {
            isProjecte = true;
            isBasicTask = false;
            activityEmoji = projectEmoji;
        } else if (activity instanceof BasicTask) {
            isProjecte = false;
            isBasicTask = true;
            activityEmoji = taskEmoji;
            isCronometreEngegat = ((Task) activity).isCronometreEngegat();
        } else {
            isProjecte = false;
            isBasicTask = false;
            activityEmoji = taskEmoji;
            isCronometreEngegat = ((Task) activity).isCronometreEngegat();
        }
    }

    /**
     * Converteix una part de les dades d'un objecte DadesActivitat a un String,
     * que serà el que es mostrarà a la interfase d'usuari, ja que els
     * <code>ListView</code> mostren el que retorna aquest mètode per a cada un
     * dels seus elements. Veure
     * {@link LlistaActivitatsActivity.Receptor#onReceive}.
     *
     * @return nom i durada de la activitat, en format hores, minuts i segons.
     */
    // @Override
    public final String toString() {
        String str = activityEmoji + nom;
        String strdurada;
        if (isCronometreEngegat()) {
            str += activeEmoji;
            if (isExpanded) {
                str += "\n" + descripcio + "\n";
            }
        }
        if (isExpanded) {
            str += "\n" + descripcio + "\n";
        }
        if (durada > 0) {

            strdurada = "\n" + hores + "h " + minuts + "m " + segons + "s";
        } else {
            strdurada = "0s";
        }
        str += " " + strdurada;

        return str;
    }

    // Getters

    /**
     * Getter de <code>dataInicial</code>.
     *
     * @return {@link #dataInicial}.
     */
    public final Date getDataInicial() {
        return dataInicial;
    }

    /**
     * Getter de <code>dataFinal</code>.
     *
     * @return {@link #dataFinal}.
     */
    public final Date getDataFinal() {
        return dataFinal;
    }

    /**
     * Getter de <code>durada</code>.
     *
     * @return {@link #durada}.
     */
    public final long getDurada() {
        return durada;
    }

    /**
     * Getter de <code>hores</code>.
     *
     * @return {@link #hores}.
     */
    public final long getHores() {
        return hores;
    }

    /**
     * Getter de <code>minuts</code>.
     *
     * @return {@link #minuts}.
     */
    public final long getMinuts() {
        return minuts;
    }

    /**
     * Getter de <code>segons</code>.
     *
     * @return {@link #segons}.
     */
    public final long getSegons() {
        return segons;
    }

    /**
     * Getter de <code>nom</code>.
     *
     * @return {@link #nom}.
     */
    public final String getNom() {
        return nom;
    }

    /**
     * Getter de <code>descripcio</code>.
     *
     * @return {@link #descripcio}.
     */
    public final String getDescripcio() {
        return descripcio;
    }

    /**
     * Getter de <code>isProjecte</code>.
     *
     * @return {@link #isProjecte}.
     */
    public final boolean isProjecte() {
        return isProjecte;
    }

    /**
     * Getter de <code>isBasicTask</code>.
     *
     * @return {@link #isBasicTask}.
     */
    public final boolean isBasicTask() {
        return isBasicTask;
    }

    /**
     * Getter de <code>isCronometreEngegat</code>.
     *
     * @return {@link #isCronometreEngegat}.
     */
    public final boolean isCronometreEngegat() {
        return isCronometreEngegat;
    }

    public final int getID() {
        return 0;
    }
}
