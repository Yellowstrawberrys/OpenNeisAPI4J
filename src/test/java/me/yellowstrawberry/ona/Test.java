package me.yellowstrawberry.ona;

import me.yellowstrawberry.openneisapi.ONA;
import me.yellowstrawberry.openneisapi.exception.NeisException;
import me.yellowstrawberry.openneisapi.objects.school.ElementarySchool;
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

        ElementarySchool dawon = (ElementarySchool) ona.searchSchool("강서초등학교", 1)[0];
        System.out.println(ona.getMealOfDay(dawon, Date.from(LocalDate.now().minusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant())).getOrigin()[0].getFrom());
    }
}
