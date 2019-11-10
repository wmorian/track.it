package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import morian.apps.trackit.Sport.SportKind;

public class SportKindEnumConverter {

    @TypeConverter
    public static SportKind intToEnum(int num) {
        return SportKind.values()[num];
    }

    @TypeConverter
    public static int EnumToInt(SportKind timeOfDay) {
        return timeOfDay.getValue();
    }
}
