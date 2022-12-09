package net.erchen.adventofcode2022.day09;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class KnotTest {

    @Test
    void up() {
        var knot = new Knot(2, 2).up();

        assertThat(knot.x()).isEqualTo(2);
        assertThat(knot.y()).isEqualTo(1);
    }

    @Test
    void down() {
        var knot = new Knot(2, 2).down();

        assertThat(knot.x()).isEqualTo(2);
        assertThat(knot.y()).isEqualTo(3);
    }

    @Test
    void left() {
        var knot = new Knot(2, 2).left();

        assertThat(knot.x()).isEqualTo(1);
        assertThat(knot.y()).isEqualTo(2);
    }

    @Test
    void right() {
        var knot = new Knot(2, 2).right();

        assertThat(knot.x()).isEqualTo(3);
        assertThat(knot.y()).isEqualTo(2);
    }

    @CsvSource({
            "1, 1, true",
            "1, 2, true",
            "1, 3, true",
            "2, 1, true",
            "2, 2, true",
            "2, 3, true",
            "3, 1, true",
            "3, 2, true",
            "3, 3, true",

            "0, 1, false",
            "0, 2, false",
            "0, 3, false",
            "4, 1, false",
            "4, 2, false",
            "4, 3, false",

            "1, 0, false",
            "2, 0, false",
            "3, 0, false",
            "4, 4, false",
            "5, 4, false",
            "6, 4, false",

            "0, 0, false",
            "4, 4, false"
    })
    @ParameterizedTest
    void isNextTo(int otherX, int otherY, boolean isNextTo) {
        var knot = new Knot(2, 2);

        assertThat(knot.isNextTo(new Knot(otherX, otherY))).isEqualTo(isNextTo);
    }
}