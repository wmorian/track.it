package morian.apps.trackit.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import morian.apps.trackit.Nutrition.Nutrition;
import morian.apps.trackit.Nutrition.NutritionDao;
import morian.apps.trackit.Sport.Sport;
import morian.apps.trackit.Sport.SportDao;
import morian.apps.trackit.Work.Work;
import morian.apps.trackit.Work.WorkDao;

@Database(version = 2, entities = {Sport.class, Nutrition.class, Work.class})
@TypeConverters({
        StringListConverter.class,
        TimeOfDayEnumConverter.class,
        WorkplaceEnumConverter.class,
        SportKindEnumConverter.class,
        TimeConverter.class,
        DateConverter.class})
public abstract class TrackDatabase extends RoomDatabase {

    private static TrackDatabase instance;

    abstract public SportDao getSportDao();
    abstract public NutritionDao getNutritionDao();
    abstract public WorkDao getWorkDao();


    public static synchronized TrackDatabase getInstance(Context context) {
        if (instance == null) {
//            context.deleteDatabase("track_database");
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TrackDatabase.class,
                    "track_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
