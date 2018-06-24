package cn.windylee.doublecolon.function;


import cn.windylee.doublecolon.Computer;

@FunctionalInterface
public interface ComputerPredicate {

    boolean filter(Computer c);

}
