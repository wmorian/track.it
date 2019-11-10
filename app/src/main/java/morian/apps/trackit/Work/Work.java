package morian.apps.trackit.Work;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.Date;


@Entity(tableName = "work_table")
public class Work {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private LocalDate date;

    private Workplace workplace;

    @ColumnInfo(name = "start_time")
    private LocalTime startTime;

    @ColumnInfo(name = "end_time")
    private LocalTime endTime;

    private String subject;

    private Boolean satisfied;

    public Work(LocalDate date, Workplace workplace, LocalTime startTime, LocalTime endTime, String subject, Boolean satisfied) {
        this.date = date;
        this.workplace = workplace;
        this.startTime = startTime;
        this.endTime = endTime;
        this.subject = subject;
        this.satisfied = satisfied;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Workplace getWorkplace() { return workplace; }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getSubject() {
        return subject;
    }

    public Boolean getSatisfied() {
        return satisfied;
    }
}
