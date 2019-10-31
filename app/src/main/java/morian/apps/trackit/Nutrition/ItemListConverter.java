package morian.apps.trackit.Nutrition;

import androidx.room.TypeConverter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemListConverter {

    @TypeConverter
    public static List<String> stringToList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        List<String> items = new ArrayList<>();

        for (String item :
                data.split(";")) {
            items.add(item);
        }

        return items;
    }

    @TypeConverter
    public static String listToString(List<String> items) {
        return String.join(";", items);
    }
}
