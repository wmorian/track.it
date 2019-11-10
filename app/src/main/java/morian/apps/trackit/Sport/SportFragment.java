package morian.apps.trackit.Sport;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.joda.time.LocalDate;

import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.R;

public class SportFragment extends Fragment {

    private SportViewModel sportViewModel;
    private DateViewModel dateViewModel;
    private LocalDate currentDate;
    private String kindOfSport;

    public SportFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport, container, false);

        initDate();
        initDateViewModel();
        initSubmitButton(view);
        initSportButtons(view);
        initLengthHelper(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initTimeOfDaySpinner(view);
    }

    public void onSportClicked(View view) {

        ConstraintLayout parent = (ConstraintLayout) view.getParent();
        for (int i = 0; i < parent.getChildCount(); i++) {
            View current = parent.getChildAt(i);
            if (current != view && current instanceof ToggleButton) {
                ((ToggleButton) current).setChecked(false);
            }
        }

        ToggleButton activeSport = ((ToggleButton) view);
        activeSport.setChecked(true);

        switch (activeSport.getId()) {
            case R.id.running:
                kindOfSport = "running";
                break;
            case R.id.exercise:
                kindOfSport = "exercise";
                break;
            case R.id.stretching:
                kindOfSport = "stretching";
                break;
            case R.id.yoga:
                kindOfSport = "yoga";
                break;
            case R.id.cycling:
                kindOfSport = "cycling";
                break;
            case R.id.walking:
                kindOfSport = "walking";
                break;
        }
    }

    private void resetSportButtons(View view) {
        final ConstraintLayout constraintLayout = view.findViewById(R.id.subject);
        for (int i = 0; i < constraintLayout.getChildCount(); i++) {
            View current = constraintLayout.getChildAt(i);
            if (current instanceof ToggleButton) {
                ((ToggleButton) current).setChecked(false);
            }
        }
    }

    private void initDateViewModel() {
        dateViewModel = ViewModelProviders.of(getActivity()).get(DateViewModel.class);
        dateViewModel.getDate().observe(this, new Observer<LocalDate>() {

            @Override
            public void onChanged(LocalDate s) {
                currentDate = s;
            }
        });
    }

    private void initSubmitButton(final View view) {
        sportViewModel = ViewModelProviders.of(this).get(SportViewModel.class);

        Button submit = view.findViewById(R.id.submit_sport);
        final Spinner time = view.findViewById(R.id.start_time);
        final EditText length = view.findViewById(R.id.place);

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Sport sport = new Sport(
                        currentDate,
                        time.getSelectedItem().toString(),
                        kindOfSport,
                        Integer.parseInt(length.getText().toString()));
                sportViewModel.insert(sport);

                resetSportButtons(view);
                length.setText("");
                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initTimeOfDaySpinner(@NonNull View view) {
        Spinner timeOfDay = view.findViewById(R.id.start_time);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.time_of_day, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeOfDay.setAdapter(adapter);

    }

    private void initSportButtons(View view) {
        ConstraintLayout layout = view.findViewById(R.id.subject);

        for (int i = 0; i < layout.getChildCount(); i++) {
            ToggleButton btn = (ToggleButton) layout.getChildAt(i);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSportClicked(v);
                }
            });
        }
    }

    private void initLengthHelper(View view) {

        Button min60 = view.findViewById(R.id.min_60);
        Button min30 = view.findViewById(R.id.min_30);
        Button min10 = view.findViewById(R.id.min_10);
        Button min5 = view.findViewById(R.id.min_5);
        ImageButton clear = view.findViewById(R.id.min_clear);
        final TextView textViewLength = view.findViewById(R.id.place);

        min60.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addLength(textViewLength, 60);
            }
        });

        min30.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addLength(textViewLength, 30);
            }
        });

        min10.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addLength(textViewLength, 10);
            }
        });

        min5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addLength(textViewLength, 5);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                textViewLength.setText("");
            }
        });
    }

    private void initDate() {
        currentDate = LocalDate.now();
    }

    private void addLength(TextView textView, int min) {
        String currentText = textView.getText().toString();

        if (currentText.isEmpty()) {
            textView.setText(String.valueOf(min));
        }
        else {
            textView.setText(String.valueOf(Integer.parseInt(currentText) + min));
        }
    }
}