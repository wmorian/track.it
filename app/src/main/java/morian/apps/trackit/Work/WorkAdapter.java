package morian.apps.trackit.Work;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

import morian.apps.trackit.R;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.WorkHolder> {

    private List<Work> works = new ArrayList<>();

    @NonNull
    @Override
    public WorkHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View workView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.work_list_item, parent, false);
        return new WorkHolder(workView);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkHolder holder, int position) {
        Work currentWork = works.get(position);
        holder.textViewSubject.setText(currentWork.getSubject());

        DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
        String startTime = currentWork.getStartTime().toString(formatter);
        String endTime = currentWork.getEndTime().toString(formatter);
        holder.textViewTime.setText(startTime + " - " + endTime);

        int workplaceResId = getWorkplaceResId(currentWork);
        holder.imageViewPlace.setImageResource(workplaceResId);

        int thumbResId = getThumbResId(currentWork);
        holder.imageViewThumb.setImageResource(thumbResId);
    }

    @Override
    public int getItemCount() {

        return works.size();
    }

    public void setWorks(List<Work> works) {
        this.works = works;
        notifyDataSetChanged();
    }

    public Work getWorkAt(int position) {
        return works.get(position);
    }

    private int getWorkplaceResId(Work work) {
        if (work.getWorkplace() == Workplace.Work) {
            return R.drawable.ic_work_off;
        }
        else {
            return R.drawable.ic_home_off;
        }
    }

    private int getThumbResId(Work work) {
        if (work.getSatisfied()) {
            return R.drawable.ic_thumb_up_off;
        }
        else {
            return R.drawable.ic_thumb_down_off;
        }
    }

    class WorkHolder extends RecyclerView.ViewHolder {
        private TextView textViewSubject;
        private TextView textViewTime;
        private ImageView imageViewPlace;
        private ImageView imageViewThumb;

        public WorkHolder(@NonNull View itemView) {
            super(itemView);
            textViewSubject = itemView.findViewById(R.id.card_work_subject);
            textViewTime = itemView.findViewById(R.id.card_work_time);
            imageViewPlace = itemView.findViewById(R.id.card_work_place);
            imageViewThumb = itemView.findViewById(R.id.card_work_thumb);
        }
    }
}
