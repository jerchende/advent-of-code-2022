package net.erchen.adventofcode2022.day21;

import java.util.Optional;

public record NumberYell(long value) implements Yell {

    static Optional<Yell> fromInput(String input) {
        return input.matches("[0-9]+") ? Optional.of(new NumberYell(Long.parseLong(input))) : Optional.empty();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    @Override
    public boolean containsHuman() {
        return false;
    }

    @Override
    public long resolveHuman(long expectedResult) {
        throw new IllegalStateException();
    }
}
