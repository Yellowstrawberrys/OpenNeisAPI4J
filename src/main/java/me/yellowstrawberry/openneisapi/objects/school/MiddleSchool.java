package me.yellowstrawberry.openneisapi.objects.school;

import me.yellowstrawberry.openneisapi.objects.others.Contact;
import me.yellowstrawberry.openneisapi.objects.others.EducationDepartment;
import me.yellowstrawberry.openneisapi.objects.others.Location;
import me.yellowstrawberry.openneisapi.objects.school.enums.EntranceExam;
import me.yellowstrawberry.openneisapi.objects.school.enums.GenderType;
import me.yellowstrawberry.openneisapi.objects.school.enums.OperatingPeriod;
import me.yellowstrawberry.openneisapi.objects.school.enums.SchoolType;
import org.json.JSONObject;

import java.text.ParseException;
import java.util.Date;

/**
 * <strong>중학교</strong>
 * */
public class MiddleSchool implements School {
    private String name;
    private String englishName;
    private String code;
    private Date foundingDate;
    private Date anniveraryDate;

    private SchoolType type;
    private Location location;
    private Contact contact;
    private EducationDepartment educationDepartment;
    private GenderType genderType;
    private EntranceExam entranceExam;
    private OperatingPeriod operatingPeriod;
    private Date lastupdate;

    public static MiddleSchool parseFromJSONObject(JSONObject json) {
        try {
            MiddleSchool school = new MiddleSchool();
            school.name = json.getString("SCHUL_NM");
            school.englishName = json.getString("ENG_SCHUL_NM");
            school.code = json.getString("SD_SCHUL_CODE");
            school.foundingDate = School.format.parse(json.getString("FOND_YMD"));
            school.anniveraryDate = School.format.parse(json.getString("FOAS_MEMRD"));
            school.type = SchoolType.parseSchoolType(json.getString("SCHUL_KND_SC_NM"));
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
