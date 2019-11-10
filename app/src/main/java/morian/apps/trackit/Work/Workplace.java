package morian.apps.trackit.Work;

public enum Workplace {
    Work(0),
    HOME(1);

    private final int value;

    Workplace(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
