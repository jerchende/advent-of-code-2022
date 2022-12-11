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
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput(), true);

        keepAwayGame.playRound();

        assertThat(keepAwayGame.monkeys().get(0).items()).containsExactly(20L, 23L, 27L, 26L);
        assertThat(keepAwayGame.monkeys().get(1).items()).containsExactly(2080L, 25L, 167L, 207L, 401L, 1046L);
        assertThat(keepAwayGame.monkeys().get(2).items()).isEmpty();
        assertThat(keepAwayGame.monkeys().get(3).items()).isEmpty();
    }

    @Test
    void shouldPlayTwentyRounds() {
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput(), true);

        keepAwayGame.playRounds(20);

        assertThat(keepAwayGame.monkeys().get(0).items()).containsExactly(10L, 12L, 14L, 26L, 34L);
        assertThat(keepAwayGame.monkeys().get(1).items()).containsExactly(245L, 93L, 53L, 199L, 115L);
        assertThat(keepAwayGame.monkeys().get(2).items()).isEmpty();
        assertThat(keepAwayGame.monkeys().get(3).items()).isEmpty();

        assertThat(keepAwayGame.stats().get(0).get()).isEqualTo(101L);
        assertThat(keepAwayGame.stats().get(1).get()).isEqualTo(95L);
        assertThat(keepAwayGame.stats().get(2).get()).isEqualTo(7L);
        assertThat(keepAwayGame.stats().get(3).get()).isEqualTo(105L);
    }

    @Test
    void levelOfMonkeyBusiness_Part1_Sample() {
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput(), true);

        keepAwayGame.playRounds(20);

        assertThat(keepAwayGame.levelOfMonkeyBusiness()).isEqualTo(10605L);
    }

    @Test
    void levelOfMonkeyBusiness_Part1_Solution() {
        var keepAwayGame = KeepAwayGame.fromInput(solutionInput(), true);

        keepAwayGame.playRounds(20);

        assertThat(keepAwayGame.levelOfMonkeyBusiness()).isEqualTo(151312L);
    }

    @Test
    void levelOfMonkeyBusiness_Part2_Sample() {
        var keepAwayGame = KeepAwayGame.fromInput(sampleInput(), false);

        keepAwayGame.playRounds(10000);

        assertThat(keepAwayGame.levelOfMonkeyBusiness()).isEqualTo(2713310158L);
    }

    @Test
    void levelOfMonkeyBusiness_Part2_Solution() {
        var keepAwayGame = KeepAwayGame.fromInput(solutionInput(), false);

        keepAwayGame.playRounds(10000);

        assertThat(keepAwayGame.levelOfMonkeyBusiness()).isEqualTo(51382025916L);
    }
}