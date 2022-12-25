package net.erchen.adventofcode2022.day24;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class BlizzardTest {

    @CsvSource({
            "0,0,Up,0,4",
            "0,0,Down,0,1",
            "0,0,Right,1,0",
            "0,0,Left,4,0",

            "2,2,Up,2,1",
            "2,2,Down,2,3",
            "2,2,Right,3,2",
            "2,2,Left,1,2",

            "4,4,Up,4,3",
            "4,4,Down,4,0",
            "4,4,Right,0,4",
            "4,4,Left,3,4",
    })
    @ParameterizedTest
    void shouldMove(int currentX, int currentY, Direction direction, int expectedX, int expectedY) {

        var blizzard = new Blizzard(new Position(currentX, currentY), direction).move(5, 5);

        assertThat(blizzard.position()).isEqualTo(new Position(expectedX, expectedY));
        assertThat(blizzard.direction()).isEqualTo(direction);
    }
}