package net.erchen.adventofcode2022.day22;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class MonkeyMapTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day22/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day22/input.txt"));
    }

    @Test
    void shouldInit() {
        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.getCommands()).hasSize(13);
        assertThat(monkeyMap.getTiles()).hasSize(192);
    }

    @Test
    void startPosition() {
        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.startingPosition()).isEqualTo(new Position(8, 0));
    }

    @Test
    void part1_Sample() {

        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.calculatePassword(false)).isEqualTo(6032);
    }

    @Test
    void part1_Solution() {

        var monkeyMap = MonkeyMap.fromInput(solutionInput());

        assertThat(monkeyMap.calculatePassword(false)).isEqualTo(20494);
    }

    @Test
    void part2_Sample() {

        var monkeyMap = MonkeyMap.fromInput(sampleInput());

        assertThat(monkeyMap.calculatePassword(true)).isEqualTo(5031);
    }

    @Test
    void part2_Solution() {

        var monkeyMap = MonkeyMap.fromInput(solutionInput());

        assertThat(monkeyMap.calculatePassword(true)).isEqualTo(20494);
    }
}