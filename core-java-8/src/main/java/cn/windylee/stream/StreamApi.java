package cn.windylee.stream;

import java.util.List;
import java.util.stream.Stream;

public class StreamApi {

    public static String getLastElementUsingReduce(List<String> valueList){
        return valueList.stream().reduce((x1, x2)->x2).orElse(null);
    }

    public static Integer getInfiniteStreamLastElementUsingReduce(){
        return Stream.iterate(0, i->i+1).limit(20).reduce((x1, x2)->x2).orElse(null);
    }

    public static String getLastElementUsingSkip(List<String> valueList){
        return valueList.stream().skip(valueList.size() - 1).findFirst().orElse(null);
    }

}
