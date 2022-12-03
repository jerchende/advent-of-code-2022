package net.erchen.adventofcode2022.day03;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class RucksackLoaderTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day03/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day03/input.txt"));
    }


    @Test
    void fromInput() {
        var rucksackLoader = RucksackLoader.fromInput(sampleInput());

        assertThat(rucksackLoader.rucksacks()).hasSize(6);
    }

    @Test
    void shouldSumPriorities_Sample() {
        var rucksackLoader = RucksackLoader.fromInput(sampleInput());

        assertThat(rucksackLoader.priorities()).isEqualTo(157);
    }

    @Test
    void shouldSumPriorities_Solution() {
        var rucksackLoader = RucksackLoader.fromInput(solutionInput());

        assertThat(rucksackLoader.priorities()).isEqualTo(7811);
    }

    @Test
    void shouldSumGroupPriorities_Sample() {
        var rucksackLoader = RucksackLoader.fromInput(sampleInput());

        assertThat(rucksackLoader.groupPriories()).isEqualTo(70);
    }

    @Test
    void shouldSumGroupPriorities_Solution() {
        var rucksackLoader = RucksackLoader.fromInput(solutionInput());

        assertThat(rucksackLoader.groupPriories()).isEqualTo(2639);
    }
}