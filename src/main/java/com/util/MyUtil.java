package com.util;

import java.sql.Date;
import java.time.LocalDate;

public class MyUtil {
    private MyUtil() {
    }

    public static LocalDate convertToLocalDateViaSqlDate(Date dateToConvert) {
        return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
    }
}
