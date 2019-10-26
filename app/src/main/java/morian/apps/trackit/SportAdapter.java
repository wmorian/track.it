package morian.apps.trackit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.SportHolder> {

    private List<Sport> sports = new ArrayList<>();

    @NonNull
    @Override
    public SportHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sportView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sport_item, parent, false);
        return new SportHolder(sportView);
    }

    @Override
    public void onBindViewHolder(@NonNull SportHolder holder, int position) {
        Sport currentSport = sports.get(position);
        holder.textViewKind.setText(currentSport.getKind());
        holder.textViewTime.setText( currentSport.getTimeOfDay());
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

    class SportHolder extends RecyclerView.ViewHolder {
        private TextView textViewKind;
        private TextView textViewTime;
        private TextView textViewLength;

        public SportHolder(@NonNull View itemView) {
            super(itemView);
            textViewKind = itemView.findViewById(R.id.kinds_of_sports);
            textViewTime = itemView.findViewById(R.id.time_of_day);
            textViewLength = itemView.findViewById(R.id.length);
        }
    }
}