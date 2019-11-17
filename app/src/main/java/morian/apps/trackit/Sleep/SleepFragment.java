package morian.apps.trackit.Sleep;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.R;

public class SleepFragment extends Fragment {


    private SleepViewModel sleepViewModel;
    private DateViewModel dateViewModel;
    private LocalDate currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep, container, false);

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
        NumberPicker start_hr_np = view.findViewById(R.id.sleep_start_hr);
        NumberPicker start_min_np = view.findViewById(R.id.sleep_start_min);
        NumberPicker end_hr_np = view.findViewById(R.id.sleep_end_hr);
        NumberPicker end_min_np = view.findViewById(R.id.sleep_end_min);
        NumberPicker wc_hr_np = view.findViewById(R.id.sleep_wc_hr);
        NumberPicker wc_min_np = view.findViewById(R.id.sleep_wc_min);
        NumberPicker awake_hr_np = view.findViewById(R.id.sleep_awake_hr);
        NumberPicker awake_min_np = view.findViewById(R.id.sleep_awake_min);

        start_hr_np.setMinValue(0);
        start_hr_np.setMaxValue(23);
        start_min_np.setMinValue(0);
        start_min_np.setMaxValue(11);

        end_hr_np.setMinValue(0);
        end_hr_np.setMaxValue(23);
        end_min_np.setMinValue(0);
        end_min_np.setMaxValue(11);

        wc_hr_np.setMinValue(0);
        wc_hr_np.setMaxValue(23);
        wc_min_np.setMinValue(0);
        wc_min_np.setMaxValue(11);

        awake_hr_np.setMinValue(0);
        awake_hr_np.setMaxValue(23);
        awake_min_np.setMinValue(0);
        awake_min_np.setMaxValue(11);

        NumberPicker.Formatter formatter = new NumberPicker.Formatter() {

            @Override
            public String format(int value) {
                int temp = value * 5;
                return String.format("%02d", temp);
            }
        };

        start_min_np.setFormatter(formatter);
        end_min_np.setFormatter(formatter);
        wc_min_np.setFormatter(formatter);
        awake_min_np.setFormatter(formatter);
    }

    private void initButtons(View view) {
        sleepViewModel = ViewModelProviders.of(this).get(SleepViewModel.class);

        final ToggleButton thumbUpBtn = view.findViewById(R.id.sleep_thumb_up_btn);
        final ToggleButton thumbDownBtn = view.findViewById(R.id.sleep_thumb_down_btn);
        final NumberPicker startHour = view.findViewById(R.id.sleep_start_hr);
        final NumberPicker startMin = view.findViewById(R.id.sleep_start_min);
        final NumberPicker endHour = view.findViewById(R.id.sleep_end_hr);
        final NumberPicker endMin = view.findViewById(R.id.sleep_end_min);
        final NumberPicker wcHour = view.findViewById(R.id.sleep_wc_hr);
        final NumberPicker wcMin = view.findViewById(R.id.sleep_wc_min);
        final NumberPicker awakeHour = view.findViewById(R.id.sleep_awake_hr);
        final NumberPicker awakeMin = view.findViewById(R.id.sleep_awake_min);
        Button submitBtn = view.findViewById(R.id.submit_sleep);

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

                LocalTime startTime = new LocalTime(startHour.getValue(), startMin.getValue() * 5);
                LocalTime endTime = new LocalTime(endHour.getValue(), endMin.getValue() * 5);
                LocalTime wcTime = new LocalTime(wcHour.getValue(), wcMin.getValue() * 5);
                LocalTime awakeTime = new LocalTime(awakeHour.getValue(), awakeMin.getValue() * 5);
                Sleep sleep = new Sleep(
                        currentDate,
                        startTime,
                        endTime,
                        wcTime,
                        awakeTime,
                        getSatisfaction(thumbUpBtn, thumbDownBtn)
                );

                sleepViewModel.insert(sleep);
                thumbDownBtn.setChecked(false);
                thumbUpBtn.setChecked(false);
                startHour.setValue(0);
                startMin.setValue(0);
                endHour.setValue(0);
                endMin.setValue(0);
                wcHour.setValue(0);
                wcMin.setValue(0);
                awakeHour.setValue(0);
                awakeMin.setValue(0);

                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });
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
