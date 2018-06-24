package cn.windylee.datetime;

import java.time.LocalDateTime;

public class UseLocalDateTime {

    LocalDateTime getLocalDateTimeUsingParseMethod(String representation){
        return LocalDateTime.parse(representation);
    }

}
