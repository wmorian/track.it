package morian.apps.trackit.Nutrition;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class NutritionRepository {

    private NutritionDao nutritionDao;
    private LiveData<List<Nutrition>> allNutritions;

    public NutritionRepository(Application application) {
        NutritionDatabase database = NutritionDatabase.getInstance(application);
        nutritionDao = database.nutritionDao();
        allNutritions = nutritionDao.getAllNutritions();
    }

    public void insert(Nutrition nutrition) {
        new InsertNutritionAsyncTask(nutritionDao).execute(nutrition);
    }

    public void update(Nutrition nutrition) {
        new UpdateNutritionAsyncTask(nutritionDao).execute(nutrition);
    }

    public void delete(Nutrition nutrition) {
        new DeleteNutritionAsyncTask(nutritionDao).execute(nutrition);
    }

    public void deleteAllNutritions() {
        new DeleteAllNutritionsAsyncTask(nutritionDao).execute();
    }

    public List<Nutrition> getNutritionsByDate(String date)
            throws ExecutionException, InterruptedException {
        return new GetNutritionsByDateAsyncTask(nutritionDao).execute(date).get();
    }

    public LiveData<List<Nutrition>> getAllNutritions() {
        return this.allNutritions;
    }

    private static class InsertNutritionAsyncTask extends AsyncTask<Nutrition, Void, Void> {
        private NutritionDao nutritionDao;

        public InsertNutritionAsyncTask(NutritionDao nutritionDao) {
            this.nutritionDao = nutritionDao;
        }


        @Override
        protected Void doInBackground(Nutrition... nutritions) {
            nutritionDao.insert(nutritions[0]);
            return null;
        }
    }

    private static class UpdateNutritionAsyncTask extends AsyncTask<Nutrition, Void, Void> {
        private NutritionDao nutritionDao;

        public UpdateNutritionAsyncTask(NutritionDao nutritionDao) {
            this.nutritionDao = nutritionDao;
        }


        @Override
        protected Void doInBackground(Nutrition... nutritions) {
            nutritionDao.update(nutritions[0]);
            return null;
        }
    }

    private static class DeleteNutritionAsyncTask extends AsyncTask<Nutrition, Void, Void> {
        private NutritionDao nutritionDao;

        public DeleteNutritionAsyncTask(NutritionDao nutritionDao) {
            this.nutritionDao = nutritionDao;
        }


        @Override
        protected Void doInBackground(Nutrition... nutritions) {
            nutritionDao.delete(nutritions[0]);
            return null;
        }
    }

    private static class DeleteAllNutritionsAsyncTask extends AsyncTask<Void, Void, Void> {
        private NutritionDao nutritionDao;

        public DeleteAllNutritionsAsyncTask(NutritionDao nutritionDao) {
            this.nutritionDao = nutritionDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            this.nutritionDao.deleteAllNutritions();
            return null;
        }
    }


    private static class GetNutritionsByDateAsyncTask extends AsyncTask<String, Void, List<Nutrition>> {
        private NutritionDao nutritionDao;

        public GetNutritionsByDateAsyncTask(NutritionDao nutritionDao) {
            this.nutritionDao = nutritionDao;
        }

        @Override
        protected List<Nutrition> doInBackground(String... strings) {
            return this.nutritionDao.getNutritionsByDate(strings[0]);
        }
    }
}

