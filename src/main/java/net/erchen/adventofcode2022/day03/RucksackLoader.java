package net.erchen.adventofcode2022.day03;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.List;
import java.util.stream.IntStream;

public record RucksackLoader(List<Rucksack> rucksacks) {

    public static RucksackLoader fromInput(String input) {
        return new RucksackLoader(SeparatorParser.parseInput(input, "\n", Rucksack::fromInput));
    }

    public int priorities() {
        return rucksacks.stream().flatMap(Rucksack::commonItems).mapToInt(Item::priority).sum();
    }

    public int groupPriories() {
        return IntStream.range(0, rucksacks.size() / 3).map(i -> Rucksack.commonItem(rucksacks.get(i * 3), rucksacks.get(i * 3 + 1), rucksacks.get(i * 3 + 2)).priority()).sum();
    }
}
