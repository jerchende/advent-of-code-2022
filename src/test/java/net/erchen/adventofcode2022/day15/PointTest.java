package net.erchen.adventofcode2022.day15;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    void shouldCalcDistance() {
        assertThat(new Point(5, 5).distanceTo(new Point(10, 0))).isEqualTo(10);
    }
}