package net.erchen.adventofcode2022.day23;


import lombok.With;

import java.util.stream.Stream;

@With
public record Position(int x, int y) {

    public Position north() {
        return this.withY(y - 1);
    }

    public Position south() {
        return this.withY(y + 1);
    }

    public Position west() {
        return this.withX(x - 1);
    }

    public Position east() {
        return this.withX(x + 1);
    }

    public Position move(Direction direction) {
        return switch (direction) {
            case North -> north();
            case South -> south();
            case West -> west();
            case East -> east();
        };
    }

    public Stream<Position> adjacent() {
        return Stream.of(north(), north().east(), north().west(), south(), south().west(), south().east(), east(), west());
    }

    public Stream<Position> lookAtPosition(Direction direction) {
        return switch (direction) {
            case North -> Stream.of(north(), north().east(), north().west());
            case South -> Stream.of(south(), south().east(), south().west());
            case West -> Stream.of(west(), west().north(), west().south());
            case East -> Stream.of(east(), east().north(), east().south());
        };
    }
}
