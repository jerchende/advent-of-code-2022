package net.erchen.adventofcode2022.day11;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class KeepAwayGameTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day11/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day11/input.txt"));
    }


    @Test
    void shouldPlayRound() {
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput());

        keepAwayGame.playRound();

        assertThat(keepAwayGame.monkeys().get(0).items()).containsExactly(20, 23, 27, 26);
        assertThat(keepAwayGame.monkeys().get(1).items()).containsExactly(2080, 25, 167, 207, 401, 1046);
        assertThat(keepAwayGame.monkeys().get(2).items()).isEmpty();
        assertThat(keepAwayGame.monkeys().get(3).items()).isEmpty();
    }

    @Test
    void shouldPlayTwentyRounds() {
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput());

        keepAwayGame.playRounds(20);

        assertThat(keepAwayGame.monkeys().get(0).items()).containsExactly(10, 12, 14, 26, 34);
        assertThat(keepAwayGame.monkeys().get(1).items()).containsExactly(245, 93, 53, 199, 115);
        assertThat(keepAwayGame.monkeys().get(2).items()).isEmpty();
        assertThat(keepAwayGame.monkeys().get(3).items()).isEmpty();

        assertThat(keepAwayGame.stats().get(0).get()).isEqualTo(101);
        assertThat(keepAwayGame.stats().get(1).get()).isEqualTo(95);
        assertThat(keepAwayGame.stats().get(2).get()).isEqualTo(7);
        assertThat(keepAwayGame.stats().get(3).get()).isEqualTo(105);
    }

    @Test
    void levelOfMonkeyBusiness_Sample() {
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput());

        keepAwayGame.playRounds(20);

        assertThat(keepAwayGame.levelOfMonkeyBusiness()).isEqualTo(10605);
    }

    @Test
    void levelOfMonkeyBusiness_Solution() {
        var keepAwayGame = KeepAwayGame.fromInput(solutionInput());

        keepAwayGame.playRounds(20);

        assertThat(keepAwayGame.levelOfMonkeyBusiness()).isEqualTo(151312);
    }
}