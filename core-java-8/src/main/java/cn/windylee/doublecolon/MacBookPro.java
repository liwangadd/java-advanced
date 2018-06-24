package cn.windylee.doublecolon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class MacBookPro extends Computer {

    private static final Logger LOG = LoggerFactory.getLogger(MacBookPro.class);

    public MacBookPro(int age, String color) {
        super(age, color);
    }

    MacBookPro(Integer age, String color, Integer healty) {
        super(age, color, healty);
    }

    @Override
    public void turnOnPc() {
        LOG.debug("MacbookPro turned on");
    }

    @Override
    public void turnOffPc() {
        LOG.debug("MacBookPro turned off");
    }

    public Double calculateValue(double initialValue){
        Function<Double, Double> function = super::calculateValue;
        final Double pcValue = function.apply(initialValue);
        LOG.debug("First Value is: " + pcValue);
        return pcValue + (initialValue / 10);
    }

}
