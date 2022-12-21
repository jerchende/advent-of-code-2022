package net.erchen.adventofcode2022.day21;

import java.util.function.Function;

public interface Yell {

    static Yell fromInput(String input, Function<String, Yell> yellSupplier) {
        return NumberYell.fromInput(input).orElseGet(() -> MathOperationYell.fromInput(input, yellSupplier));
    }

    boolean containsHuman();


    long value();

    long resolveHuman(long expectedResult);

}
