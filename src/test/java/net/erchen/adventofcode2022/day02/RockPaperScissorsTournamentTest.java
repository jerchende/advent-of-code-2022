package net.erchen.adventofcode2022.day02;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class RockPaperScissorsTournamentTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day02/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day02/input.txt"));
    }


    @Test
    void shouldCalculateScore_Part1_Sample() {
        var rockPaperScissorsTournament = RockPaperScissorsTournament.fromInputPart1(sampleInput());

        assertThat(rockPaperScissorsTournament.yourScore()).isEqualTo(15);
    }

    @Test
    void shouldCalculateScore_Part1_Solution() {
        var rockPaperScissorsTournament = RockPaperScissorsTournament.fromInputPart1(solutionInput());

        assertThat(rockPaperScissorsTournament.yourScore()).isEqualTo(12855);
    }

    @Test
    void shouldCalculateScore_Part2_Sample() {
        var rockPaperScissorsTournament = RockPaperScissorsTournament.fromInputPart2(sampleInput());

        assertThat(rockPaperScissorsTournament.yourScore()).isEqualTo(12);
    }

    @Test
    void shouldCalculateScore_Part2_Solution() {
        var rockPaperScissorsTournament = RockPaperScissorsTournament.fromInputPart2(solutionInput());

        assertThat(rockPaperScissorsTournament.yourScore()).isEqualTo(13726);
    }

}