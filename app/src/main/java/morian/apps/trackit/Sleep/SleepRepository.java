package morian.apps.trackit.Sleep;

import android.app.Application;
import android.os.AsyncTask;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.concurrent.ExecutionException;

import morian.apps.trackit.Database.TrackDatabase;

public class SleepRepository {

    private SleepDao sleepDao;

    public SleepRepository(Application application) {
        TrackDatabase database = TrackDatabase.getInstance(application);
        sleepDao = database.getSleepDao();
    }

    public void insert(Sleep sleep) {
        new InsertWorkAsyncTask(sleepDao).execute(sleep);
    }

    public void update(Sleep sleep) {
        new UpdateWorkAsyncTask(sleepDao).execute(sleep);
    }

    public void delete(Sleep sleep) {
        new DeleteWorkAsyncTask(sleepDao).execute(sleep);
    }

    public List<Sleep> getSleepsByDate(LocalDate date)
            throws ExecutionException, InterruptedException {
        return new GetSleepsByDateAsyncTask(sleepDao).execute(date).get();
    }

    private static class InsertWorkAsyncTask extends AsyncTask<Sleep, Void, Void> {

        private SleepDao sleepDao;

        public InsertWorkAsyncTask(SleepDao sleepDao) {
            this.sleepDao = sleepDao;
        }

        @Override
        protected Void doInBackground(Sleep... sleeps) {
            sleepDao.insert(sleeps[0]);
            return null;
        }
    }

    private static class UpdateWorkAsyncTask extends AsyncTask<Sleep, Void, Void> {
        private SleepDao sleepDao;

        public UpdateWorkAsyncTask(SleepDao sleepDao) {
            this.sleepDao = sleepDao;
        }

        @Override
        protected Void doInBackground(Sleep... sleeps) {
            sleepDao.update(sleeps[0]);
            return null;
        }
    }

    private static class DeleteWorkAsyncTask extends AsyncTask<Sleep, Void, Void> {
        private SleepDao sleepDao;

        public DeleteWorkAsyncTask(SleepDao sleepDao) {
            this.sleepDao = sleepDao;
        }

        @Override
        protected Void doInBackground(Sleep... sleeps) {
            sleepDao.delete(sleeps[0]);
            return null;
        }
    }

    private static class GetSleepsByDateAsyncTask extends AsyncTask<LocalDate, Void, List<Sleep>> {
        private SleepDao sleepDao;

        public GetSleepsByDateAsyncTask(SleepDao sleepDao) {
            this.sleepDao = sleepDao;
        }

        @Override
        protected List<Sleep> doInBackground(LocalDate... localDates) {
            return sleepDao.getSleepsByDate(localDates[0]);
        }
    }
}
