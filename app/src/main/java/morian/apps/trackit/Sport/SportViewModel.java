package morian.apps.trackit.Sport;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.concurrent.ExecutionException;


public class SportViewModel extends AndroidViewModel {

    private SportRepository repository;
    private LiveData<List<Sport>> allSports;

    public SportViewModel(@NonNull Application application) {
        super(application);

        repository = new SportRepository(application);
        allSports = repository.getAllSports();
    }

    public void insert(Sport sport) {

        repository.insert(sport);
    }

    public void update(Sport sport) {

        repository.update(sport);
    }

    public void delete(Sport sport) {

        repository.delete(sport);
    }

    public void deleteAllSports() {

        repository.deleteAllNotes();
    }

    public List<Sport> getSportsByDate(LocalDate date)
            throws ExecutionException, InterruptedException {

        return repository.getSportsByDay(date);
    }

    public LiveData<List<Sport>> getAllSports() {

        return allSports;
    }
}
