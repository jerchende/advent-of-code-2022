package net.erchen.adventofcode2022.day24;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void shouldInitFromChar() {
        assertThat(Direction.fromInput('^')).isEqualTo(Direction.Up);
        assertThat(Direction.fromInput('v')).isEqualTo(Direction.Down);
        assertThat(Direction.fromInput('<')).isEqualTo(Direction.Left);
        assertThat(Direction.fromInput('>')).isEqualTo(Direction.Right);
    }
}