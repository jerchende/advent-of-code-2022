package net.erchen.adventofcode2022.day24;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;


@Getter
@RequiredArgsConstructor
public enum Direction {
    Up('^'), Down('v'), Left('<'), Right('>');

    private final char representation;

    public static Direction fromInput(char input) {
        return Arrays.stream(Direction.values()).filter(d -> d.representation == input).findFirst().orElseThrow();
    }
}
