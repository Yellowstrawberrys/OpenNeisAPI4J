package me.yellowstrawberry.openneisapi.objects.school.enums;

public enum HighSchoolType {
    Normal,
    Specialized,
    SpecialPurpose,
    Autonomous,
    Unknown;

    public static HighSchoolType parse(String kr) {
        if(kr.equals("일반고")) return Normal;
        else if(kr.equals("특성화고")) return Specialized;
        else if(kr.equals("특목고")) return SpecialPurpose;
        else if(kr.equals("자율고")) return Autonomous;
        else return Unknown;
    }
}
