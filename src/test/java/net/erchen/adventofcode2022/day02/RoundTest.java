package net.erchen.adventofcode2022.day02;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static net.erchen.adventofcode2022.day02.Move.Paper;
import static net.erchen.adventofcode2022.day02.Move.Rock;
import static org.assertj.core.api.Assertions.assertThat;

class RoundTest {

    @Test
    void shouldInitByInput_Part1() {

        var round = Round.fromInputPart1("A Y");

        assertThat(round.getOpponentsMove()).isEqualTo(Rock);
        assertThat(round.getYourMove()).isEqualTo(Paper);
    }

    @Test
    void shouldInitByInput_Part2() {

        var round = Round.fromInputPart2("A Y");

        assertThat(round.getOpponentsMove()).isEqualTo(Rock);
        assertThat(round.getYourMove()).isEqualTo(Rock);
    }

    @Test
    void shouldInitByInput_Part22() {

        var round = Round.fromInputPart2("B X");

        assertThat(round.getOpponentsMove()).isEqualTo(Paper);
        assertThat(round.getYourMove()).isEqualTo(Rock);
    }

    @CsvSource(value = {
            "A Y,8",
            "B X,1",
            "C Z,6"
    })
    @ParameterizedTest
    void shouldCalculateScore(String input, Integer expectedScore) {
        var round = Round.fromInputPart1(input);

        assertThat(round.yourScore()).isEqualTo(expectedScore);
    }
}