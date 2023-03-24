package me.yellowstrawberry.openneisapi.objects.school;

import me.yellowstrawberry.openneisapi.objects.others.Contact;
import me.yellowstrawberry.openneisapi.objects.others.EducationDepartment;
import me.yellowstrawberry.openneisapi.objects.others.Location;
import me.yellowstrawberry.openneisapi.objects.school.enums.EntranceExam;
import me.yellowstrawberry.openneisapi.objects.school.enums.GenderType;
import me.yellowstrawberry.openneisapi.objects.school.enums.OperatingPeriod;
import me.yellowstrawberry.openneisapi.objects.school.enums.SchoolType;

import java.text.SimpleDateFormat;
import java.util.Date;

public interface School {
    SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

    /**
     * <strong>학교 이름</strong>
     *
     * @return 학교 이름
     * */
    String getName();
    /**
     * <strong>학교 영문 이름</strong>
     *
     * @return 학교 영문 이름
     * */
    String getEnglishName();
    /**
     * <strong>학교 코드</strong>
     *
     * @return 학교 코드
     * */
    String getCode();
    /**
     * <strong>학교 설립일</strong>
     *
     * @return 학교 설립일
     * */
    Date getFoundingDate();
    /**
     * <strong>학교 개교 기념일</strong>
     *
     * @return 학교 개교 기념일
     * */
    Date getAnniversaryDate();

    /**
     * <strong>학교 종류</strong>
     *
     * @return 학교 종류
     * */
    SchoolType getType();
    /**
     * <strong>학교 위치 정보</strong>
     *
     * Location 내부에 있는 methods
     *
     * Location#province -> 시/도 (경기도, 강원도, 서울특별시)
     * Location#address -> 주소 (경기도 xx시 xx동 xx로 xx학교)
     * Location#detailedAddress -> 상세 주소 (xx학교 (xx면))
     * Location#postcode -> 우편번호
     * Location#localEducationDepartment -> 관할 지역 교육청
     *
     * @return Location
     * */
    Location getLocation();
    /**
     * <strong>학교 연락 정보</strong>
     *
     * Contact 내부에 있는 methods
     *
     * Contact#telephoneNumber -> 전화번호
     * Contact#faxNumber -> 펙스번호
     * Contact#website -> 웹사이트 주소
     *
     * @return Contact
     * */
    Contact getContact();
    /**
     * <strong>관할 시/도 교육청</strong>
     *
     * @return EducationDepartment
     * */
    EducationDepartment getEducationDepartment();

    /**
     * <strong>남자 여자 공학 구분</strong>
     *
     * @return GenderType
     * */
    GenderType getGenderType();
    /**
     * <strong>입시 시기</strong>
     * (전기, 후기)
     * @return EntranceExam
     * */
    EntranceExam getPeroidOfEntranceExam();
    /**
     * <strong>운영시기</strong>
     * (주간, 야간)
     * @return OperatingPeriod
     * */
    OperatingPeriod getOperatingPeriod();

    /**
     * <strong>정보 마지막 업데이트</strong>
     *
     * @return date
     * */
    Date lastUpdate();
}
