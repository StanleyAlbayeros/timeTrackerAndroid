package nucli;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Project extends Activity {

    private ArrayList<Activity> activityList = new java.util.ArrayList<Activity>();

    public Project() {
        this.activityList = new ArrayList<Activity>();

    }

    public Project(String name, String description, Project father, ArrayList<Activity> root) {
        super(name, description, father, root);
    }

    public Project(String name, String description, Project father) {
        super(name, description, father, father.getActivityList());
    }

    public ArrayList<Activity> getActivityList() {
        return activityList;
    }

    public void setActivityList(ArrayList<Activity> nactivityList) {
        this.activityList = nactivityList;
    }

}
