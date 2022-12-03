package net.erchen.adventofcode2022.day03;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class ItemTest {


    @CsvSource({
            "a,1",
            "b,2",
            "z,26",
            "A,27",
            "B,28",
            "Z,52",
    })
    @ParameterizedTest
    void shouldCalculatePriority(char c, int expectedScore) {

        assertThat(new Item(c).priority()).isEqualTo(expectedScore);
    }
}