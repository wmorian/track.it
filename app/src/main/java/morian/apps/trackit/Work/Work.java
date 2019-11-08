package morian.apps.trackit.Work;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity(tableName = "work_table")
public class Work {

    @PrimaryKey(autoGenerate = true)
    public int id;

    public Date date;

    public Date startTime;

    public Date endTime;

    public String subject;

    public Boolean satisfied;
}
