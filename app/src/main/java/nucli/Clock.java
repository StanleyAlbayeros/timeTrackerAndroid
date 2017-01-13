package nucli;
// import java.io.*;

import com.example.joans.timetracker.Actualitzable;
import com.example.joans.timetracker.Actualitzador;

import java.util.Date;
import java.util.Observable;

public class Clock extends Observable implements Actualitzable {

    private Actualitzador clockTicker;
    private final int delayMillis = 1000;

    private static Clock myClockSingleton = null;

    private Clock() {
        setCurrentDate(new Date());
        clockTicker = new Actualitzador(this, delayMillis, "rellotge");
    }

    public static synchronized Clock getInstance() {
        if (myClockSingleton == null) {
            myClockSingleton = new Clock();
        }
        return myClockSingleton;

    }

    private static Date currentDate;

    public static Date getCurrentDate() {
        return currentDate;
    }

    public static void setCurrentDate(Date ncurrentDate) {
        Clock.currentDate = ncurrentDate;
    }

    private void tick() {
        setCurrentDate(new Date());
        setChanged();
        notifyObservers(this);
    }

    @Override
    public void update() {
        tick();
    }

    public void stop() {
        clockTicker.para();
    }

    public void start() {
        clockTicker.engega();
    }

}
