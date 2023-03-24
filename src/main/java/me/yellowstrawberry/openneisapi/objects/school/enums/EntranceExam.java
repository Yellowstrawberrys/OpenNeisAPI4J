package me.yellowstrawberry.openneisapi.objects.school.enums;

public enum EntranceExam {
    Early,
    Late,
    Unknown;

    public static EntranceExam parse(String kr) {
        if(kr.equals("전기")) return Early;
        else if(kr.equals("후기")) return Late;
        else return Unknown;
    }
}
