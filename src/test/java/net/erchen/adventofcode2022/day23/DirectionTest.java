package net.erchen.adventofcode2022.day23;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void shouldGetNextLookups() {
        assertThat(Direction.North.nextLookups()).containsExactly(Direction.North, Direction.South, Direction.West, Direction.East);
        assertThat(Direction.South.nextLookups()).containsExactly(Direction.South, Direction.West, Direction.East, Direction.North);
        assertThat(Direction.West.nextLookups()).containsExactly(Direction.West, Direction.East, Direction.North, Direction.South);
        assertThat(Direction.East.nextLookups()).containsExactly(Direction.East, Direction.North, Direction.South, Direction.West);
    }
}