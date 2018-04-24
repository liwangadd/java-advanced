package cn.windylee.string;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class StringJoinerTest {

    private final String DELIMITER_COMMA = ",";
    private final String DELIMITER_HYPHEN = "-";
    private final String PREFIX = "[";
    private final String SUFFIX = "]";
    private final String EMPTY_JOINER = "empty";

    @Test
    public void testStringJoiner() {
        StringJoiner joiner = new StringJoiner(DELIMITER_COMMA);
        Assert.assertEquals(0, joiner.toString().length());

        joiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        Assert.assertEquals(joiner.toString(), PREFIX + SUFFIX);

        joiner = new StringJoiner(DELIMITER_COMMA);
        joiner.setEmptyValue(EMPTY_JOINER);
        Assert.assertEquals(EMPTY_JOINER, joiner.toString());

        joiner = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        joiner.setEmptyValue(EMPTY_JOINER);
        Assert.assertEquals(joiner.toString(), EMPTY_JOINER);

        joiner.add("red").add("green").add("blue");
        Assert.assertEquals(joiner.toString(), "[red,green,blue]");

        StringJoiner joiner1 = new StringJoiner(DELIMITER_COMMA, PREFIX, SUFFIX);
        StringJoiner joiner2 = new StringJoiner(DELIMITER_HYPHEN, PREFIX, SUFFIX);
        joiner1.add("red").add("green").add("blue");
        joiner2.add("red").add("green").add("blue");
        joiner1.merge(joiner2);
        Assert.assertEquals(joiner1.toString(), "[red,green,blue,red-green-blue]");

        List<String> rgbList = Arrays.asList("Red", "Green", "Blue");
        String result = rgbList.stream()
                .collect(Collectors.joining(","));
        Assert.assertEquals(result, "Red,Green,Blue");

    }

}
