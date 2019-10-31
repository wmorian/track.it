package morian.apps.trackit.Nutrition;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "nutrition_table")
public class Nutrition {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String day;

    @ColumnInfo(name = "time_of_day")
    private String timeOfDay;

    private List<String> items;

    public Nutrition(String day, String timeOfDay, List<String> items) {
        this.day = day;
        this.timeOfDay = timeOfDay;
        this.items = items;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public List<String> getItems() {
        return items;
    }
}
