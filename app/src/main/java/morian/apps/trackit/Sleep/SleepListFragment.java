package morian.apps.trackit.Sleep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.R;
import morian.apps.trackit.ViewPageFragmentLifecycle;
import morian.apps.trackit.Sleep.Sleep;
import morian.apps.trackit.Sleep.SleepAdapter;
import morian.apps.trackit.Sleep.SleepViewModel;

public class SleepListFragment extends Fragment implements ViewPageFragmentLifecycle {

    private SleepAdapter adapter;
    private SleepViewModel sleepViewModel;
    private DateViewModel dateViewModel;
    private LocalDate currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new SleepAdapter();
        recyclerView.setAdapter(adapter);
        sleepViewModel = ViewModelProviders.of(this).get(SleepViewModel.class);
        dateViewModel = ViewModelProviders.of(getActivity()).get(DateViewModel.class);

        subscribeToDateChange();
        getItemTouchHelperForDeleteOnSwipe().attachToRecyclerView(recyclerView);

        // init list with the current date data
        initDate();
        adapter.setSleeps(getSleepsForCurrentDay());

        return view;
    }

    private void subscribeToDateChange() {
        dateViewModel.getDate().observe(this, new Observer<LocalDate>() {
            @Override
            public void onChanged(LocalDate s) {
                currentDate = s;
                adapter.setSleeps(getSleepsForCurrentDay());
            }
        });
    }

    private ItemTouchHelper getItemTouchHelperForDeleteOnSwipe() {
        return new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                sleepViewModel.delete(adapter.getSleepAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getActivity(), "Deleted!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Sleep> getSleepsForCurrentDay() {

        List<Sleep> sleeps = new ArrayList<>();

        try {
            sleeps = sleepViewModel.getSleepsByDate(currentDate);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        if (sleeps == null) sleeps = new ArrayList<>();

        return sleeps;
    }

    private void initDate() {
        currentDate = LocalDate.now();
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {
        adapter.setSleeps(getSleepsForCurrentDay());
    }
}
