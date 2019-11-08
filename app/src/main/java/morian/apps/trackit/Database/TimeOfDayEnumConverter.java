package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import morian.apps.trackit.Date.TimeOfDay;

public class TimeOfDayEnumConverter {

    @TypeConverter
    public static TimeOfDay intToEnum(int num) {
        return TimeOfDay.values()[num];
    }

    @TypeConverter
    public static int EnumToInt(TimeOfDay timeOfDay) {
        return timeOfDay.getValue();
    }
}
