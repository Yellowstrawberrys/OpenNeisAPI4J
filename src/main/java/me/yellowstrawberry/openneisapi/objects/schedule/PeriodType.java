package me.yellowstrawberry.openneisapi.objects.schedule;

public enum PeriodType {
    Korean,
    Mathematics,
    English,
    Science,
    Society,
    History,
    Chinese,
    Japanese,
    KoreanChinese,
    Russian,
    Vietnamese,
    Arabic,
    Music,
    Art,
    PhysicalEducation,
    TechnologyHome,
    Moral,
    InformationCommunicationsTechnology,
    CreativeExperienceActivities,
    Unknown;

    public static PeriodType parseType(String kr) {
        if(kr.contains("국어")) return Korean;
        else if(kr.contains("수학") || kr.contains("미적분")) return Mathematics;
        else if(kr.contains("영어")) return English;
        else if(kr.contains("과학") || kr.contains("물리") || kr.contains("화학") || kr.contains("생명") || kr.contains("지구")) return Science;
        else if(kr.contains("사회") || kr.contains("지리") || kr.contains("정치") || kr.contains("경제")) return Society;
        else if(kr.contains("역사") || kr.contains("세계사") || kr.contains("동아시아사")) return History;
        else if(kr.contains("중국어")) return Chinese;
        else if(kr.contains("일본어")) return Japanese;
        else if(kr.contains("한문")) return KoreanChinese;
        else if(kr.contains("러시아어")) return Russian;
        else if(kr.contains("베트남어")) return Vietnamese;
        else if(kr.contains("아랍어")) return Arabic;
        else if(kr.contains("음악")) return Music;
        else if(kr.contains("미술")) return Art;
        else if(kr.contains("체육")) return PhysicalEducation;
        else if(kr.contains("기술") && kr.contains("가정") || kr.contains("기과")) return TechnologyHome;
        else if(kr.contains("도덕")) return Moral;
        else if(kr.contains("정보")) return InformationCommunicationsTechnology;
        else if(kr.contains("창체") || kr.contains("자율") || kr.contains("창의적 체험활동")) return CreativeExperienceActivities;
        else return Unknown;
    }
}
