package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import org.joda.time.LocalDate;

public class DateConverter {

    @TypeConverter
    public static LocalDate fromString(String value) {
        LocalDate date = LocalDate.parse(value);
        return date;
    }

    @TypeConverter
    public static String toString(LocalDate date) {
        return date.toString();
    }
}
