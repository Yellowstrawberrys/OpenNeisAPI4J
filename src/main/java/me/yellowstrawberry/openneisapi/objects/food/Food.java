package me.yellowstrawberry.openneisapi.objects.food;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Food {
    private final String name;
    private final Allergy[] allergies;
    private static final Pattern pattern = Pattern.compile(".+ \\((.*)\\)");

    /**
     * <strong>음식</strong>
     *
     * 어떤 음식인지, 이 음식에서 유발할 수 있는 알러지 반응은 무엇이 있는지 알 수 있습니다.
     * @since 0.0.2
     * */
    public Food(String text) {
        String[] parsed = parse(text);
        System.out.println(text);
        name = parsed[0];
        allergies = Allergy.parse(parsed[1]);
    }

    private String[] parse(String text) {
        Matcher matcher = pattern.matcher(text);
        if(matcher.find()) return new String[]{text, matcher.group(1)};
        else return new String[]{text, null};
    }

    private int lastIndexFromEnd(String text, char c) {
        for(int i=text.lastIndexOf(c); i>0; i--) {
            if(text.charAt(i) != c) return i+1;
        }
        return text.length();
    }

    private boolean isNumber(char c) {
        return c == '0' || c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    /**
     * 음식 이름 가져오기
     * @return 음식 이름
     * @since 0.0.2
     * */
    public String getName() {
        return name;
    }

    /**
     * 알러지 정보 가져오기
     * @return 알러지 유발 정보
     * @since 0.0.2
     * */
    public Allergy[] getAllergies() {
        return allergies;
    }
}
