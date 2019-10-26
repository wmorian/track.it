package morian.apps.trackit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

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

    public LiveData<List<Sport>> getAllSports() {
        return allSports;
    }
}
