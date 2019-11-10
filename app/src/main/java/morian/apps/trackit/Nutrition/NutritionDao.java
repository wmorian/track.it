package morian.apps.trackit.Nutrition;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.joda.time.LocalDate;

import java.util.List;

@Dao
public interface NutritionDao {

    @Insert
    void insert(Nutrition nutrition);

    @Update
    void update(Nutrition nutrition);

    @Delete
    void delete(Nutrition nutrition);

    @Query("DELETE FROM nutrition_table")
    void deleteAllNutritions();

    @Query("SELECT * FROM nutrition_table ORDER BY date ASC")
    LiveData<List<Nutrition>> getAllNutritions();

    @Query("SELECT * FROM nutrition_table WHERE date=:currentDay ORDER BY date ASC")
    List<Nutrition> getNutritionsByDate(LocalDate currentDay);
}
