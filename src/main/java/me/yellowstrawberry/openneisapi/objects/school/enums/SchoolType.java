package me.yellowstrawberry.openneisapi.objects.school.enums;

public enum SchoolType {
    Elementary("초등학교", "Elementary School"),
    Middle("중학교", "Middle Shcool"),
    High("고등학교", "High School"),
    Special("특수학교", "Special School"),
    Unknown("알 수 없음", "Unknown School");

    private final String korean;
    private final String english;

    SchoolType(String korean, String english) {
        this.korean = korean;
        this.english = english;
    }

    public String getKoreanName() {
        return korean;
    }

    public String getEnglishName() {
        return english;
    }

    public static SchoolType parseSchoolType(String type) {
        switch (type) {
            case "초등학교" -> {
                return SchoolType.Elementary;
            }
            case "중학교" -> {
                return SchoolType.Middle;
            }
            case "고등학교" -> {
                return SchoolType.High;
            }
            case "특수학교" -> {
                return SchoolType.Special;
            }
            default -> {
                System.out.printf("Unknown School Type: '%s'%n", type);
                return SchoolType.Unknown;
            }
        }
    }
}
