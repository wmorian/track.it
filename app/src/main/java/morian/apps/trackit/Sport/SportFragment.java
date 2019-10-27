package morian.apps.trackit.Sport;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Date;

import morian.apps.trackit.Date.DateFragment;
import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.DatePickerFragment;
import morian.apps.trackit.R;

public class SportFragment extends Fragment {

    private SportViewModel sportViewModel;
    private DateViewModel dateViewModel;
    private String currentDate;

    public static final int REQUEST_CODE = 11;

    public SportFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);

        dateViewModel = ViewModelProviders.of(getActivity()).get(DateViewModel.class);
        dateViewModel.getDate().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                currentDate = s;
            }
        });

        sportViewModel = ViewModelProviders.of(this).get(SportViewModel.class);

        Button submit = view.findViewById(R.id.submit_sport);
        final Spinner kind = view.findViewById(R.id.kinds_of_sports);
        final Spinner time = view.findViewById(R.id.time_of_day);
        final EditText length = view.findViewById(R.id.length);
        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Sport sport = new Sport(
                        currentDate,
                        time.getSelectedItem().toString(),
                        kind.getSelectedItem().toString(),
                        Integer.parseInt(length.getText().toString()));
                sportViewModel.insert(sport);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initTimeOfDaySpinner(view);
        initKindOfSportsSpinner(view);
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