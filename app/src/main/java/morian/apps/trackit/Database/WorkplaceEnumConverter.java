package morian.apps.trackit.Database;

import androidx.room.TypeConverter;

import morian.apps.trackit.Work.Workplace;

public class WorkplaceEnumConverter {

    @TypeConverter
    public static Workplace intToEnum(int num) {
        return Workplace.values()[num];
    }

    @TypeConverter
    public static int EnumToInt(Workplace timeOfDay) {
        return timeOfDay.getValue();
    }
}
