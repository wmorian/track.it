package morian.apps.trackit.Nutrition;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NutritionViewModel extends AndroidViewModel {

    private NutritionRepository repository;
    private LiveData<List<Nutrition>> allNutritions;


    public NutritionViewModel(@NonNull Application application) {
        super(application);

        repository = new NutritionRepository(application);
        allNutritions = repository.getAllNutritions();
    }

    public void insert(Nutrition nutrition) {
        repository.insert(nutrition);
    }

    public void update(Nutrition nutrition) {
        repository.update(nutrition);
    }

    public void delete(Nutrition nutrition) {
        repository.delete(nutrition);
    }

    public void deleteAllNutritions() {
        repository.deleteAllNutritions();
    }

    public List<Nutrition> getNutritionsByDate(String date)
            throws ExecutionException, InterruptedException {

        return repository.getNutritionsByDate(date);
    }

    public LiveData<List<Nutrition>> getAllNutritions() {
        return allNutritions;
    }
}
