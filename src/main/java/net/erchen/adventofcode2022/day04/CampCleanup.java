package net.erchen.adventofcode2022.day04;

import net.erchen.codingpuzzlescommon.parser.SeparatorParser;

import java.util.List;

public record CampCleanup(List<ElfGroup> elfGroups) {

    public static CampCleanup fromInput(String input) {
        return new CampCleanup(SeparatorParser.parseInput(input, "\n", ElfGroup::fromInput));
    }

    public int countFullyOverlappingGroups() {
        return (int) elfGroups.stream().filter(ElfGroup::isFullyOverlapped).count();
    }

    public int countPartiallyOverlappingGroups() {
        return (int) elfGroups.stream().filter(ElfGroup::isPartiallyOverlapped).count();
    }
}

