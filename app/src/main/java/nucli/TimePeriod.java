package nucli;

import java.io.Serializable;
import java.util.Date;

public class TimePeriod implements Serializable {

    private Date startDate;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date di) {
        this.startDate = di;
    }

    private Date endDate;

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date df) {
        this.endDate = df;
    }

    private long length;

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
        // System.out.println(this.info.descr + "length " + getLength());
    }

    public void addLength(long increment) {
        length += increment;
    }

    public TimePeriod(Date startDate) {
        setStartDate(startDate);
        setEndDate(startDate);
        setLength(0);
    }

    public TimePeriod(Date startDate, Date endDate, long length) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.length = length;
    }

}
