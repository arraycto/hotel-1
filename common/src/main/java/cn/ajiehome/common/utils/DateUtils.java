package cn.ajiehome.common.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @Author: Jie
 * @Date: 2020/12/27
 */
public class DateUtils {
    private static  final  String DATE_FORMANT_1 = "yyyyMMdd";
    private static final DateTimeFormatter DATE_TIME_FORMATTER_1 =  DateTimeFormatter.ofPattern(DATE_FORMANT_1);

    public static String formatDateTime(LocalDate localDate){
        return DATE_TIME_FORMATTER_1.format(localDate);
    }
}
