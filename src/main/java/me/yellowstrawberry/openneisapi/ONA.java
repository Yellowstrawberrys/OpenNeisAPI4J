package me.yellowstrawberry.openneisapi;

import me.yellowstrawberry.openneisapi.exception.NeisException;
import me.yellowstrawberry.openneisapi.objects.food.SchoolMeal;
import me.yellowstrawberry.openneisapi.objects.schedule.Period;
import me.yellowstrawberry.openneisapi.objects.schedule.PeriodType;
import me.yellowstrawberry.openneisapi.objects.school.*;
import me.yellowstrawberry.openneisapi.objects.school.enums.SchoolType;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ONA {

    private static String baseURL = "https://open.neis.go.kr/hub";

    private OkHttpClient client;
    private String apiKey;

    private ONA() {
    }

    ONA(String apiKey) {
        this.client = new OkHttpClient();
        this.apiKey = apiKey;
    }

    /**
     * <strong>학교 찾기</strong>
     * <p>
     * 나이스 교육정보 개방 포털에서 학교를 찾습니다. (기본 5개)
     *
     * @param name 학교 이름
     * @throws NeisException 나이스 교육 정보 개방 포털에서 오류를 리턴했을때
     * @since 0.0.1
     */
    public School[] searchSchool(String name) throws IOException {
        return searchSchool(name, 5);
    }

    /**
     * <strong>학교 찾기</strong>
     * <p>
     * 나이스 교육정보 개방 포털에서 학교를 찾습니다.
     *
     * @param name     학교 이름
     * @param maxIndex 최대 개수
     * @since 0.0.1
     */
    public School[] searchSchool(String name, int maxIndex) throws IOException {
        List<School> schools = new ArrayList<>();

        JSONArray array = get("/schoolInfo", QueryParameter.of("pSize", String.valueOf(maxIndex)), QueryParameter.of("SCHUL_NM", name)).getJSONArray("schoolInfo").getJSONObject(1).getJSONArray("row");

        for (Object nObj : array) {
            JSONObject obj = new JSONObject(nObj.toString());

            SchoolType type = SchoolType.parseSchoolType(obj.getString("SCHUL_KND_SC_NM"));
            if (type == SchoolType.Elementary) schools.add(ElementarySchool.parseFromJSONObject(obj));
            else if (type == SchoolType.Middle) schools.add(MiddleSchool.parseFromJSONObject(obj));
            else if (type == SchoolType.High) schools.add(HighSchool.parseFromJSONObject(obj));
            else if (type == SchoolType.Special) schools.add(SpecialSchool.parseFromJSONObject(obj));
        }

        return schools.toArray(new School[]{});
    }

    /**
     * <strong>시간표 가져오기</strong>
     * <p>
     * 나이스 교육정보 개방 포털에서 시간표를 가져옵니다.
     *
     * @param school    학교
     * @param grade     학년
     * @param className 반
     * @param from      시간표를 가져올 첫날
     * @param to        시간표를 가져올 마지막날
     * @return 시간표
     * @throws DateTimeException 시간이 틀렸을때
     * @since 0.0.1
     */
    public Period[][] getSchedule(School school, int grade, String className, LocalDateTime from, LocalDateTime to) throws IOException {
        if (from.isBefore(to)) throw new DateTimeException("'from' should be before 'to'.");
        List<Period[]> periods = new ArrayList<>();

        while (!from.isEqual(to)) {
            periods.add(getScheduleOfDay(school, grade, className, Date.from(from.toLocalDate().atStartOfDay(ZoneId.systemDefault()).toInstant())));
            from = from.plusDays(1);
        }

        return periods.toArray(new Period[][]{});
    }

    /**
     * <strong>시간표 가져오기</strong>
     *
     * 나이스 교육정보 개방 포털에서 시간표를 가져옵니다.
     *
     * @param school    학교
     * @param grade     학년
     * @param className 반
     * @param date      날짜
     * @return 시간표
     * @since 0.0.1
     */
    public Period[] getScheduleOfDay(School school, int grade, String className, Date date) throws IOException {
        List<Period> periods = new ArrayList<>();

        String v = school.getType() == SchoolType.Elementary ? "/elsTimetable" : (
                school.getType() == SchoolType.Middle ? "/misTimetable" : (
                        school.getType() == SchoolType.High ? "/hisTimetable" : "/specialTimetable"
                )
        );
        JSONArray array = get(
                v,
                QueryParameter.of("ATPT_OFCDC_SC_CODE", school.getEducationDepartment().code()),
                QueryParameter.of("SD_SCHUL_CODE", school.getCode()),
                QueryParameter.of("GRADE", String.valueOf(grade)),
                QueryParameter.of("CLASS_NM", className),
                QueryParameter.of("ALL_TI_YMD", School.format.format(date))
        ).getJSONArray(v.substring(1)).getJSONObject(1).getJSONArray("row");

        for (Object nObj : array) {
            JSONObject obj = new JSONObject(nObj.toString());
            periods.add(new Period(obj.getString("PERIO"), PeriodType.parseType(obj.getString("ITRT_CNTNT").replace("-", "")), obj.getString("ITRT_CNTNT").replace("-", "")));
        }

        return periods.toArray(new Period[]{});
    }

    /**
     * <strong>급식 가져오기</strong>
     *
     * 나이스 교육정보 개방 포털에서 급식를 불러옵니다.
     *
     * @param school 학교
     * @param date 날짜
     * @return 급식
     * @since 0.0.2
     * */
    public SchoolMeal getMealOfDay(School school, Date date) throws IOException {
        JSONObject obj = get(
                "/mealServiceDietInfo",
                QueryParameter.of("ATPT_OFCDC_SC_CODE", school.getEducationDepartment().code()),
                QueryParameter.of("SD_SCHUL_CODE", school.getCode()),
                QueryParameter.of("MLSV_YMD", School.format.format(date))
        ).getJSONArray("mealServiceDietInfo").getJSONObject(1).getJSONArray("row").getJSONObject(0);

        return new SchoolMeal(obj);
    }

    private JSONObject get(String path, QueryParameter... params) throws IOException {
        Request req = new Request.Builder()
                .url(getURL(path, params))
                .build();

        Response res = client.newCall(req).execute();
        JSONObject jsonObject = new JSONObject(res.body().string());

        if(jsonObject.has("RESULT") && !jsonObject.getJSONObject("RESULT").getString("CODE").equals("INFO-000")) {
            throw new NeisException("OpenNeisAPI responded with error: "+jsonObject.getJSONObject("RESULT").getString("MESSAGE"));
        }
        return jsonObject;
    }

    private HttpUrl getURL(String path, QueryParameter... params) {
        HttpUrl.Builder url = HttpUrl.parse(baseURL + path).newBuilder();

        url.addQueryParameter("KEY", apiKey);
        url.addQueryParameter("Type", "json");
        for (QueryParameter param : params) {
            url.addQueryParameter(param.getKey(), param.getValue());
        }
        return url.build();
    }

    public static class Builder {

        private String apiKey;

        public Builder(String apiKey) {
            this.apiKey = apiKey;
        }

        public ONA build() {
            return new ONA(apiKey);
        }
    }
}