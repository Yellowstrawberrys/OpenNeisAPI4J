package me.yellowstrawberry.openneisapi.objects.school;

import me.yellowstrawberry.openneisapi.objects.others.Contact;
import me.yellowstrawberry.openneisapi.objects.others.EducationDepartment;
import me.yellowstrawberry.openneisapi.objects.others.Location;
import me.yellowstrawberry.openneisapi.objects.school.enums.*;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

/**
 * <strong>고등학교</strong>
 * @since 0.0.2
 * */
public class HighSchool implements School {
    private String name;
    private String englishName;
    private String code;
    private Date foundingDate;
    private Date anniveraryDate;

    private SchoolType type;
    private HighSchoolType highSchoolType;
    private boolean hasIndustrySpecialClass;
    private String generalBusinessType;
    private String specialPurposeType;
    private Location location;
    private Contact contact;
    private EducationDepartment educationDepartment;
    private GenderType genderType;
    private EntranceExam entranceExam;
    private OperatingPeriod operatingPeriod;
    private Date lastupdate;

    public static HighSchool parseFromJSONObject(JSONObject json) {
        try {
            HighSchool school = new HighSchool();
            school.name = json.getString("SCHUL_NM");
            school.englishName = json.getString("ENG_SCHUL_NM");
            school.code = json.getString("SD_SCHUL_CODE");
            school.foundingDate = School.format.parse(json.getString("FOND_YMD"));
            school.anniveraryDate = School.format.parse(json.getString("FOAS_MEMRD"));
            school.type = SchoolType.parseSchoolType(json.getString("SCHUL_KND_SC_NM"));
            school.highSchoolType = HighSchoolType.parse(json.getString("HS_SC_NM"));
            school.hasIndustrySpecialClass = json.getString("INDST_SPECL_CCCCL_EXST_YN").equals("Y");
            school.generalBusinessType = json.getString("HS_GNRL_BUSNS_SC_NM");
            school.specialPurposeType = !json.isNull("SPCLY_PURPS_HS_ORD_NM")?json.getString("SPCLY_PURPS_HS_ORD_NM"):null;
            school.location = new Location(json.getString("LCTN_SC_NM"), json.getString("ORG_RDNMA"), json.getString("ORG_RDNDA"), Integer.parseInt(json.getString("ORG_RDNZC").replaceAll(" ", "")), json.getString("JU_ORG_NM"));
            school.contact = new Contact(json.getString("ORG_TELNO"), json.getString("ORG_FAXNO"), json.getString("HMPG_ADRES"));
            school.educationDepartment = new EducationDepartment(json.getString("ATPT_OFCDC_SC_CODE"), json.getString("ATPT_OFCDC_SC_NM"));
            school.genderType = GenderType.parse(json.getString("COEDU_SC_NM"));
            school.entranceExam = EntranceExam.parse(json.getString("ENE_BFE_SEHF_SC_NM"));
            school.operatingPeriod = OperatingPeriod.parse(json.getString("DGHT_SC_NM"));
            school.lastupdate = School.format.parse(json.getString("LOAD_DTM"));

            return school;
        }catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 고등학교의 종류를 가져옵니다.
     * (일반고, 특성화고, 특목고)
     * @return 고등학교 종류
     * @since  0.0.2
     * */
    public HighSchoolType getHighSchoolType() {
        return highSchoolType;
    }

    /**
     * 산업체 특별학급 존재 여부를 가져옵니다.
     * @return 존재 여부
     * @since  0.0.2
     * */
    public boolean isHasIndustrySpecialClass() {
        return hasIndustrySpecialClass;
    }

    /**
     * 고등학교 일반/실업 구분명을 가져옵니다.
     * @return 일반/실업 구분명
     * @since 0.0.2
     * */
    public String getGeneralBusinessType() {
        return generalBusinessType;
    }

    /**
     * 특수 목적 고듷학교 계열명을 가져옵니다.
     * @return 특목고 계열명
     * @since 0.0.2
     * */
    public String getSpecialPurposeType() {
        return specialPurposeType;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getEnglishName() {
        return englishName;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public Date getFoundingDate() {
        return foundingDate;
    }

    @Override
    public Date getAnniversaryDate() {
        return anniveraryDate;
    }

    @Override
    public SchoolType getType() {
        return type;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Contact getContact() {
        return contact;
    }

    @Override
    public EducationDepartment getEducationDepartment() {
        return educationDepartment;
    }

    @Override
    public GenderType getGenderType() {
        return genderType;
    }

    @Override
    public EntranceExam getPeroidOfEntranceExam() {
        return entranceExam;
    }

    @Override
    public OperatingPeriod getOperatingPeriod() {
        return operatingPeriod;
    }

    @Override
    public Date lastUpdate() {
        return lastupdate;
    }
}
