package net.erchen.adventofcode2022.day21;

public class Human implements Yell {
    @Override
    public long value() {
        throw new IllegalStateException();
    }

    @Override
    public String toString() {
        return "X";
    }

    @Override
    public boolean containsHuman() {
        return true;
    }

    @Override
    public long resolveHuman(long expectedResult) {
        return expectedResult;
    }
}
