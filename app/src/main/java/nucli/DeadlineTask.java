package nucli;

import java.util.Date;

/**
 * Created by Vernon on 13/01/2017.
 */

public class DeadlineTask extends Task {

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    private Date deadline;

    public DeadlineTask(String nom, String descr, Project father, Date deadline) {
        super(nom, descr, father);
        this.deadline = deadline;
    }

}
