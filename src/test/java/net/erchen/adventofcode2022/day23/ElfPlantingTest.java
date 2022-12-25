package net.erchen.adventofcode2022.day23;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class ElfPlantingTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day23/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day23/input.txt"));
    }

    @Test
    void shouldInit() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        assertThat(elfPlanting.getElves()).hasSize(22);
    }

    @Test
    void shouldGetCurrentWidth() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        assertThat(elfPlanting.currentWidth()).isEqualTo(7);
    }

    @Test
    void shouldGetCurrentHeight() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        assertThat(elfPlanting.currentHeight()).isEqualTo(7);
    }

    @Test
    void shouldCountEmptyFields() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        assertThat(elfPlanting.countEmptyFields()).isEqualTo(27);
    }

    @Test
    void shouldPrint() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        assertThat(elfPlanting.toString()).isEqualTo(sampleInput());
    }

    @Test
    void shouldPlayRound_MiniSample() {
        var elfPlanting = ElfPlanting.fromInput("""
                .....
                ..##.
                ..#..
                .....
                ..##.
                .....
                """);

        elfPlanting.playRound();

        assertThat(elfPlanting.toString()).isEqualTo("""
                ##
                ..
                #.
                .#
                #.
                """);

        elfPlanting.playRound();

        assertThat(elfPlanting.toString()).isEqualTo("""                
                .##.
                #...
                ...#
                ....
                .#..
                """);

        elfPlanting.playRound();

        assertThat(elfPlanting.toString()).isEqualTo("""                
                ..#..
                ....#
                #....
                ....#
                .....
                ..#..
                """);
    }

    @Test
    void shouldPlayRound_Sample() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        elfPlanting.playRounds(10);

        assertThat(elfPlanting.toString()).isEqualTo("""
                ......#.....
                ..........#.
                .#.#..#.....
                .....#......
                ..#.....#..#
                #......##...
                ....##......
                .#........#.
                ...#.#..#...
                ............
                ...#..#..#..
                """);
        elfPlanting.playRounds(10);
        assertThat(elfPlanting.toString()).isEqualTo("""
                .......#......
                ....#......#..
                ..#.....#.....
                ......#.......
                ...#....#.#..#
                #.............
                ....#.....#...
                ..#.....#.....
                ....#.#....#..
                .........#....
                ....#......#..
                .......#......
                """);

    }

    @Test
    void shouldCountEmptyFieldsAfter10Rounds_Sample() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        elfPlanting.playRounds(10);

        assertThat(elfPlanting.countEmptyFields()).isEqualTo(110);
    }

    @Test
    void shouldCountEmptyFieldsAfter10Rounds_Solution() {
        var elfPlanting = ElfPlanting.fromInput(solutionInput());

        elfPlanting.playRounds(10);

        assertThat(elfPlanting.countEmptyFields()).isEqualTo(4025);
    }

    @Test
    void shouldCountRoundsUntilNoOneMoves_Sample() {
        var elfPlanting = ElfPlanting.fromInput(sampleInput());

        assertThat(elfPlanting.countRoundsUntilNoOneMoves()).isEqualTo(20);
    }

    @Test
    void shouldCountRoundsUntilNoOneMoves_Solution() {
        var elfPlanting = ElfPlanting.fromInput(solutionInput());

        assertThat(elfPlanting.countRoundsUntilNoOneMoves()).isEqualTo(935);
    }
}