package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateConverter {

//    @TypeConverter
//    public static Date fromTimestamp(Long value) {
//        return value == null ? null : new Date(value);
//    }
//
//    @TypeConverter
//    public static Long dateToTimestamp(Date date) {
//        return date == null ? null : date.getTime();
//    }

    @TypeConverter
    public static Date fromTimestamp(String value) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
        Date date;
        try {
            date = formatter.parse(value);
        } catch (ParseException e) {
            e.printStackTrace();
            date = null;
        }
        return date;
    }

    @TypeConverter
    public static String timeToTimestamp(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd:MM:yyyy");
        return formatter.format(date);
    }
}
