package net.erchen.adventofcode2022.day04;

public record ElfGroup(Section elf1, Section elf2) {

    public static ElfGroup fromInput(String input) {
        var inputs = input.split(",");
        return new ElfGroup(Section.fromInput(inputs[0]), Section.fromInput(inputs[1]));
    }

    public boolean isFullyOverlapped() {
        return elf1.fullyOverlaps(elf2) || elf2.fullyOverlaps(elf1);
    }

    public boolean isPartiallyOverlapped() {
        return elf1.partiallyOverlaps(elf2);
    }
}
