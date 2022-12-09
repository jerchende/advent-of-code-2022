package net.erchen.adventofcode2022.day09;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class RopeMotionSimulationTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day09/sample.txt"));
    }

    @SneakyThrows
    static String sampleInput2() {
        return Files.readString(Path.of("src/test/resources/day09/sample2.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day09/input.txt"));
    }


    @Test
    void shouldParseSimpleCommands() {
        var directions = RopeMotionSimulation.simpleCommands("R 11");

        assertThat(directions).containsExactly(Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right, Direction.Right);
    }

    @Test
    void shouldCountVisited_Part1_Sample() {
        var countVisitedTailFields = RopeMotionSimulation.countVisitedTailFields(sampleInput(), 2);

        assertThat(countVisitedTailFields).isEqualTo(13);
    }

    @Test
    void shouldCountVisited_Part1_Solution() {
        var countVisitedTailFields = RopeMotionSimulation.countVisitedTailFields(solutionInput(), 2);

        assertThat(countVisitedTailFields).isEqualTo(6271);
    }

    @Test
    void shouldCountVisited_Part2_Sample() {
        var countVisitedTailFields = RopeMotionSimulation.countVisitedTailFields(sampleInput(), 10);

        assertThat(countVisitedTailFields).isEqualTo(1);
    }

    @Test
    void shouldCountVisited_Part2_Sample2() {
        var countVisitedTailFields = RopeMotionSimulation.countVisitedTailFields(sampleInput2(), 10);

        assertThat(countVisitedTailFields).isEqualTo(36);
    }

    @Test
    void shouldCountVisited_Part2_Solution() {
        var countVisitedTailFields = RopeMotionSimulation.countVisitedTailFields(solutionInput(), 10);

        assertThat(countVisitedTailFields).isEqualTo(2458);
    }
}