package net.erchen.adventofcode2022.day06;

import java.util.stream.IntStream;

public record Signal(String input) {

    public int paketMarker() {
        return marker(4);
    }

    public int messageMarker() {
        return marker(14);
    }

    private int marker(int length) {
        return IntStream.range(length, input.length())
                .filter(i -> input.substring(i - length, i).chars().distinct().count() == length)
                .findFirst()
                .orElseThrow();
    }
}
