package nucli;
// import java.io.*;

import com.example.joans.timetracker.Actualitzable;
import com.example.joans.timetracker.Actualitzador;

import java.util.Date;
import java.util.Observable;

/**
 * Actualitzacio Setembre 2016 :
 * <p>
 * He provat d'utilitzar un rellotge "normal i corrent", el que fem per la fita 1 i
 * que esta basat o conte un Thread de Java, ja sigui en la classe Timer, TimerTask,
 * o en la versio més simple, que es tenir un rellotge sense practicament funcionalitat
 * i alhora una classe GeneradorTicks que es la que realment es un Thread i va
 * actualitzant el rellotge cada segon.
 * <p>
 * Amb aquest tipus de rellotge la aplicacio Android segueix funcionant en l'emulador
 * de l'Android Studio.
 * <p>
 * No obstant, prefereixo la implementacio original perque evitar que el Thread de
 * GeneradorTicks estigui viu sempre. Amb la implementacio original podem parar
 * el mecanisme d'actualitzacio (de fet ho fem). No obstant, deixo incloses al
 * projecte el codi de les classes Rellotge i GeneradorTicks per si cas, nomes
 * que excloses del path de compilacio (arxiu build.gradle).
 */
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
