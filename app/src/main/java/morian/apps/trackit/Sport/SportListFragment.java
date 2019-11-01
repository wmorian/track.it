package morian.apps.trackit.Sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.R;
import morian.apps.trackit.ViewPageFragmentLifecycle;

public class SportListFragment extends Fragment implements ViewPageFragmentLifecycle {

    private SportAdapter adapter;
    private SportViewModel sportViewModel;
    private DateViewModel dateViewModel;
    private String currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        adapter = new SportAdapter();
        recyclerView.setAdapter(adapter);
        sportViewModel = ViewModelProviders.of(this).get(SportViewModel.class);

        dateViewModel = ViewModelProviders.of(getActivity()).get(DateViewModel.class);
        dateViewModel.getDate().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                currentDate = s;
                adapter.setSports(getSportsForCurrentDay());
            }
        });

        // init list with the current date data
        currentDate = initDate();
        adapter.setSports(getSportsForCurrentDay());

        return view;
    }

    private List<Sport> getSportsForCurrentDay() {

        List<Sport> sports = new ArrayList<>();

        try {
            sports = sportViewModel.getSportsByDate(currentDate);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        if (sports == null) sports = new ArrayList<>();

        return sports;
    }

    private String initDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        return formatter.format(date);
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {
        adapter.setSports(getSportsForCurrentDay());
    }
}
