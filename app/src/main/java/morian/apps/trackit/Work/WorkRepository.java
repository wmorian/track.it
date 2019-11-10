package morian.apps.trackit.Work;

import android.app.Application;
import android.os.AsyncTask;

import org.joda.time.LocalDate;

import java.util.List;
import java.util.concurrent.ExecutionException;

import morian.apps.trackit.Database.TrackDatabase;

public class WorkRepository {
    private WorkDao workDao;

    public WorkRepository(Application application) {
        TrackDatabase database = TrackDatabase.getInstance(application);
        workDao = database.getWorkDao();
    }

    public void insert(Work work) {
        new InsertWorkAsyncTask(workDao).execute(work);
    }

    public void update(Work work) {
        new UpdateWorkAsyncTask(workDao).execute(work);
    }

    public void delete(Work work) {
        new DeleteWorkAsyncTask(workDao).execute(work);
    }

    public List<Work> getWorksByDate(LocalDate date)
            throws ExecutionException, InterruptedException {
        return new GetWorksByDateAsyncTask(workDao).execute(date).get();
    }

    private static class InsertWorkAsyncTask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        private InsertWorkAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.insert(works[0]);
            return null;
        }
    }

    private static class UpdateWorkAsyncTask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        private UpdateWorkAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.update(works[0]);
            return null;
        }
    }

    private static class DeleteWorkAsyncTask extends AsyncTask<Work, Void, Void> {
        private WorkDao workDao;

        private DeleteWorkAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected Void doInBackground(Work... works) {
            workDao.delete(works[0]);
            return null;
        }
    }

    private static class GetWorksByDateAsyncTask extends AsyncTask<LocalDate, Void, List<Work>> {
        private WorkDao workDao;

        private GetWorksByDateAsyncTask(WorkDao workDao) {
            this.workDao = workDao;
        }

        @Override
        protected List<Work> doInBackground(LocalDate... date) {
            return workDao.getWorksByDate(date[0]);
        }
    }
}
