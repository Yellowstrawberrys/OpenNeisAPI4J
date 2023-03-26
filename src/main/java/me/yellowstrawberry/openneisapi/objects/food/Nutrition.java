package me.yellowstrawberry.openneisapi.objects.food;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Nutrition {
    private static final Pattern pattern = Pattern.compile("(.*)\\((.*)\\) : (\\d)");

    private final String type;
    private final String measurementType;
    private final double mass;

    /**
     * <strong>영양성분</strong>
     *
     * 이 영양성분의 이름(종류)이 무엇인지, 질량을 측정할때 어떤 단위를 쓰는지, 질량이 얼만큼 되는지 알 수 있습니다.
     * @since 0.0.2
     * */
    public Nutrition(String text) {
        Matcher matcher = pattern.matcher(text);

        if(!matcher.find()) throw new IllegalArgumentException("Cannot find any nutrition data");

        type = matcher.group(1);
        measurementType = matcher.group(2);
        mass = Double.parseDouble(matcher.group(3));
    }

    /**
     * 영양성분 종류 가져오기
     * @return 영양성분 종류
     * @since 0.0.2
     * */
    public String getType() {
        return type;
    }

    /**
     * 질량 측정 단위 가져오기
     * @return 질량 측정 단위
     * @since 0.0.2
     * */
    public String getMeasurementType() {
        return measurementType;
    }

    /**
     * 질량 가져오기
     * @return 질량
     * @since 0.0.2
     * */
    public double getMass() {
        return mass;
    }

    /**
     * 질량+질량측정단위 가져오기
     *
     * 예시값: "2.0mg", "4.6g"
     * @return 질량+질량측정단위
     * @since 0.0.2
     * */
    public String getFormattedMass() {
        return mass+measurementType;
    }
}
