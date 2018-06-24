package cn.windylee.datetime;

import java.time.LocalDate;
import java.time.Period;

public class UsePeriod {

    LocalDate modifyDates(LocalDate localDate, Period period) {
        return localDate.plus(period);
    }

    Period getDiffBetweenDates(LocalDate date1, LocalDate date2) {
        return Period.between(date1, date2);
    }
}
