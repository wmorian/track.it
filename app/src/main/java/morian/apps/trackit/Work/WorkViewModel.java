package morian.apps.trackit.Work;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WorkViewModel extends AndroidViewModel {

    private WorkRepository repository;

    public WorkViewModel(@NonNull Application application) {
        super(application);

        repository = new WorkRepository(application);
    }

    public void insert(Work work) {
        repository.insert(work);
    }

    public void update(Work work) {
        repository.update(work);
    }

    public void delete(Work work) {
        repository.delete(work);
    }

    public List<Work> getWorksByDate(Date date)
            throws ExecutionException, InterruptedException {

        return repository.getWorksByDate(date);
    }
}
