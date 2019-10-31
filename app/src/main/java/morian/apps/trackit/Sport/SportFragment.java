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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.Date;

import morian.apps.trackit.Date.DateViewModel;
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

        initDate();
        initDateViewModel();
        initSubmitButton(view);
        initLengthHelper(view);

        return view;
    }

    private void initLengthHelper(View view) {

        Button min60 = view.findViewById(R.id.min_60);
        Button min30 = view.findViewById(R.id.min_30);
        Button min10 = view.findViewById(R.id.min_10);
        Button min5 = view.findViewById(R.id.min_5);
        ImageButton clear = view.findViewById(R.id.min_clear);
        final TextView textViewLength = view.findViewById(R.id.length);

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initTimeOfDaySpinner(view);
        initKindOfSportsSpinner(view);
    }

    private void initDateViewModel() {
        dateViewModel = ViewModelProviders.of(getActivity()).get(DateViewModel.class);
        dateViewModel.getDate().observe(this, new Observer<String>() {

            @Override
            public void onChanged(String s) {
                currentDate = s;
            }
        });
    }

    private void initSubmitButton(View view) {
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
                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
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

    private void initDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        currentDate = formatter.format(date);
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