package net.erchen.adventofcode2022.day01;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

class CalorieCounterTest {

    @SneakyThrows
    static String sampleInput() {
        return Files.readString(Path.of("src/test/resources/day01/sample.txt"));
    }

    @SneakyThrows
    static String solutionInput() {
        return Files.readString(Path.of("src/test/resources/day01/input.txt"));
    }

    @Test
    void shouldParseFromInput() {
        var calorieCounter = new CalorieCounter("""
                100
                200
                                
                300
                400""");

        assertThat(calorieCounter.getElfs()).hasSize(2);
    }

    @Test
    void shouldFindElfWithTheMostCalories_Sample() {
        var calorieCounter = new CalorieCounter(sampleInput());

        assertThat(calorieCounter.findElfWithTheMostCalories().totalCalories()).isEqualTo(24000);
    }

    @Test
    void shouldFindElfWithTheMostCalories_Part1() {
        var calorieCounter = new CalorieCounter(solutionInput());

        assertThat(calorieCounter.findElfWithTheMostCalories().totalCalories()).isEqualTo(70764);
    }

    @Test
    void shouldFindTop3ElvesWithTheMostCalories_Sample() {
        var calorieCounter = new CalorieCounter(sampleInput());

        assertThat(calorieCounter.findTop3ElvesWithTheMostCalories()).extracting(Elf::totalCalories).containsExactly(24000, 11000, 10000);
    }

    @Test
    void shouldFindTop3ElvesWithTheMostCalories_Part2() {
        var calorieCounter = new CalorieCounter(solutionInput());

        assertThat(calorieCounter.findTop3ElvesWithTheMostCalories()).extracting(Elf::totalCalories).containsExactly(70764, 67568, 65573);
    }
}