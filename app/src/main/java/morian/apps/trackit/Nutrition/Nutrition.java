package morian.apps.trackit.Nutrition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.joda.time.LocalDate;

import java.util.List;

@Entity(tableName = "nutrition_table")
public class Nutrition {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private LocalDate date;

    @ColumnInfo(name = "time_of_day")
    private String timeOfDay;

    private List<String> items;

    public Nutrition(LocalDate date, String timeOfDay, List<String> items) {
        this.date = date;
        this.timeOfDay = timeOfDay;
        this.items = items;
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

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public List<String> getItems() {
        return items;
    }
}
