package morian.apps.trackit;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

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

    @Query("SELECT * FROM sport_table ORDER BY day DESC")
    List<Sport> getAllSports();
}
