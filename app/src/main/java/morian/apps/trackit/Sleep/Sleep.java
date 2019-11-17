package morian.apps.trackit.Sleep;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

@Entity(tableName = "sleep_table")
public class Sleep {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private LocalDate date;

    private LocalTime start;

    private LocalTime end;

    private LocalTime wc;

    private LocalTime awake;

    private Boolean satisfied;

    public Sleep(LocalDate date, LocalTime start, LocalTime end, LocalTime wc, LocalTime awake, Boolean satisfied) {
        this.date = date;
        this.start = start;
        this.end = end;
        this.wc = wc;
        this.awake = awake;
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

    public LocalTime getStart() {
        return start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public LocalTime getWc() {
        return wc;
    }

    public LocalTime getAwake() {
        return awake;
    }

    public Boolean getSatisfied() {
        return satisfied;
    }
}
