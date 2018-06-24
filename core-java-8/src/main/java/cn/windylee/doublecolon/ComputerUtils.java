package cn.windylee.doublecolon;

import cn.windylee.doublecolon.function.ComputerPredicate;

import java.util.List;
import java.util.stream.Collectors;

public class ComputerUtils {

    static final ComputerPredicate after2010Predicate = (c)->(c.getAge() > 2010);

    static final ComputerPredicate blackPredicate = (c)->("black".equals(c.getColor()));

    public static List<Computer> filter(final List<Computer> inventory, final ComputerPredicate p){
        return inventory.stream().filter(p::filter).collect(Collectors.toList());
    }

}
