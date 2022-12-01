package net.erchen.adventofcode2022.day01;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.List;

public record Elf(List<Integer> carriedCalories) {

    public static Elf parseInput(String input) {
        return new Elf(SeparatorParser.parseInput(input, "\n", Integer::valueOf));
    }

    public int totalCalories() {
        return carriedCalories.stream().mapToInt(Integer::intValue).sum();
    }
}
