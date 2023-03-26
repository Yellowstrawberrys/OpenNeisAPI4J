# OpenNeisAPI4J
<a href="https://open.neis.go.kr/portal/mainPage.do">나이스 교육정보 개방 포털</a>이 제공하는 Rest API를 Java에서 쉽게 사용할 수 있도록 만든 API. <br/>
(해당 API는 나이스 교육정보 개방 포털이 공식적으로 제공하는 Java API가 아닙니다.)
<br/>

> 현재 사용 가능한 것:<br/>
> - 학교 찾기(초등, 중등, 고등, 특별)<br/>
> - 시간표 불러오기(초등, 중등, 고등, 특별)<br/>
> - 급식정보(초등, 중등, 고등, 특별)<br/>

> 추후 추가 예정인 것:<br/>
> - 나이스 교육정보 개방 포털이 제공하는 다른 데이터셋<br/>

<br/>

## 시작하기
```java
public class main {
    public static void main(String[] args) {
        ONA ona = new ONA.Builder("토큰").build();
        
        //학교 검색
        School school = ona.searchSchool("00초등학교")[0];
        
        //오늘 2학년 1반 첫 과목을 불러옴
        Period period = ona.getScheduleOfDay(dawon, 2, "1", Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()))[0];
        //과목 이름 출력
        System.out.println(period.getName());
    }
}
```