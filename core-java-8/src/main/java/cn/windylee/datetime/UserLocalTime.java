package cn.windylee.datetime;

import java.time.LocalTime;

public class UserLocalTime {

    LocalTime getLocalTimeUsingFactoryOfMethod(int hour, int min, int sec) {
        return LocalTime.of(hour, min, sec);
    }

    LocalTime getLocalTimeUsingParseMethod(String timeRepresentation) {
        return LocalTime.parse(timeRepresentation);
    }

    LocalTime getLocalTimeFromClock() {
        return LocalTime.now();
    }

    LocalTime addAnHour(LocalTime localTime) {
        return localTime.plusHours(1);
    }

    int getHourFromLocalTime(LocalTime localTime) {
        return localTime.getHour();
    }

    LocalTime getLocalTimeWithMinuteSetToValue(LocalTime localTime, int min) {
        return localTime.withMinute(min);
    }

}
