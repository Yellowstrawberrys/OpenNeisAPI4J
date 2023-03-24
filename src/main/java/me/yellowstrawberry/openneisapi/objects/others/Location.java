package me.yellowstrawberry.openneisapi.objects.others;

public record Location(String province, String address, String detailedAddress, int postcode,
                       String localEducationDepartment) {}
