package morian.apps.trackit;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SportFragment extends Fragment {

    private SportViewModel sportViewModel;

    public static final int REQUEST_CODE = 11;
    TextView currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);

        Button datePicker = view.findViewById(R.id.date_picker);
        currentDate = view.findViewById(R.id.current_date);
        final FragmentManager fm = getActivity().getSupportFragmentManager();

        datePicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.setTargetFragment(SportFragment.this, REQUEST_CODE);
                fragment.show(fm, "datePicker");
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        recyclerView.setHasFixedSize(true);

        final SportAdapter adapter = new SportAdapter();
        recyclerView.setAdapter(adapter);

        sportViewModel = ViewModelProviders.of(this).get(SportViewModel.class);
        sportViewModel.getAllSports().observe(this, new Observer<List<Sport>>() {
            @Override
            public void onChanged(List<Sport> sports) {
                // update RecyclerView
                adapter.setSports(sports);
//                Toast.makeText(getActivity(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        currentDate.setText(formatter.format(date));

        initTimeOfDaySpinner(view);
        initKindOfSportsSpinner(view);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            String date = data.getStringExtra("selectedDate");
            currentDate.setText(date);
        }
    }

    private void initTimeOfDaySpinner(@NonNull View view) {
        Spinner timeOfDay = view.findViewById(R.id.time_of_day);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.time_of_day, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeOfDay.setAdapter(adapter);

    }

    private void initKindOfSportsSpinner(@NonNull View view) {
        Spinner kindsOfSports = view.findViewById(R.id.kinds_of_sports);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.sports, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kindsOfSports.setAdapter(adapter);

    }
}