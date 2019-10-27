package morian.apps.trackit.Sport;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sport_table")
public class Sport {

    @PrimaryKey(autoGenerate = true)
    private int id;
    
    private String day;

    @ColumnInfo(name = "time_of_day")
    private String timeOfDay;

    private String kind;

    private int length;

    public Sport(String day, String timeOfDay, String kind, int length) {
        this.day = day;
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

    public String getDay() { return day; }

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
