package net.erchen.adventofcode2022.day23;

import java.util.stream.Stream;

public enum Direction {
    North, South, West, East;

    public Direction next() {
        return switch (this) {
            case North -> South;
            case South -> West;
            case West -> East;
            case East -> North;
        };
    }

    public Stream<Direction> nextLookups() {
        return Stream.iterate(this, Direction::next).limit(Direction.values().length);
    }
}
