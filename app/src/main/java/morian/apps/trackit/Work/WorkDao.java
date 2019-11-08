package morian.apps.trackit.Work;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.Date;
import java.util.List;

@Dao
public interface WorkDao {

    @Insert
    void insert(Work work);

    @Update
    void update(Work work);

    @Delete
    void delete(Work work);

    @Query("SELECT * FROM work_table WHERE date=:currentDay ORDER BY startTime ASC")
    List<Work> getWorksByDate(Date currentDay);
}
