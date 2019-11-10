package morian.apps.trackit.Date;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import org.joda.time.LocalDate;

public class DateViewModel extends ViewModel {

    private final MutableLiveData<LocalDate> date = new MutableLiveData();

    public void setDate(LocalDate date) {
        this.date.setValue(date);
    }

    public MutableLiveData<LocalDate> getDate() {
        return this.date;
    }
}
