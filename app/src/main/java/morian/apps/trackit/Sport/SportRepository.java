package morian.apps.trackit.Sport;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.concurrent.ExecutionException;

import morian.apps.trackit.Database.TrackDatabase;

public class SportRepository {
    private SportDao sportDao;
    private LiveData<List<Sport>> allSports;

    public SportRepository(Application application) {
//        SportDatabase database = SportDatabase.getInstance(application);
        TrackDatabase database = TrackDatabase.getInstance(application);
        sportDao = database.getSportDao();
        allSports = sportDao.getAllSports();
    }

    public void insert(Sport sport) {
        new InsertSportAsyncTask(sportDao).execute(sport);
    }

    public void update(Sport sport) {

        new UpdateSportAsyncTask(sportDao).execute(sport);
    }

    public void delete(Sport sport) {

        new DeleteSportAsyncTask(sportDao).execute(sport);
    }

    public void deleteAllNotes() {

        new DeleteAllSportsAsyncTask(sportDao).execute();
    }

    public List<Sport> getSportsByDay(LocalDate date)
            throws ExecutionException, InterruptedException {

        return new GetSportsByDateAsyncTask(sportDao).execute(date).get();
    }

    public LiveData<List<Sport>> getAllSports() {
        return allSports;
    }

    private static class InsertSportAsyncTask extends AsyncTask<Sport, Void, Void> {
        private SportDao sportDao;

        private InsertSportAsyncTask(SportDao sportDao) {
            this.sportDao = sportDao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            sportDao.insert(sports[0]);
            return null;
        }
    }

    private static class UpdateSportAsyncTask extends AsyncTask<Sport, Void, Void> {
        private SportDao sportDao;

        private UpdateSportAsyncTask(SportDao sportDao) {
            this.sportDao = sportDao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            sportDao.update(sports[0]);
            return null;
        }
    }

    private static class DeleteSportAsyncTask extends AsyncTask<Sport, Void, Void> {
        private SportDao sportDao;

        private DeleteSportAsyncTask(SportDao sportDao) {
            this.sportDao = sportDao;
        }

        @Override
        protected Void doInBackground(Sport... sports) {
            sportDao.delete(sports[0]);
            return null;
        }
    }

    private static class DeleteAllSportsAsyncTask extends AsyncTask<Void, Void, Void> {
        private SportDao sportDao;

        private DeleteAllSportsAsyncTask(SportDao sportDao) {
            this.sportDao = sportDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sportDao.deleteAllSports();
            return null;
        }
    }

    private static class GetSportsByDateAsyncTask extends AsyncTask<LocalDate, Void, List<Sport>> {
        private SportDao sportDao;

        private GetSportsByDateAsyncTask(SportDao sportDao) {
            this.sportDao = sportDao;
        }

        @Override
        protected List<Sport> doInBackground(LocalDate... dates) {
            return sportDao.getSportsByDate(dates[0]);
        }
    }
}
