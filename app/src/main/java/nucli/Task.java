package nucli;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

public class Task extends Activity {
    private ArrayList<Interval> intervalList = new ArrayList<>();

    public ArrayList<Interval> getIntervalList() {
        return intervalList;
    }

    public int intervalsSize() {
        return intervalList.size();
    }


    public boolean addIntervals(Interval interval) {
        return intervalList.add(interval);
    }

    public void setIntervalList(ArrayList<Interval> intervalList) {
        this.intervalList = intervalList;
    }

    public void startTaskInterval(Clock rellotge) {
        if (!isCronometreEngegat()) { // pre-condicio: la tasca no es pot estar ja cronometrant
            cronometreEngegat = true;
            Date horaActual = rellotge.getCurrentDate();
            // si es la primera vegada que cronometrem la tasca cal crear el seu
            // TimePeriod i el mateix respecte els seus projectes antecessors
            creaPeriode(horaActual);

            Interval nouInterval = new Interval(this, horaActual);
            this.addIntervals(nouInterval);
            Clock.getInstance().addObserver(nouInterval);
        }
    }

    public void stopTaskInterval(Clock rellotge) {
        if (isCronometreEngegat()) { // pre-condicio : la tasca s'esta cronometrant
            cronometreEngegat = false;
            Interval ultimInterval = intervalList.get(this.intervalsSize() - 1);
            rellotge.deleteObserver(ultimInterval);
            // no cal actualitzar data final i durada de tasca perque ja ho
            // haura ordenat update() d'interval
        }
    }

    public Task(String nom, String descr, Project father) {
        super(nom, descr, father, father.getActivityList());
    }

    private boolean cronometreEngegat;

    public boolean isCronometreEngegat() {
        return cronometreEngegat;
    }

}
























