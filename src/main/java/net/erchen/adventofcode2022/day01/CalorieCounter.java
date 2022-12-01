package net.erchen.adventofcode2022.day01;

import lombok.Getter;
import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.Comparator;
import java.util.List;

public class CalorieCounter {

    @Getter
    private final List<Elf> elfs;

    public CalorieCounter(String input) {
        this.elfs = SeparatorParser.parseInput(input, "\n\n", Elf::parseInput);
    }

    public Elf findElfWithTheMostCalories() {
        return elfs.stream().max(Comparator.comparing(Elf::totalCalories)).orElse(null);
    }

    public List<Elf> findTop3ElvesWithTheMostCalories() {
        return elfs.stream().sorted(Comparator.comparing(Elf::totalCalories).reversed()).limit(3).toList();
    }
}
