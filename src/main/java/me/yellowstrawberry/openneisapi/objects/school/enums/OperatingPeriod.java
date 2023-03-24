package me.yellowstrawberry.openneisapi.objects.school.enums;

public enum OperatingPeriod {
    Day,
    Night,
    Unknown;

    public static OperatingPeriod parse(String kr) {
        if(kr.equals("주간")) return Day;
        else if(kr.equals("야간")) return Night;
        else return Unknown;
    }
}
