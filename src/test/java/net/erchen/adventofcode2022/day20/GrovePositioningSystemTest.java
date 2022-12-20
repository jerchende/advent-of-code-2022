package net.erchen.adventofcode2022.day20;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class GrovePositioningSystemTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day20/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day20/input.txt"));
    }


    @Test
    void shouldParseFromInput() {

        var gps = GrovePositioningSystem.fromInput(sampleInput(), false);

        assertThat(gps.getNumbers()).extracting(GrovePositioningSystem.Number::getValue).containsExactly(1L, 2L, -3L, 3L, -2L, 0L, 4L);
    }

    @Test
    void shouldMix_Part1() {

        var gps = GrovePositioningSystem.fromInput(sampleInput(), false);

        gps.mix();

        assertThat(gps.getNumbers()).extracting(GrovePositioningSystem.Number::getValue).containsExactly(1L, 2L, -3L, 4L, 0L, 3L, -2L);
    }


    @Test
    void shouldGetCoordinates_Part1_Sample() {
        var gps = GrovePositioningSystem.fromInput(sampleInput(), false);

        var coordinates = gps.coordinates(1);

        assertThat(coordinates).containsExactly(4L, -3L, 2L);
    }

    @Test
    void shouldGetCoordinates_Part1_Solution() {
        var gps = GrovePositioningSystem.fromInput(solutionInput(), false);

        var coordinates = gps.coordinates(1);

        assertThat(coordinates).containsExactly(-5270L, 5698L, 7156L);
    }

    @Test
    void shouldParseFromInputWithMultiplier() {

        var gps = GrovePositioningSystem.fromInput(sampleInput(), true);

        assertThat(gps.getNumbers()).extracting(GrovePositioningSystem.Number::getValue).containsExactly(811589153L, 1623178306L, -2434767459L, 2434767459L, -1623178306L, 0L, 3246356612L);
    }

    @Test
    void shouldMix_Part2() {

        var gps = GrovePositioningSystem.fromInput(sampleInput(), true);

        gps.mix();

        assertThat(gps.getNumbers()).extracting(GrovePositioningSystem.Number::getValue).containsExactly(0L, -2434767459L, 3246356612L, -1623178306L, 2434767459L, 1623178306L, 811589153L);
    }

    @Test
    void shouldGetCoordinates_Part2_Sample() {
        var gps = GrovePositioningSystem.fromInput(sampleInput(), true);

        var coordinates = gps.coordinates(10);

        assertThat(coordinates).containsExactly(811589153L, 2434767459L, -1623178306L);
    }

    @Test
    void shouldGetCoordinates_Part2_Solution() {
        var gps = GrovePositioningSystem.fromInput(solutionInput(), true);

        var coordinates = gps.coordinates(10);

        assertThat(coordinates).containsExactly(3101082153613L, 4489711194396L, -2683113739818L);
    }
}