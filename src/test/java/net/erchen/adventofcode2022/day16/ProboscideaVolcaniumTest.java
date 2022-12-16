package net.erchen.adventofcode2022.day16;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ProboscideaVolcaniumTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day16/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day16/input.txt"));
    }

    @Test
    void shouldCalcMaximalPressure_Sample() {
        var proboscideaVolcanium = ProboscideaVolcanium.fromInput(sampleInput());

        assertThat(proboscideaVolcanium.maximumPressureRelease()).isEqualTo(1651);
    }

    @Test
    void shouldCalcMaximalPressure_Solution() {
        var proboscideaVolcanium = ProboscideaVolcanium.fromInput(solutionInput());

        assertThat(proboscideaVolcanium.maximumPressureRelease()).isEqualTo(1947);
    }

    @Test
    void shouldCalcMaximalPressureWithElephantHelp_Sample() {
        var proboscideaVolcanium = ProboscideaVolcanium.fromInput(sampleInput());

        assertThat(proboscideaVolcanium.maximumPressureReleaseWithElephantHelp()).isEqualTo(1707);
    }

    @Test
    @EnabledOnOs(OS.MAC)
        // will take 70seconds on my m1 pro, should not run on gitlab ci...
    void shouldCalcMaximalPressureWithElephantHelp_Solution() {
        var proboscideaVolcanium = ProboscideaVolcanium.fromInput(solutionInput());

        assertThat(proboscideaVolcanium.maximumPressureReleaseWithElephantHelp()).isEqualTo(2556);
    }
}