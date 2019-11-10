package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import org.joda.time.LocalTime;

public class TimeConverter {

    @TypeConverter
    public static LocalTime fromString(String value) {
        LocalTime time = LocalTime.parse(value);
        return time;
    }

    @TypeConverter
    public static String toString(LocalTime time) {
        return time.toString("HH:mm");
    }
}
