package morian.apps.trackit.Nutrition;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import morian.apps.trackit.Date.DateViewModel;
import morian.apps.trackit.R;

public class NutritionFragment extends Fragment {

    private NutritionViewModel nutritionViewModel;
    private DateViewModel dateViewModel;
    private String currentDate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);

        initDate();
        initDateViewModel();
        initSubmitButton(view);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initTimeOfDaySpinner(view);
    }

    private void initTimeOfDaySpinner(@NonNull View view) {
        Spinner timeOfDay = view.findViewById(R.id.time_of_day);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.time_of_day, android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        timeOfDay.setAdapter(adapter);

    }

    private void initSubmitButton(View view) {
        nutritionViewModel = ViewModelProviders.of(this).get(NutritionViewModel.class);

        Button submit = view.findViewById(R.id.submit_nutrition);

        final TableLayout table = view.findViewById(R.id.nutrition_table);
        final Spinner time = view.findViewById(R.id.time_of_day);
        final EditText editTextMisc = view.findViewById(R.id.miscellenous);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> items = new ArrayList<>();

                int numOfTableRows = table.getChildCount();
                for (int i = 0; i < numOfTableRows; i++) {
                    TableRow row = (TableRow) table.getChildAt(i);

                    int numOfBtns = row.getChildCount();
                    for (int j = 0; j < numOfBtns; j++) {
                        ToggleButton toggleButton = (ToggleButton) row.getChildAt(j);

                        if (toggleButton.isChecked()) {
                            items.add(toggleButton.getText().toString());
                            toggleButton.setChecked(false);
                        }
                    }
                }

                String misc = editTextMisc.getText().toString();

                if (!misc.isEmpty()) {
                    String[] miscItems = misc.split(" ");
                    for (String item:
                            miscItems) {
                        items.add(item);
                    }

                    editTextMisc.setText("");
                }

                Nutrition nutrition = new Nutrition(
                        currentDate,
                        time.getSelectedItem().toString(),
                        items
                );

                nutritionViewModel.insert(nutrition);
                Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT).show();
            }
        });

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

    private void initDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        Date date = new Date();
        currentDate = formatter.format(date);
    }
}
