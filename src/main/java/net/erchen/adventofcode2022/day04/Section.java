package net.erchen.adventofcode2022.day04;

import static java.lang.Integer.parseInt;

public record Section(int from, int to) {
    public static Section fromInput(String input) {
        var inputs = input.split("-");
        return new Section(parseInt(inputs[0]), parseInt(inputs[1]));
    }

    public boolean fullyOverlaps(Section other) {
        return this.from >= other.from && this.to <= other.to;
    }

    public boolean partiallyOverlaps(Section other) {
        return !(this.to < other.from || this.from > other.to);
    }
}
