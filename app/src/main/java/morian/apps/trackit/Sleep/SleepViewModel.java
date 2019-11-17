package morian.apps.trackit.Sleep;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class SleepViewModel extends AndroidViewModel {

    private SleepRepository sleepRepository;

    public SleepViewModel(@NonNull Application application) {
        super(application);

        sleepRepository = new SleepRepository(application);
    }

    public void insert(Sleep sleep) {
        sleepRepository.insert(sleep);
    }

    public void update(Sleep sleep) {
        sleepRepository.update(sleep);
    }

    public void delete(Sleep sleep) {
        sleepRepository.delete(sleep);
    }

    public List<Sleep> getSleepsByDate(LocalDate date)
            throws ExecutionException, InterruptedException {
        return sleepRepository.getSleepsByDate(date);
    }
}
