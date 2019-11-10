package morian.apps.trackit.Sport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import org.joda.time.LocalDate;

import java.util.List;

import morian.apps.trackit.Sport.Sport;

@Dao
public interface SportDao {

    @Insert
    void insert(Sport sport);

    @Update
    void update(Sport sport);

    @Delete
    void delete(Sport sport);

    @Query("DELETE FROM sport_table")
    void deleteAllSports();

    @Query("SELECT * FROM sport_table ORDER BY date ASC")
    LiveData<List<Sport>> getAllSports();

    @Query("SELECT * FROM sport_table WHERE date=:currentDay ORDER BY date ASC")
    List<Sport> getSportsByDate(LocalDate currentDay);
}
