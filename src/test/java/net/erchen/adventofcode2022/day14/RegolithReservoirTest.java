package net.erchen.adventofcode2022.day14;

import lombok.SneakyThrows;
import net.erchen.adventofcode2022.day14.RegolithReservoir.Point;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class RegolithReservoirTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day14/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day14/input.txt"));
    }


    @Test
    void shouldDrawHorizontalLine() {
        var start = new Point(0, 5);
        var end = new Point(5, 5);

        assertThat(start.drawLineTo(end)).containsExactly(new Point(0, 5), new Point(1, 5), new Point(2, 5), new Point(3, 5), new Point(4, 5), new Point(5, 5));
    }

    @Test
    void shouldDrawReverseHorizontalLine() {
        var start = new Point(3, 1);
        var end = new Point(1, 1);

        assertThat(start.drawLineTo(end)).containsExactlyInAnyOrder(new Point(3, 1), new Point(2, 1), new Point(1, 1));
    }


    @Test
    void shouldVerticalLine() {
        var start = new Point(5, 0);
        var end = new Point(5, 5);

        assertThat(start.drawLineTo(end)).containsExactly(new Point(5, 0), new Point(5, 1), new Point(5, 2), new Point(5, 3), new Point(5, 4), new Point(5, 5));
    }

    @Test
    void shouldDrawReverseVerticalLine() {
        var start = new Point(1, 3);
        var end = new Point(1, 1);

        assertThat(start.drawLineTo(end)).containsExactlyInAnyOrder(new Point(1, 3), new Point(1, 2), new Point(1, 1));
    }

    @Test
    void shouldInitRockFromInput() {
        var rock = RegolithReservoir.Rock.fromInput("0,0 -> 0,2 -> 2,2");

        assertThat(rock.points()).containsExactlyInAnyOrder(new Point(0, 0), new Point(0, 1), new Point(0, 2), new Point(1, 2), new Point(2, 2));
    }

    @Test
    void shouldInitReservoirFromInput() {
        var rock = RegolithReservoir.fromInput(sampleInput());

        assertThat(rock.blockedFields()).hasSize(20);
    }

    @Test
    void shouldSimulateSand_Sample() {
        var reservoir = RegolithReservoir.fromInput(sampleInput());

        assertThat(reservoir.simulateSand()).hasSize(24);
    }

    @Test
    void shouldSimulateSand_Solution() {
        var reservoir = RegolithReservoir.fromInput(solutionInput());

        assertThat(reservoir.simulateSand()).hasSize(964);
    }

    @Test
    void shouldSimulateSandWithFloor_Sample() {
        var reservoir = RegolithReservoir.fromInput(sampleInput()).addFloor();

        assertThat(reservoir.simulateSand()).hasSize(93);
    }

    @Test
    void shouldSimulateSandWithFloor_Solution() {
        var reservoir = RegolithReservoir.fromInput(solutionInput()).addFloor();

        assertThat(reservoir.simulateSand()).hasSize(32041);
    }
}