package me.yellowstrawberry.openneisapi.objects.schedule;

public class Period {
    private final String period;
    private final PeriodType type;
    private final String name;

    public Period(String period, PeriodType type, String name) {
        this.period = period;
        this.type = type;
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public PeriodType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
