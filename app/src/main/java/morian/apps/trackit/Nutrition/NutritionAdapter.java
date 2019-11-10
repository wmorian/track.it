package morian.apps.trackit.Nutrition;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

import morian.apps.trackit.R;

public class NutritionAdapter extends RecyclerView.Adapter<NutritionAdapter.NutritionHolder> {

    private List<Nutrition> nutritions = new ArrayList<>();


    @NonNull
    @Override
    public NutritionHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View nutritionView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.nutrition_list_item, parent, false);

        return new NutritionHolder(nutritionView);
    }

    @Override
    public void onBindViewHolder(@NonNull NutritionHolder holder, int position) {
        Nutrition currentNutrition = nutritions.get(position);
        holder.textViewTime.setText(currentNutrition.getTimeOfDay());

        StringJoiner joiner = new StringJoiner("\n");
        for (String item :
                currentNutrition.getItems()) {
            joiner.add(item);
        }

        holder.textViewItems.setText(joiner.toString());
    }

    @Override
    public int getItemCount() {

        return nutritions.size();
    }

    public void setNutritions(List<Nutrition> nutritions) {
        this.nutritions = nutritions;
        notifyDataSetChanged();
    }

    public Nutrition getNutritionAt(int position) {

        return nutritions.get(position);
    }

    class NutritionHolder extends RecyclerView.ViewHolder {
        private TextView textViewTime;
        private TextView textViewItems;

        public NutritionHolder(@NonNull View itemView) {
            super(itemView);
            textViewTime = itemView.findViewById(R.id.card_nutrition_time_of_day);
            textViewItems = itemView.findViewById(R.id.card_nutrition_items);
        }
    }
}
