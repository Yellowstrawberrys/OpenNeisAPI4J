package me.yellowstrawberry.ona;

import me.yellowstrawberry.openneisapi.ONA;
import me.yellowstrawberry.openneisapi.exception.NeisException;
import me.yellowstrawberry.openneisapi.objects.school.ElementarySchool;
import me.yellowstrawberry.openneisapi.objects.school.HighSchool;
import me.yellowstrawberry.openneisapi.objects.school.MiddleSchool;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws IOException {
        ONA ona = new ONA.Builder(args[0]).build();

        HighSchool sunrint = (HighSchool) ona.searchSchool("선린인터넷고등학교", 1)[0]; // 오류 내는것엔 진심인 선린
        System.out.println(ona.getMealOfDay(sunrint, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant())).getOrigin()[0].getFrom());
    }
}
