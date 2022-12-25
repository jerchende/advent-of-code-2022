package net.erchen.adventofcode2022.day22;

import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
public enum TileType {
    Open('.'), Wall('#'), Nothing(' ');

    private final char representation;

    public static TileType fromInput(char input) {
        return Arrays.stream(TileType.values()).filter(d -> d.representation == input).findFirst().orElseThrow();
    }
}
