package net.erchen.adventofcode2022.day21;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Operation {
    Plus('+'), Minus('-'), Multiply('*'), Divide('/');

    private final char identifier;

    public static Operation fromIdentifier(char identifier) {
        return Arrays.stream(Operation.values()).filter(operation -> operation.identifier == identifier).findFirst().orElseThrow();
    }

    @Override
    public String toString() {
        return String.valueOf(identifier);
    }
}
