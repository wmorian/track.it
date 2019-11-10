package morian.apps.trackit.Date;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import org.joda.time.LocalDate;

import morian.apps.trackit.R;

public class DateFragment extends Fragment {

    private static final int REQUEST_CODE = 11;
    private TextView textViewGlobalDate;
    private DateViewModel dateViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date, container, false);

        textViewGlobalDate = view.findViewById(R.id.global_date);

        LocalDate date = LocalDate.now();
        textViewGlobalDate.setText(date.toString("dd.MM.yyyy"));

        dateViewModel = ViewModelProviders.of(getActivity()).get(DateViewModel.class);
//        textViewGlobalDate.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                dateViewModel.setDate(textViewGlobalDate.getText().toString());
//            }
//        });

        final FragmentManager fm = getActivity().getSupportFragmentManager();
        textViewGlobalDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                DialogFragment fragment = new DatePickerFragment();
                fragment.setTargetFragment(DateFragment.this, REQUEST_CODE);
                fragment.show(fm, "datePicker");
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
//            String date = data.getStringExtra("selectedDate");
            LocalDate date = LocalDate.parse(data.getStringExtra("selectedDate"));
            textViewGlobalDate.setText(date.toString("dd.MM.yyyy"));
            dateViewModel.setDate(date);
        }
    }
}
