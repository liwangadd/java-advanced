package cn.windylee.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

public class UseLocalDate {

    LocalDate getLocalDateUsingFactoryofMethod(int year, int month, int day){
        return LocalDate.of(year, month, day);
    }

    LocalDate getLocalDateUsingParseMethod(String representation){
        return LocalDate.parse(representation);
    }

    LocalDate getLocalDateFromClock(){
        return LocalDate.now();
    }

    LocalDate getNextDay(LocalDate localDate){
        return localDate.plusDays(1);
    }

    LocalDate getPreviousDay(LocalDate localDate){
        return localDate.minusDays(1);
    }

    DayOfWeek getDayOfWeek(LocalDate localDate){
        return localDate.getDayOfWeek();
    }

    LocalDate getFirstDayOfMonth(){
        return LocalDate.now().with(TemporalAdjusters.firstDayOfNextMonth());
    }

    LocalDateTime getStartOfDay(LocalDate localDate){
        return localDate.atStartOfDay();
    }

}
