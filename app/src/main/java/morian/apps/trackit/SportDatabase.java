package morian.apps.trackit;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Sport.class, version = 1)
public abstract class SportDatabase extends RoomDatabase {

    private static SportDatabase instance;

    public abstract SportDao sportDao();

    public static synchronized SportDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    SportDatabase.class, "sport_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private SportDao sportDao;

        private PopulateDbAsyncTask(SportDatabase db) {
            sportDao = db.sportDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            sportDao.insert(new Sport("26.10.2019", "Morning", "Running", 30));
            sportDao.insert(new Sport("26.10.2019", "Noon", "Running", 40));
//            sportDao.insert(new Sport("25.10.2019", "Evening", "Running", 50));
            return null;
        }
    }
}
