package me.yellowstrawberry.openneisapi.objects.food;

import me.yellowstrawberry.openneisapi.objects.school.School;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SchoolMeal {

    private final Date date;
    private final String code;
    private final int caterers;


    private final Food[] food;
    private final Origin[] origin;
    private final Nutrition[] nutrition;
    private final double Kcal;

    /**
     * <strong>급식</strong>가져오기
     *
     * 급식 class
     * @since 0.0.2
     * */
    public SchoolMeal(JSONObject json) {
        try {
            date = School.format.parse(json.getString("MLSV_YMD"));
            code = json.getString("MMEAL_SC_CODE");
            caterers = Integer.parseInt(json.getString("MLSV_FGR"));

            food = parseFood(json.getString("DDISH_NM"));
            origin = parseOrigin(json.getString("ORPLC_INFO"));
            nutrition = parseNutrition(json.getString("NTR_INFO"));
            Kcal = Double.parseDouble(json.getString("CAL_INFO").substring(0, json.getString("CAL_INFO").length() - 5));
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private Food[] parseFood(String text) {
        List<Food> foods = new ArrayList<>();

        String[] sts = text.split("<br/>");

        for(String st : sts) {
            foods.add(new Food(st));
        }
        return foods.toArray(new Food[]{});
    }

    private Origin[] parseOrigin(String text) {
        List<Origin> origins = new ArrayList<>();

        String[] sts = text.split("<br/>");

        for(String st : sts) {
            origins.add(new Origin(st));
        }
        return origins.toArray(new Origin[]{});
    }

    private Nutrition[] parseNutrition(String text) {
        List<Nutrition> nutrition = new ArrayList<>();

        String[] sts = text.split("<br/>");

        for(String st : sts) {
            nutrition.add(new Nutrition(st));
        }
        return nutrition.toArray(new Nutrition[]{});
    }

    /**
     * 급식이 배급되는 날짜를 가져옵니다.
     * @return 급식 배급일
     * @since 0.0.2
     * */
    public Date getDate() {
        return date;
    }

    /**
     * 급식 코드를 가져옵니다.
     * @return 급식코드
     * @since 0.0.2
     * */
    public String getCode() {
        return code;
    }

    /**
     * 급식이 배급되는 인원을 가져옵니다.
     * @return 인원수
     * @since 0.0.2
     * */
    public int getCaterers() {
        return caterers;
    }

    /**
     * 급식에 어떤 음식이 제공되는지 가져옵니다.
     * @return 급식
     * @since 0.0.2
     * */
    public Food[] getFood() {
        return food;
    }

    /**
     * 급식에 들어가는 재료의 원산지는 어디인지 가져옵니다.
     * @return 원산지
     * @since 0.0.2
     * */
    public Origin[] getOrigin() {
        return origin;
    }

    /**
     * 급식에 어떤 영양성분이 들어가있는지 가져옵니다.
     * @return 영양성분
     * @since 0.0.2
     * */
    public Nutrition[] getNutrition() {
        return nutrition;
    }

    /**
     * 해당 급식이 어느정도에 칼로리를 제공하는지 가져옵니다.
     * @return 칼로리
     * @since 0.0.2
     * */
    public double getKcal() {
        return Kcal;
    }
}
