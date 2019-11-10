package morian.apps.trackit.Work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.R;

public class WorkFragment extends Fragment {

    private WorkViewModel workViewModel;
    private DateViewModel dateViewModel;
    private LocalDate currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);

        initDate();
        initDateViewModel();
        initTimePickers(view);
        initButtons(view);

        return view;
    }

    private void initDate() {
        currentDate = LocalDate.now();
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

    private void initTimePickers(View view) {
        NumberPicker start_hr_np = view.findViewById(R.id.start_time_hr);
        NumberPicker start_min_np = view.findViewById(R.id.start_time_min);
        NumberPicker end_hr_np = view.findViewById(R.id.end_time_hr);
        NumberPicker end_min_np = view.findViewById(R.id.end_time_min);

        start_hr_np.setMinValue(0);
        start_hr_np.setMaxValue(23);
        start_min_np.setMinValue(0);
        start_min_np.setMaxValue(11);

        end_hr_np.setMinValue(0);
        end_hr_np.setMaxValue(23);
        end_min_np.setMinValue(0);
        end_min_np.setMaxValue(11);

        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {

            @Override
            public String format(int value) {
                int temp = value * 5;
                return String.format("%02d", temp);
            }
        };

        start_min_np.setFormatter(formatter);
        end_min_np.setFormatter(formatter);
    }

    private void initButtons(View view) {
        workViewModel = ViewModelProviders.of(this).get(WorkViewModel.class);

        final ToggleButton workBtn = view.findViewById(R.id.work_btn);
        final ToggleButton homeBtn = view.findViewById(R.id.home_btn);
        final ToggleButton thumbUpBtn = view.findViewById(R.id.thumb_up_btn);
        final ToggleButton thumbDownBtn = view.findViewById(R.id.thumb_down_btn);
        final EditText subjectTxt = view.findViewById(R.id.subject_txt);
        final NumberPicker startHour = view.findViewById(R.id.start_time_hr);
        final NumberPicker startMin = view.findViewById(R.id.start_time_min);
        final NumberPicker endHour = view.findViewById(R.id.end_time_hr);
        final NumberPicker endMin = view.findViewById(R.id.end_time_min);
        Button submitBtn = view.findViewById(R.id.submit_work);

        workBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeBtn.setChecked(false);
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workBtn.setChecked(false);
            }
        });

        thumbUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbDownBtn.setChecked(false);
            }
        });

        thumbDownBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thumbUpBtn.setChecked(false);
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LocalTime startTime = new LocalTime(startHour.getValue(), startMin.getValue());
                LocalTime endTime = new LocalTime(endHour.getValue(), endMin.getValue() * 5);
                Work work = new Work(
                        currentDate,
                        getWorkplace(workBtn, homeBtn),
                        startTime,
                        endTime,
                        subjectTxt.getText().toString(),
                        getSatisfaction(thumbUpBtn, thumbDownBtn)
                );

                workViewModel.insert(work);
                workBtn.setChecked(false);
                homeBtn.setChecked(false);
                thumbDownBtn.setChecked(false);
                thumbUpBtn.setChecked(false);
                subjectTxt.setText("");
                startHour.setValue(0);
                startMin.setValue(0);
                endHour.setValue(0);
                endMin.setValue(0);

                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Workplace getWorkplace(ToggleButton workBtn, ToggleButton homeBtn) {
        if (workBtn.isChecked()) {
            return Workplace.Work;
        }
        else if (homeBtn.isChecked()) {
            return Workplace.HOME;
        }

        return null;
    }

    private Boolean getSatisfaction(ToggleButton thumbUpBtn, ToggleButton thumbDownBtn) {
        if (thumbDownBtn.isChecked()) {
            return false;
        }
        else if (thumbUpBtn.isChecked()) {
            return true;
        }

        return null;
    }
}
