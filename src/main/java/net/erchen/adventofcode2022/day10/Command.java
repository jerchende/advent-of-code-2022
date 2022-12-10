package net.erchen.adventofcode2022.day10;


import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Command {

    Noop(1), AddX(2);

    private final int cycles;

    public static Command fromInput(String input) {
        return Arrays.stream(Command.values()).filter(c -> c.name().equalsIgnoreCase(input)).findFirst().orElseThrow();
    }
}
