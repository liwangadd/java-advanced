package cn.windylee.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JoinerSplitterTest {

    @Test
    public void testJoin(){
        String[] programLang={"java","python","ruby"};
        String expectation="java,python,ruby";
        String result = JoinerSplitter.join(programLang);
        assertEquals(expectation, result);
    }

    @Test
    public void testJoinWithFix(){
        String[] programLang={"java","python","ruby"};
        String expectation="[java,python,ruby]";
        String result = JoinerSplitter.joinWithPrefixPostFix(programLang);
        assertEquals(expectation, result);
    }

    @Test
    public void testSplit(){
        String programLang="java,python,nodejs,ruby";
        List<String> expectation = Arrays.asList("java","python","nodejs","ruby");
        List<String> result = JoinerSplitter.split(programLang);
        assertEquals(expectation, result);
    }

    @Test
    public void testSplitToListOfChar(){
        String programLang = "java,python,nodejs,ruby";
        List<Character> expectation = new ArrayList<>();
        for(char c:programLang.toCharArray()){
            expectation.add(c);
        }
        List<Character> result = JoinerSplitter.splitToListOfChar(programLang);
        assertEquals(expectation, result);
    }

}
