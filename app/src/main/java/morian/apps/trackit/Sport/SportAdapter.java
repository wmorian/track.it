package morian.apps.trackit.Sport;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import morian.apps.trackit.R;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportHolder> {

    private List<Sport> sports = new ArrayList<>();

    @NonNull
    @Override
    public SportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sportView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sport_list_item, parent, false);
        return new SportHolder(sportView);
    }

    @Override
    public void onBindViewHolder(@NonNull SportHolder holder, int position) {
        Sport currentSport = sports.get(position);
        holder.imageViewKind.setImageResource(getSportKindResId(currentSport));
        holder.textViewTime.setText(currentSport.getTimeOfDay());
        holder.textViewLength.setText(String.valueOf(currentSport.getLength()).concat(" min"));
    }

    @Override
    public int getItemCount() {

        return sports.size();
    }

    public void setSports(List<Sport> sports) {
        this.sports = sports;
        notifyDataSetChanged();
    }

    public Sport getSportAt(int position) {
        return sports.get(position);
    }

    private int getSportKindResId(Sport sport) {
        switch (sport.getKind()) {
            case RUNNING:
                return R.drawable.ic_running_off;
            case EXERCISE:
                return R.drawable.ic_exercise_off;
            case STRETCHING:
                return R.drawable.ic_stretching_off;
            case YOGA:
                return R.drawable.ic_yoga_off;
            case CYCLING:
                return R.drawable.ic_cycling_off;
            case WALKING:
                return R.drawable.ic_walking_off;
            default:
                return 0;
        }
    }

    class SportHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewKind;
        private TextView textViewTime;
        private TextView textViewLength;

        public SportHolder(@NonNull View itemView) {
            super(itemView);
            imageViewKind = itemView.findViewById(R.id.card_sport_kind);
            textViewTime = itemView.findViewById(R.id.card_sport_time_of_day);
            textViewLength = itemView.findViewById(R.id.card_sport_length);
        }
    }
}
