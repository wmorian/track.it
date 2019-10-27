package morian.apps.trackit.Sport;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

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

    @Query("SELECT * FROM sport_table ORDER BY day ASC")
    LiveData<List<Sport>> getAllSports();

    @Query("SELECT * FROM sport_table WHERE day=:currentDay ORDER BY day ASC")
    List<Sport> getSportsByDate(String currentDay);
}
