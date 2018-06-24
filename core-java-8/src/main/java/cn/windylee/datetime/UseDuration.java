package cn.windylee.datetime;

import java.time.Duration;
import java.time.LocalTime;

public class UseDuration {

    public LocalTime modifyDates(LocalTime localTime, Duration duration) {
        return localTime.plus(duration);
    }

    public Duration getDiffBetweenDates(LocalTime time1, LocalTime time2) {
        return Duration.between(time1, time2);
    }

}
