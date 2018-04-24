package cn.windylee.string;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class JoinerSplitter {

    public static String join(String[] arrayOfString){
        return Arrays.asList(arrayOfString)
                .stream()
                .collect(joining(","));
    }

    public static String joinWithPrefixPostFix(String[] arrayOfString){
        return Arrays.asList(arrayOfString)
                .stream()
                .collect(joining(",", "[", "]"));
    }

    public static List<String> split(String str){
        return Stream.of(str.split(","))
                .map(String::new)
                .collect(toList());
    }

    public static List<Character> splitToListOfChar(String str){
        return str.chars()
                .mapToObj(item->(char)item)
                .collect(toList());
    }

}
