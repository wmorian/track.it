package morian.apps.trackit.Sleep;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.joda.time.LocalDate;

import java.util.List;

@Dao
public interface SleepDao {

    @Insert
    void insert(Sleep sleep);

    @Update
    void update(Sleep sleep);

    @Delete
    void delete(Sleep sleep);

    @Query("SELECT * FROM sleep_table WHERE date=:date ORDER BY start ASC")
    List<Sleep> getSleepsByDate(LocalDate date);
}
