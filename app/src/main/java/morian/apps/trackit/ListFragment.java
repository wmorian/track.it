package morian.apps.trackit;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import morian.apps.trackit.Nutrition.NutritionListFragment;
import morian.apps.trackit.ViewPageFragmentLifcycle;

public class ListFragment extends Fragment implements ViewPageFragmentLifcycle {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        FragmentTransaction transaction = getFragmentManager()
                .beginTransaction()
                .replace(R.id.list_frame, new NutritionListFragment());

        transaction.commit();

        return view;
    }

    @Override
    public void onPauseFragment() {

    }

    @Override
    public void onResumeFragment() {

    }
}
