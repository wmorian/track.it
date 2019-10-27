package morian.apps.trackit.Sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutionException;

import morian.apps.trackit.R;

public class SportListFragment extends Fragment {

    private SportViewModel sportViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_items, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        final SportAdapter adapter = new SportAdapter();
        recyclerView.setAdapter(adapter);

        sportViewModel = ViewModelProviders.of(this).get(SportViewModel.class);

        sportViewModel.getAllSports().observe(this, new Observer<List<Sport>>() {
            @Override
            public void onChanged(List<Sport> sports) {
                adapter.setSports(sports);
            }
        });

        return view;
    }
}
