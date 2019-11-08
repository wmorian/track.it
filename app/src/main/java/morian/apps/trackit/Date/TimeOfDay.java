package morian.apps.trackit.Date;

public enum TimeOfDay {
    MORNIGN(0),
    FORENOON(1),
    NOON(2),
    AFTERNOON(3),
    EVENING(4),
    NIGHT(5),
    ALLDAY(6);

    private final int value;

    TimeOfDay(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
