package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeConverter {

    @TypeConverter
    public static Date fromTimestamp(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        Date time;
        try {
            time = formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            time = null;
        }
        return time;
    }

    @TypeConverter
    public static String timeToTimestamp(Date time) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy HH:mm");
        return formatter.format(time);
    }
}
