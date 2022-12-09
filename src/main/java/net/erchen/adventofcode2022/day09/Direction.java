package net.erchen.adventofcode2022.day09;


import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum Direction {

    Up('U'), Left('L'), Right('R'), Down('D');

    private final char c;

    public static Direction fromChar(char c) {
        return Arrays.stream(Direction.values()).filter(direction -> direction.c == c).findFirst().orElseThrow();
    }
}
