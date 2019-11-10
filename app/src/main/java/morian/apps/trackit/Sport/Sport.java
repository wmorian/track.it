package morian.apps.trackit.Sport;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;

@Entity(tableName = "sport_table")
public class Sport {

    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private LocalDate date;

    @ColumnInfo(name = "time_of_day")
    private String timeOfDay;

    private String kind;

    private int length;

    public Sport(LocalDate date, String timeOfDay, String kind, int length) {
        this.date = date;
        this.timeOfDay = timeOfDay;
        this.kind = kind;
        this.length = length;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() { return date; }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public String getKind() {
        return kind;
    }

    public int getLength() {
        return length;
    }
}
