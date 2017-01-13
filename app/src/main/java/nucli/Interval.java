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

    public Interval(Task fatherTask, Date horaInicial) {
        setFatherTask(fatherTask);

        //durada = 0L;
        //dataInicial = horaInicial;
        //dataFinal = dataInicial;

        setTimePeriod(new TimePeriod(horaInicial));
    }


    public void update(Observable rellotge, Object ob) {
        Date horaActual = ((Clock) rellotge).getCurrentDate();
        Date df = timePeriod.getEndDate();

        if (fatherTask instanceof DeadlineTask) {
            if (!horaActual.after(((DeadlineTask) fatherTask).getDeadline())) {
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
                this.getFatherTask().updateTimeInformation(horaActual, incrementDurada);
            } else {
                fatherTask.stopTaskInterval((Clock) rellotge);
            }
        } else {
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
            this.getFatherTask().updateTimeInformation(horaActual, incrementDurada);
        }
    }


    private Task fatherTask = null;

    public Task getFatherTask() {
        return fatherTask;
    }

    public void setFatherTask(Task fatherTask) {
        this.fatherTask = fatherTask;
    }

    public long calculaDurada(Date data1, Date data2) {
        return (long) (data2.getTime() - data1.getTime()) / 1000; // en segons
    }

    public Date getStartDate() {
        return timePeriod.getStartDate();
    }

    public Date getEndDate() {
        return timePeriod.getEndDate();
    }


    public long getLength() {
        return timePeriod.getLength();
    }
}
