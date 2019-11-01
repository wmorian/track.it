package morian.apps.trackit.Nutrition;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = Nutrition.class, version = 1)
@TypeConverters({ItemListConverter.class})
public abstract class NutritionDatabase extends RoomDatabase {

    private static NutritionDatabase instance;

    public abstract NutritionDao nutritionDao();

    public static synchronized NutritionDatabase getInstance(Context context) {
        if (instance == null) {
            context.deleteDatabase("nutrition_database");
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    NutritionDatabase.class,
                    "nutrition_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
