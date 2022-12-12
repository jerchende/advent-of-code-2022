package net.erchen.adventofcode2022.day12;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class HillClimbingAlgorithmTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day12/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day12/input.txt"));
    }


    @Test
    void shouldCalcFewestSteps_Sample() {
        var hillClimbingAlgorithm = HillClimbingAlgorithm.fromInput(sampleInput());

        assertThat(hillClimbingAlgorithm.calculateFewestSteps()).isEqualTo(31);
    }

    @Test
    void shouldCalcFewestSteps_Solution() {
        var hillClimbingAlgorithm = HillClimbingAlgorithm.fromInput(solutionInput());

        assertThat(hillClimbingAlgorithm.calculateFewestSteps()).isEqualTo(528);
    }

    @Test
    void shouldCalcFewestStepsFromAnyLowestPoint_Sample() {
        assertThat(HillClimbingAlgorithm.calculateFewestStepsFromAnyLowestPoint(sampleInput())).isEqualTo(29);
    }

    @Test
    void shouldCalcFewestStepsFromAnyLowestPoint_Solution() {
        assertThat(HillClimbingAlgorithm.calculateFewestStepsFromAnyLowestPoint(solutionInput())).isEqualTo(522);
    }
}