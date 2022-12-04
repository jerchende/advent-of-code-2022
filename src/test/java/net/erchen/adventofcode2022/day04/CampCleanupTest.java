package net.erchen.adventofcode2022.day04;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CampCleanupTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day04/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day04/input.txt"));
    }


    @Test
    void shouldInitFromInput() {
        var section = CampCleanup.fromInput(sampleInput());

        assertThat(section.elfGroups()).hasSize(6);
    }

    @Test
    void shouldCountFullOverlappingGroups_Sample() {
        var section = CampCleanup.fromInput(sampleInput());

        assertThat(section.countFullyOverlappingGroups()).isEqualTo(2);
    }

    @Test
    void shouldCountFullOverlappingGroups_Solution() {
        var section = CampCleanup.fromInput(solutionInput());

        assertThat(section.countFullyOverlappingGroups()).isEqualTo(560);
    }

    @Test
    void shouldCountPartiallyOverlappingGroups_Sample() {
        var section = CampCleanup.fromInput(sampleInput());

        assertThat(section.countPartiallyOverlappingGroups()).isEqualTo(4);
    }

    @Test
    void shouldCountPartiallyOverlappingGroups_Solution() {
        var section = CampCleanup.fromInput(solutionInput());

        assertThat(section.countPartiallyOverlappingGroups()).isEqualTo(839);
    }
}