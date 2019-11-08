package morian.apps.trackit.Work;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import morian.apps.trackit.R;

public class WorkFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_work, container, false);

        initTimePickers(view);

        return view;
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
                return "" + temp;
            }
        };

        start_min_np.setFormatter(formatter);
        end_min_np.setFormatter(formatter);
    }
}
