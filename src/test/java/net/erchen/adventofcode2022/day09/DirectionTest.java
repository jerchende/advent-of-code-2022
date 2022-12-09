package net.erchen.adventofcode2022.day09;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void shouldGetByChar() {
        assertThat(Direction.fromChar('U')).isEqualTo(Direction.Up);
        assertThat(Direction.fromChar('L')).isEqualTo(Direction.Left);
        assertThat(Direction.fromChar('R')).isEqualTo(Direction.Right);
        assertThat(Direction.fromChar('D')).isEqualTo(Direction.Down);
    }
}