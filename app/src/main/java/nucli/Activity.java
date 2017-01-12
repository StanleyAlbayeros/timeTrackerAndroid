package nucli;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public abstract class Activity implements Serializable {
    private TimePeriod timePeriod = null;

    protected TimePeriod getTimePeriod() {
        // per saber si un projecte te timePeriod creat i associat o es la
        // primera vegada que se'n cronometra una tasca i cal crear-li el
        // timePeriod
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        // protected perque a engegarCronometre de Task fem un setTimePeriod(new TimePeriod())
        // la primera vegada que cronometrem una tasca. Tambe ho farem aixi a Project.
        this.timePeriod = timePeriod;
    }

    private String description = new java.lang.String();

    protected Project father = null;

    private String name = new java.lang.String();

    /**
     * Task and project constructor
     * <p>
     * param root: root object
     * param name: Activity instance name
     * param description: Activity instance descrpition
     * param father: father activity of the new activity
     */
    public Activity(String name, String description, Project nFather, ArrayList<Activity> root) {
        this.father = nFather;
        this.name = name;
        this.description = description;
        root.add(this);
        father.setActivityList(root);
        creaPeriode(new Date());
        //log.info("Created activity: " + name + " with the following description: " + description);
    }

    /**
     * No arg constructor used to serialize
     */
    public Activity() {

    }

    public Project getFather() {
        return father;
    }

    public void setFather(Project nFather) {
        this.father = nFather;
    }

    public Date getStartDate() {
        return timePeriod.getStartDate();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String ndescription) {
        this.description = ndescription;
    }

    public void setStartDate(Date startDate) {
        timePeriod.setStartDate(startDate);
    }

    public long getLength() {
        return timePeriod.getLength();
    }

    public void setLength(int length) {
        timePeriod.setLength(length);
    }

    public void addLength(long more) {
        timePeriod.addLength(more);
    }

    public String getName() {
        return name;
    }

    public void setName(String nname) {
        this.name = nname;
    }

    public Project getProject() {
        return father;
    }

    public void setProject(Project nfather) {
        this.father = nfather;
    }


    public Date getEndDate() {
        return timePeriod.getEndDate();
    }

    public void setEndDate(Date endDate) {
        timePeriod.setEndDate(endDate);
    }

    /**
     */
    public void creaPeriode(Date horaActual) {
        if (getTimePeriod() == null) { // encara no hem cronometrat mai cap tasca derivada d'aquest projecte
            setTimePeriod(new TimePeriod(horaActual)); // aixo ja inicialitza la data inicial, final i durada
            Project father = getFather();
            if (father != null) { // si no som en un projecte arrel ja
                father.creaPeriode(horaActual);
            }
        }
    }


    /**
     */
    public void updateTimeInformation(Date endDate, long increment) {
        // updateTimeInformation la durada i la data final del projecte o tasca,
        // a consequencia de cronometrar
        addLength(increment);
        setEndDate(endDate);

        //System.out.println(this.getClass().getName() + " " + getName()    + " " + // Project o Task
        //		           "durada "     + getLength() + " " +
        //		           "data final " + getEndDate());
        // i propaga l'actualitzacio cap al projecte pare, si n'hi ha

        Project father = getFather();
        if (father != null) {
            father.updateTimeInformation(endDate, increment);
        }
    }


}