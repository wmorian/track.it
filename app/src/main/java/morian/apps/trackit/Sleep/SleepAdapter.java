package morian.apps.trackit.Sleep;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import morian.apps.trackit.R;
import morian.apps.trackit.Sleep.Sleep;

public class SleepAdapter extends RecyclerView.Adapter<SleepAdapter.SleepHolder> {

    private List<Sleep> sleeps = new ArrayList<>();

    @NonNull
    @Override
    public SleepHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View sleepView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sleep_list_item, parent, false);
        return new SleepHolder(sleepView);
    }

    @Override
    public void onBindViewHolder(@NonNull SleepHolder holder, int position) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

        Sleep currentSleep = sleeps.get(position);


        String startTime = currentSleep.getStart().toString(formatter);
        String endTime = currentSleep.getEnd().toString(formatter);
        holder.textViewSleep.setText(startTime + " - " + endTime);

        LocalTime wcTime = currentSleep.getWc();
        String wc = "-";
        if (wcTime != null) {
            wc = wcTime.toString(formatter);
        }
        holder.textViewWC.setText("WC: " + wc);

        LocalTime awakeTime = currentSleep.getAwake();
        String awake = "-";
        if (awakeTime != null) {
            awake = awakeTime.toString(formatter);
        }
        holder.textViewAwake.setText("Awake: " + awake);

        int thumbResId = getThumbResId(currentSleep);
        holder.imageViewThumb.setImageResource(thumbResId);
    }

    @Override
    public int getItemCount() {

        return sleeps.size();
    }

    public void setSleeps(List<Sleep> sleeps) {
        this.sleeps = sleeps;
        notifyDataSetChanged();
    }

    public Sleep getSleepAt(int position) {
        return sleeps.get(position);
    }

    private int getThumbResId(Sleep sleep) {
        if (sleep.getSatisfied()) {
            return R.drawable.ic_thumb_up_off;
        }
        else {
            return R.drawable.ic_thumb_down_off;
        }
    }

    class SleepHolder extends RecyclerView.ViewHolder {
        private TextView textViewSleep;
        private TextView textViewWC;
        private TextView textViewAwake;
        private ImageView imageViewThumb;

        public SleepHolder(@NonNull View itemView) {
            super(itemView);
            textViewSleep = itemView.findViewById(R.id.card_sleep_time);
            textViewWC = itemView.findViewById(R.id.card_sleep_wc);
            textViewAwake = itemView.findViewById(R.id.card_sleep_awake);
            imageViewThumb = itemView.findViewById(R.id.card_sleep_thumb);
        }
    }
}
