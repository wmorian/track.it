package morian.apps.trackit;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import morian.apps.trackit.Nutrition.ItemListConverter;
import morian.apps.trackit.Nutrition.Nutrition;
import morian.apps.trackit.Nutrition.NutritionDao;
import morian.apps.trackit.Sport.Sport;
import morian.apps.trackit.Sport.SportDao;

@Database(version = 1, entities = {Sport.class, Nutrition.class})
@TypeConverters({ItemListConverter.class})
public abstract class TrackDatabase extends RoomDatabase {

    private static TrackDatabase instance;

    abstract public SportDao getSportDao();

    abstract public NutritionDao getNutritionDao();

    public static synchronized TrackDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    TrackDatabase.class,
                    "track_table")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
