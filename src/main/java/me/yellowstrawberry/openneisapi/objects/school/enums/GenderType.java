package me.yellowstrawberry.openneisapi.objects.school.enums;

public enum GenderType {
    Boys,
    Girls,
    Coeducational,
    Unknown;

    public static GenderType parse(String kr) {
        if(kr.equals("남")) return Boys;
        else if(kr.equals("여")) return Girls;
        else if (kr.equals("남여공학")) return Coeducational;
        else return Unknown;
    }
}
