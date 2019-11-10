package morian.apps.trackit.Sport;

public enum SportKind {
    RUNNING(0),
    EXERCISE(1),
    STRETCHING(2),
    YOGA(3),
    CYCLING(4),
    WALKING(5);

    private final int value;

    SportKind(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
