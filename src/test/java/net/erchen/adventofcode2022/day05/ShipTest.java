package net.erchen.adventofcode2022.day05;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ShipTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day05/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day05/input.txt"));
    }

    @Test
    void shouldInitFromInput() {
        var ship = Ship.fromInput(sampleInput());

        assertThat(ship.getCommands()).hasSize(4);
        assertThat(ship.getCrates()).containsExactly(new LinkedList<>(List.of('N', 'Z')), new LinkedList<>(List.of('D', 'C', 'M')), new LinkedList<>(List.of('P')));
    }

    @Test
    void shouldApplyCommands() {
        var ship = Ship.fromInput(sampleInput());
        ship.applyCommandsCrateMover9000();

        assertThat(ship.getCrates()).containsExactly(new LinkedList<>(List.of('C')), new LinkedList<>(List.of('M')), new LinkedList<>(List.of('Z', 'N', 'D', 'P')));
    }

    @Test
    void shouldGetMessage() {
        var ship = Ship.fromInput(sampleInput());

        assertThat(ship.message()).isEqualTo("NDP");
    }

    @Test
    void SamplePart1() {
        var ship = Ship.fromInput(sampleInput());

        ship.applyCommandsCrateMover9000();

        assertThat(ship.message()).isEqualTo("CMZ");
    }

    @Test
    void SoulutionPart1() {
        var ship = Ship.fromInput(solutionInput());

        ship.applyCommandsCrateMover9000();

        assertThat(ship.message()).isEqualTo("MQTPGLLDN");
    }

    @Test
    void SamplePart2() {
        var ship = Ship.fromInput(sampleInput());

        ship.applyCommandsCrateMover9001();

        assertThat(ship.message()).isEqualTo("MCD");
    }

    @Test
    void SoulutionPart2() {
        var ship = Ship.fromInput(solutionInput());

        ship.applyCommandsCrateMover9001();

        assertThat(ship.message()).isEqualTo("LVZPSTTCZ");
    }


}