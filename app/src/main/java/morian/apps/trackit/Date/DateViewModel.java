package morian.apps.trackit.Date;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DateViewModel extends ViewModel {

    private final MutableLiveData<String> date = new MutableLiveData();

    public void setDate(String date) {
        this.date.setValue(date);
    }

    public MutableLiveData<String> getDate() {
        return this.date;
    }
}
