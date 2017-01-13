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

    public ArrayList<Task> getTaskList() {
        ArrayList<Task> tasks = new ArrayList<Task>();
        boolean noTasks = true;
        for (Activity currentActivity : this.getActivityList()) {
            //if the current activity falls under any task categor and has this project as father
            // add it to the task list.
            if (((currentActivity.getClass() == Task.class)
                    || (currentActivity.getClass() == BasicTask.class)
                    || (currentActivity.getClass() == DeadlineTask.class))
                    && (currentActivity.father == this)) {
                tasks.add((Task) currentActivity);
                noTasks = false;
            }
            if (noTasks) {
                return null;
            }
        }
        return tasks;
    }

    public ArrayList<Project> getProjectTree() {
        ArrayList<Project> tree = new ArrayList<Project>();
        for (Activity currentActivity : this.getActivityList()) {
            if (currentActivity.getClass() == Project.class) {
                tree.add((Project) currentActivity);
                tree.addAll(((Project) currentActivity).getProjectTree());
            }
        }
        return tree;
    }

}
