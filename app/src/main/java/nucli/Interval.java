package nucli;

import java.io.Serializable;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Interval implements Observer, Serializable {

    private TimePeriod timePeriod = null;

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public Interval(Task t, Date horaInicial) {
        setTask(t);

        //durada = 0L;
        //dataInicial = horaInicial;
        //dataFinal = dataInicial;

        setTimePeriod(new TimePeriod(horaInicial));
    }


    public void update(Observable rellotge, Object ob) {
        Date horaActual = ((Clock) rellotge).getCurrentDate();
        Date df = timePeriod.getEndDate();

        long incrementDurada = calculaDurada(df, horaActual);
        //long incrementDurada = calculaDurada(dataFinal,horaActual);
        timePeriod.addLength(incrementDurada);
        timePeriod.setEndDate(horaActual);
        //durada = timePeriod.getLength();

        //durada += incrementDurada;
        //dataFinal = horaActual;

        //System.out.println("Durada d'interval " + durada + " s.");
        // propaguem l'increment de la durada de l'interval i data final cap amunt
        // ho fem ara i no en parar el cronometre per que la durada i les dates inicial
        // i final siguin consistents, ja que a mes es mostren per pantalla mentre
        // cronometrem
        this.getTask().updateTimeInformation(horaActual, incrementDurada);
    }


    private Task task = null;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public long calculaDurada(Date data1, Date data2) {
        return (long) (data2.getTime() - data1.getTime()) / 1000; // en segons
    }

    public Date getDataInicial() {
        return timePeriod.getStartDate();
    }

    public Date getDataFinal() {
        return timePeriod.getEndDate();
    }


    public long getDurada() {
        return timePeriod.getLength();
    }
}
